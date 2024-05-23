package com.example.cbzcstsowbillpaymentsintegration.service;

import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto.StudentTransactionRequestDTO;
import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto.ZimraAccountCreationRequest;
import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto.ZimraAccountValidationaRequest;
import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto.ZimraAssessmentRequest;
import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto.ZimraTransactionRequestDto;
import com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto.BillValidation;
import com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto.Root;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ConnectTimeoutException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class ZimraService {
    private final WebClient webClient;
    @Value("${connections.username}")
    private String username;
    @Value("${connections.password}")
    private String password;
    @Value("${connections.wsdl}")
    private String url;


    public String validate(ZimraAccountValidationaRequest validationaRequest) throws JsonProcessingException {
        String FRE_HEADER_XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n";
        var reference = RandomUtils.nextInt(10000, 90000);

        String requestXML = "   <soapenv:Body>\n" +
                "      <tem:SubmitTransaction>\n" +
                "         <tem:Username>" + username + "</tem:Username>\n" +
                "         <tem:Password>" + password + "</tem:Password>\n" +
                "         <tem:aSystem>Flexcube</tem:aSystem>\n" +
                "         <tem:TransactionType>50_PAY_1</tem:TransactionType>\n" +
                "         <tem:RequestXml>\n" +
                "         <![CDATA[\n" +
                "         <ZMR_ASSNO_REQ>\n" +
                "<ASSNO>"+validationaRequest.getAssNo()+"</ASSNO>"+
                "  <TIN>"+validationaRequest.getTin()+"</TIN>\n" +
                "  <CCY>"+validationaRequest.getCurrency()+"</CCY>\n" +
                "</ZMR_ASSNO_REQ>\n" +
                "         ]]>\n" +
                "\n" +
                "\t\t</tem:RequestXml>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:Reference>" + reference + "</tem:Reference>\n" +
                "      </tem:SubmitTransaction>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";


        String response = webClient.post()
                .uri(url)//url to call
                .accept(MediaType.APPLICATION_XML)
                .header("Content-Type", "text/xml") // request + response in XML
                .bodyValue(FRE_HEADER_XML + requestXML)  // header + request body in SoapEnvelope
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(body -> {
                            // Handle 5xx errors
                            return Mono.error(new RuntimeException("5xx error: " + body));
                        })
                )
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(body -> {
                            // Handle 4xx errors
                            return Mono.error(new RuntimeException("4xx error: " + body));
                        })
                )
                .onStatus(HttpStatus::isError, clientResponse ->
                        clientResponse.bodyToMono(ConnectTimeoutException.class).flatMap(body -> {
                            System.out.println("ERROR ERROR ERROR--------------");
                            // Handle other errors
                            return Mono.error(new EntityNotFoundException("Other error: " + body));
                        })
                )
                .bodyToMono(String.class) // response body
                .block(); // to retrieve object value we should block

        System.out.println(response);

        response = response.replaceAll("soap:", "");
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");

        JSONObject jsonObject = XML.toJSONObject(response);
        System.out.println(jsonObject.toString());
//        ObjectMapper om = new ObjectMapper();
//        Root root = om.readValue(jsonObject.toString(), Root.class);
//
//
//        return root.getEnvelope().getBody().getSubmitTransactionResponse().getSubmitTransactionResult().getBillValidation();
        return jsonObject.toString();
    }

    public String assessment(ZimraAssessmentRequest assessmentRequest) throws JsonProcessingException {
        String FRE_HEADER_XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n";
        var reference = RandomUtils.nextInt(10000, 90000);

        String requestXML = "   <soapenv:Body>\n" +
                "      <tem:SubmitTransaction>\n" +
                "         <tem:Username>" + username + "</tem:Username>\n" +
                "         <tem:Password>" + password + "</tem:Password>\n" +
                "         <tem:aSystem>ZIMRA</tem:aSystem>\n" +
                "         <tem:TransactionType>ZMR_ASSNO</tem:TransactionType>\n" +
                "         <tem:RequestXml>\n" +
                "         <![CDATA[\n" +
                "         <ZMR_ASSNO_REQ>\n" +
                "<ASSNO>"+assessmentRequest.getAssesmentNumber()+"</ASSNO>\n"+
                "  <TIN>"+assessmentRequest.getTin()+"</TIN>\n" +
                "  <CCY>"+assessmentRequest.getCurrency()+"</CCY>\n" +
                "</ZMR_ASSNO_REQ>\n" +
                "         ]]>\n" +
                "\n" +
                "\t\t</tem:RequestXml>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:Reference>" + reference + "</tem:Reference>\n" +
                "      </tem:SubmitTransaction>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";


        String response = webClient.post()
                .uri(url)//url to call
                .accept(MediaType.APPLICATION_XML)
                .header("Content-Type", "text/xml") // request + response in XML
                .bodyValue(FRE_HEADER_XML + requestXML)  // header + request body in SoapEnvelope
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(body -> {
                            // Handle 5xx errors
                            return Mono.error(new RuntimeException("5xx error: " + body));
                        })
                )
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(body -> {
                            // Handle 4xx errors
                            return Mono.error(new RuntimeException("4xx error: " + body));
                        })
                )
                .onStatus(HttpStatus::isError, clientResponse ->
                        clientResponse.bodyToMono(ConnectTimeoutException.class).flatMap(body -> {
                            System.out.println("ERROR ERROR ERROR--------------");
                            // Handle other errors
                            return Mono.error(new EntityNotFoundException("Other error: " + body));
                        })
                )
                .bodyToMono(String.class) // response body
                .block(); // to retrieve object value we should block

        System.out.println(response);

        response = response.replaceAll("soap:", "");
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");

        JSONObject jsonObject = XML.toJSONObject(response);
        System.out.println(jsonObject.toString());
//        ObjectMapper om = new ObjectMapper();
//        Root root = om.readValue(jsonObject.toString(), Root.class);
//
//
//        return root.getEnvelope().getBody().getSubmitTransactionResponse().getSubmitTransactionResult().getBillValidation();
        return jsonObject.toString();
    }

    public String prepaymentValidation(ZimraAccountValidationaRequest validationaRequest) throws JsonProcessingException {
        String FRE_HEADER_XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n";
        var reference = RandomUtils.nextInt(10000, 90000);

        String requestXML = "   <soapenv:Body>\n" +
                "      <tem:SubmitTransaction>\n" +
                "         <tem:Username>" + username + "</tem:Username>\n" +
                "         <tem:Password>" + password + "</tem:Password>\n" +
                "         <tem:aSystem>ZIMRA</tem:aSystem>\n" +
                "         <tem:TransactionType>ZMR_ASSNO</tem:TransactionType>\n" +
                "         <tem:RequestXml>\n" +
                "         <![CDATA[\n" +
                "         <ZMR_ASSNO_REQ>\n" +
                "<ASSNO>"+validationaRequest.getAssNo()+"</ASSNO>\n"+
                "  <TIN>"+validationaRequest.getTin()+"</TIN>\n" +
                "  <CCY>"+validationaRequest.getCurrency()+"</CCY>\n" +
                "</ZMR_ASSNO_REQ>\n" +
                "         ]]>\n" +
                "\n" +
                "\t\t</tem:RequestXml>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:Reference>" + reference + "</tem:Reference>\n" +
                "      </tem:SubmitTransaction>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";


        String response = webClient.post()
                .uri(url)//url to call
                .accept(MediaType.APPLICATION_XML)
                .header("Content-Type", "text/xml") // request + response in XML
                .bodyValue(FRE_HEADER_XML + requestXML)  // header + request body in SoapEnvelope
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(body -> {
                            // Handle 5xx errors
                            return Mono.error(new RuntimeException("5xx error: " + body));
                        })
                )
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(body -> {
                            // Handle 4xx errors
                            return Mono.error(new RuntimeException("4xx error: " + body));
                        })
                )
                .onStatus(HttpStatus::isError, clientResponse ->
                        clientResponse.bodyToMono(ConnectTimeoutException.class).flatMap(body -> {
                            System.out.println("ERROR ERROR ERROR--------------");
                            // Handle other errors
                            return Mono.error(new EntityNotFoundException("Other error: " + body));
                        })
                )
                .bodyToMono(String.class) // response body
                .block(); // to retrieve object value we should block

        System.out.println(response);

        response = response.replaceAll("soap:", "");
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");

        JSONObject jsonObject = XML.toJSONObject(response);
        System.out.println(jsonObject.toString());
//        ObjectMapper om = new ObjectMapper();
//        Root root = om.readValue(jsonObject.toString(), Root.class);
//
//
//        return root.getEnvelope().getBody().getSubmitTransactionResponse().getSubmitTransactionResult().getBillValidation();
        return jsonObject.toString();
    }

    public String accountCreationValidation(String  tin) throws JsonProcessingException {
        String FRE_HEADER_XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n";
        var reference = RandomUtils.nextInt(10000, 90000);

        String requestXML = "   <soapenv:Body>\n" +
                "      <tem:SubmitTransaction>\n" +
                "         <tem:Username>" + username + "</tem:Username>\n" +
                "         <tem:Password>" + password + "</tem:Password>\n" +
                "         <tem:aSystem>ZIMRA</tem:aSystem>\n" +
                "         <tem:TransactionType>ZMR_TIN</tem:TransactionType>\n" +
                "         <tem:RequestXml>\n" +
                "         <![CDATA[\n" +
                "         <ZMR_TIN_REQ>\n" +
                "  <TIN>1000000389</TIN> \n" +
                "</ZMR_TIN_REQ>\n" +
                "         ]]>\n" +
                "\n" +
                "\t\t</tem:RequestXml>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:Reference>" + reference + "</tem:Reference>\n" +
                "      </tem:SubmitTransaction>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";


        String response = webClient.post()
                .uri(url)//url to call
                .accept(MediaType.APPLICATION_XML)
                .header("Content-Type", "text/xml") // request + response in XML
                .bodyValue(FRE_HEADER_XML + requestXML)  // header + request body in SoapEnvelope
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(body -> {
                            // Handle 5xx errors
                            return Mono.error(new RuntimeException("5xx error: " + body));
                        })
                )
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(body -> {
                            // Handle 4xx errors
                            return Mono.error(new RuntimeException("4xx error: " + body));
                        })
                )
                .onStatus(HttpStatus::isError, clientResponse ->
                        clientResponse.bodyToMono(ConnectTimeoutException.class).flatMap(body -> {
                            System.out.println("ERROR ERROR ERROR--------------");
                            // Handle other errors
                            return Mono.error(new EntityNotFoundException("Other error: " + body));
                        })
                )
                .bodyToMono(String.class) // response body
                .block(); // to retrieve object value we should block

        System.out.println(response);

        response = response.replaceAll("soap:", "");
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");

        JSONObject jsonObject = XML.toJSONObject(response);
        System.out.println(jsonObject.toString());
//        ObjectMapper om = new ObjectMapper();
//        Root root = om.readValue(jsonObject.toString(), Root.class);
//
//
//        return root.getEnvelope().getBody().getSubmitTransactionResponse().getSubmitTransactionResult().getBillValidation();
        return jsonObject.toString();
    }


    public String createAccount(ZimraAccountCreationRequest accountCreationRequest) throws JsonProcessingException {
        String FRE_HEADER_XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n";
        var reference = RandomUtils.nextInt(10000, 90000);

        String requestXML = "   <soapenv:Body>\n" +
                "      <tem:SubmitTransaction>\n" +
                "         <tem:Username>" + username + "</tem:Username>\n" +
                "         <tem:Password>" + password + "</tem:Password>\n" +
                "         <tem:aSystem>ZIMRA</tem:aSystem>\n" +
                "         <tem:TransactionType>ZMR_CACC</tem:TransactionType>\n" +
                "         <tem:RequestXml>\n" +
                "         <![CDATA[\n" +
                "<ZMR_CACC_REQ>\n" +
                "      <TIN>"+accountCreationRequest.getTin()+"</TIN>\n" +
                "      <BANK>"+accountCreationRequest.getBank()+"</BANK>\n" +
                "      <BRANCH>"+accountCreationRequest.getBranch()+"</BRANCH>\n" +
                "      <ACCOUNTS>\n" +
                "      <ACCOUNT>\n" +
                "            <NUMBER>"+accountCreationRequest.getNumber()+"</NUMBER>\n" +
                "            <CURRENCY>"+accountCreationRequest.getCurrency()+"</CURRENCY>\n" +
                "            <STATUS>\n" +
                "                  <CODE>"+accountCreationRequest.getStatusCode()+"</CODE>\n" +
                "                  <NAME>"+accountCreationRequest.getName()+"</NAME>\n" +
                "            </STATUS>\n" +
                "      </ACCOUNT>\n" +
                "      <ACCOUNT>\n" +
                "            <NUMBER>"+accountCreationRequest.getNumber()+"</NUMBER>\n" +
                "            <CURRENCY>"+accountCreationRequest.getCurrency()+"</CURRENCY>\n" +
                "            <STATUS>\n" +
                "                  <CODE>"+accountCreationRequest.getStatusCode()+"</CODE>\n" +
                "                  <NAME>"+accountCreationRequest.getName()+"</NAME>\n" +
                "            </STATUS>\n" +
                "</ACCOUNT>\n" +
                "      </ACCOUNTS>\n" +
                "</ZMR_CACC_REQ>" +
                "         ]]>\n" +
                "\n" +
                "\t\t</tem:RequestXml>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:Reference>" + reference + "</tem:Reference>\n" +
                "      </tem:SubmitTransaction>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";


        String response = webClient.post()
                .uri(url)//url to call
                .accept(MediaType.APPLICATION_XML)
                .header("Content-Type", "text/xml") // request + response in XML
                .bodyValue(FRE_HEADER_XML + requestXML)  // header + request body in SoapEnvelope
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(body -> {
                            // Handle 5xx errors
                            return Mono.error(new RuntimeException("5xx error: " + body));
                        })
                )
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(body -> {
                            // Handle 4xx errors
                            return Mono.error(new RuntimeException("4xx error: " + body));
                        })
                )
                .onStatus(HttpStatus::isError, clientResponse ->
                        clientResponse.bodyToMono(ConnectTimeoutException.class).flatMap(body -> {
                            System.out.println("ERROR ERROR ERROR--------------");
                            // Handle other errors
                            return Mono.error(new EntityNotFoundException("Other error: " + body));
                        })
                )
                .bodyToMono(String.class).timeout(Duration.ofSeconds(60)) // response body
                .block(); // to retrieve object value we should block

        response = response.replaceAll("soap:", "");
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");
//        System.out.println(response);


        JSONObject jsonObject = XML.toJSONObject(response);
//        System.out.println(jsonObject.toString());
//        ObjectMapper om = new ObjectMapper();
//        Root root = om.readValue(jsonObject.toString(), Root.class);
//
//        System.out.println(root.getEnvelope());

//        return root.getEnvelope().getBody().getSubmitTransactionResponse().getSubmitTransactionResult().getCreatetransactionFsfsRes();
        return jsonObject.toString();
    }




    public String postTransaction(ZimraTransactionRequestDto transactionRequestDto) throws JsonProcessingException {
        String FRE_HEADER_XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n";
        var reference = RandomUtils.nextInt(10000, 90000);

        String requestXML = "   <soapenv:Body>\n" +
                "      <tem:SubmitTransaction>\n" +
                "         <tem:Username>" + username + "</tem:Username>\n" +
                "         <tem:Password>" + password + "</tem:Password>\n" +
                "         <tem:aSystem>Flexcube</tem:aSystem>\n" +
                "         <tem:TransactionType>50_PAY_1</tem:TransactionType>\n" +
                "         <tem:RequestXml>\n" +
                "         <![CDATA[\n" +
                "<CREATETRANSACTION_FSFS_REQ>\n" +
                "  <FCUBS_HEADER>\n" +
                "    <SOURCE>MOBAPP</SOURCE>\n" +
                "    <UBSCOMP>FCUBS</UBSCOMP>\n" +
                "    <USERID>CBZMOBILE</USERID>\n" +
                "    <BRANCH>033</BRANCH>\n" +
                "    <MODULEID>RT</MODULEID>\n" +
                "    <SERVICE>FCUBSRTService</SERVICE>\n" +
                "    <OPERATION>CreateTransaction</OPERATION>\n" +
                "    <FUNCTIONID>1006</FUNCTIONID>\n" +
                "    <ACTION>NEW</ACTION>\n" +
                "    <MSGSTAT>SUCCESS</MSGSTAT>\n" +
                "  </FCUBS_HEADER>\n" +
                "  <FCUBS_BODY>\n" +
                "    <Transaction-Details>\n" +
                "      <XREF>"+transactionRequestDto.getXRef()+"</XREF>\n"+
                "      <FCCREF/>\n" +
                "      <MODULE/>\n" +
                "      <PRD>FBZI</PRD>\n" +
                "      <BRN>"+transactionRequestDto.getBrn()+"</BRN>\n" +
                "      <TXNBRN>"+transactionRequestDto.getTxnBrn()+"</TXNBRN>\n" +
                "      <TXNACC>"+transactionRequestDto.getTxnAcc()+"</TXNACC>\n" +
                "      <TXNCCY>"+transactionRequestDto.getTxnCcy()+"</TXNCCY>\n" +
                "      <TXNAMT>"+transactionRequestDto.getTxnAmt()+"</TXNAMT>\n" +
                "      <TXNTRN />\n" +
                "      <SCODE>MOBAPP</SCODE>\n" +
                "      <OFFSETBRN>"+transactionRequestDto.getOffSetBrn()+"</OFFSETBRN>\n" +
                "      <OFFSETACC>"+transactionRequestDto.getOffsetAcc()+"</OFFSETACC>\n" +
                "      <OFFSETCCY>"+transactionRequestDto.getOffsetCcy()+"</OFFSETCCY>\n" +
                "      <OFFSETAMT>"+transactionRequestDto.getOffsetAmt()+"</OFFSETAMT>\n" +
                "      <OFFSETTRN />\n" +
                "      <XRATE>1</XRATE>\n" +
                "      <LCYAMT>0.00</LCYAMT>\n" +
                "      <TXNDATE>"+transactionRequestDto.getTxnDate()+"</TXNDATE>\n" +
                "      <INSTRDATE />\n" +
                "      <INSTRCD />\n" +
                "      <CRINSTCD />\n" +
                "      <DRINSTCD />\n" +
                "      <RELCUST />\n" +
                "      <MISHEAD />\n" +
                "      <REMACCOUNT />\n" +
                "      <VALDATE>"+transactionRequestDto.getValDate()+"</VALDATE>\n" +
                "      <REMBANK />\n" +
                "      <REMBRANCH />\n" +
                "      <ROUTINGNO />\n" +
                "      <ENDPOINT />\n" +
                "      <SERIALNO />\n" +
                "      <ROUTECODE />\n" +
                "      <MAKERID />\n" +
                "      <MAKERSTAMP />\n" +
                "      <CHECKERID />\n" +
                "      <CHECKERSTAMP />\n" +
                "      <RECSTAT />\n" +
                "      <AUTHSTAT />\n" +
                "      <REPREASON />\n" +
                "      <MODNO>0</MODNO>\n" +
                "      <ESN>0</ESN>\n" +
                "      <EVNTCD />\n" +
                "      <TIMERECV />\n" +
                "      <THEIRCHGS />\n" +
                "      <THEIRCHGS1 />\n" +
                "      <THEIRCHGS2 />\n" +
                "      <THEIRCHGS3 />\n" +
                "      <THEIRCHGS4 />\n" +
                "      <THEIRACC />\n" +
                "      <THEIRACC1 />\n" +
                "      <THEIRACC2 />\n" +
                "      <THEIRACC3 />\n" +
                "      <THEIRACC4 />\n" +
                "      <BENFNAME />\n" +
                "      <BENFADDR1>"+transactionRequestDto.getBenfAddr1()+"</BENFADDR1>\n" +
                "      <BENFADDR2>"+transactionRequestDto.getBenfAddr2()+"</BENFADDR2>\n" +
                "      <BENFADDR3>//DUTY</BENFADDR3>\n" +
                "      <BENFADDR4>"+transactionRequestDto.getBenfAddr4()+"</BENFADDR4>\n" +
                "      <DOCNUMBER>//0000000_0</DOCNUMBER>\n" +
                "      <MOBILE_NUMBER>"+transactionRequestDto.getMobileNumber()+"</MOBILE_NUMBER>\n" +
                "      <EMAIL_ID>"+transactionRequestDto.getEmail()+"</EMAIL_ID>\n" +
                "      <Message-Details>\n" +
                "        <INSTRCD />\n" +
                "        <BENFADDR1>//ZWKR</BENFADDR1>\n" +
                "        <BENFADDR2>//200043464</BENFADDR2>\n" +
                "        <BENFADDR3>//DUTY</BENFADDR3>\n" +
                "        <BENFADDR4>//Bozmic</BENFADDR4>\n" +
                "      </Message-Details>\n" +
                "      <Charge-Details>\n" +
                "        <CHGCOMP>TRANSFER FEE</CHGCOMP>\n" +
                "        <WAIVER>N</WAIVER>\n" +
                "        <CHGAMT>1590.00</CHGAMT>\n" +
                "        <CHGCCY>ZiG</CHGCCY>\n" +
                "        <TXNCODE>CHG</TXNCODE>\n" +
                "      </Charge-Details>\n" +
                "      <Charge-Details>\n" +
                "        <CHGCOMP>TAX</CHGCOMP>\n" +
                "        <WAIVER>N</WAIVER>\n" +
                "        <CHGAMT>"+transactionRequestDto.getChgAmt()+"</CHGAMT>\n" +
                "        <CHGCCY>"+transactionRequestDto.getChgCcy()+"</CHGCCY>\n" +
                "        <TXNCODE>IMT</TXNCODE>\n" +
                "      </Charge-Details>\n" +
                "      <FT-Details>\n" +
                "        <ULTBENDET1>2238774</ULTBENDET1>\n" +
                "        <ULTBENDET2>CBZ BANK</ULTBENDET2>\n" +
                "        <ULTBENDET3>Bozmic</ULTBENDET3>\n" +
                "        <ULTBENDET4>0000000_0</ULTBENDET4>\n" +
                "        <ULTBENDET5>"+transactionRequestDto.getUltBendet5()+"</ULTBENDET5>\n" +
                "        <ULTBENDET6 />\n" +
                "      </FT-Details>\n" +
                "    </Transaction-Details>\n" +
                "  </FCUBS_BODY>\n" +
                "</CREATETRANSACTION_FSFS_REQ>" +
                "         ]]>\n" +
                "\n" +
                "</tem:RequestXml>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:Reference>" + reference + "</tem:Reference>\n" +
                "      </tem:SubmitTransaction>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";


        String response = webClient.post()
                .uri(url)//url to call
                .accept(MediaType.APPLICATION_XML)
                .header("Content-Type", "text/xml") // request + response in XML
                .bodyValue(FRE_HEADER_XML + requestXML)  // header + request body in SoapEnvelope
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(body -> {
                            // Handle 5xx errors
                            return Mono.error(new RuntimeException("5xx error: " + body));
                        })
                )
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        clientResponse.bodyToMono(String.class).flatMap(body -> {
                            // Handle 4xx errors
                            return Mono.error(new RuntimeException("4xx error: " + body));
                        })
                )
                .onStatus(HttpStatus::isError, clientResponse ->
                        clientResponse.bodyToMono(ConnectTimeoutException.class).flatMap(body -> {
                            System.out.println("ERROR ERROR ERROR--------------");
                            // Handle other errors
                            return Mono.error(new EntityNotFoundException("Other error: " + body));
                        })
                )
                .bodyToMono(String.class) // response body
                .block(); // to retrieve object value we should block

        response = response.replaceAll("soap:", "");
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");
//        System.out.println(response);


        JSONObject jsonObject = XML.toJSONObject(response);
//        System.out.println(jsonObject.toString());
//        ObjectMapper om = new ObjectMapper();
//        Root root = om.readValue(jsonObject.toString(), Root.class);
//
//        System.out.println(root.getEnvelope());

//        return root.getEnvelope().getBody().getSubmitTransactionResponse().getSubmitTransactionResult().getCreatetransactionFsfsRes();
        return jsonObject.toString();
    }

}
