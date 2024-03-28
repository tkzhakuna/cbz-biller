package com.example.cbzcstsowbillpaymentsintegration.service;

import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto.StudentRequestDto;
import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto.StudentTransactionRequestDTO;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchoolFeesService {
    private final WebClient webClient;
    @Value("${connections.username}")
    private String username;
    @Value("${connections.password}")
    private String password;
    @Value("${connections.wsdl}")
    private String url;


    public String studentValidation(String regNumber) throws JsonProcessingException {
        String FRE_HEADER_XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n";
        var reference = RandomUtils.nextInt(10000, 90000);

        String requestXML = "   <soapenv:Body>\n" +
                "      <tem:SubmitTransaction>\n" +
                "         <tem:Username>" + username + "</tem:Username>\n" +
                "         <tem:Password>" + password + "</tem:Password>\n" +
                "         <tem:aSystem>Flexcube</tem:aSystem>\n" +
                "         <tem:TransactionType>107_SCV_1</tem:TransactionType>\n" +
                "         <tem:RequestXml>\n" +
                "         <![CDATA[\n" +
                "         <SCHOOL_VALIDATION>\n" +
                "  <ACCOUNT_NUMBER>20492620020</ACCOUNT_NUMBER>\n" +
                "         </SCHOOL_VALIDATION>\n" +
                "         ]]>\n" +
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
        ObjectMapper om = new ObjectMapper();
//        Root root = om.readValue(jsonObject.toString(), Root.class);


       // return root.getEnvelope().getBody().getSubmitTransactionResponse().getSubmitTransactionResult().getBillValidation();
return jsonObject.toString();
    }


    public String payFees(StudentRequestDto studentTransactionRequestDTO) throws JsonProcessingException {
        String FRE_HEADER_XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n";
        var reference = RandomUtils.nextInt(10000, 90000);

        String requestXML = "<soapenv:Body>\n" +
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
                "    <BRANCH>"+studentTransactionRequestDTO.getTransactionBranch()+"</BRANCH>\n" +
                "    <MODULEID>RT</MODULEID>\n" +
                "    <SERVICE>FCUBSRTService</SERVICE>\n" +
                "    <OPERATION>CreateTransaction</OPERATION>\n" +
                "    <FUNCTIONID>1006</FUNCTIONID>\n" +
                "    <ACTION>NEW</ACTION>\n" +
                "    <MSGSTAT>SUCCESS</MSGSTAT>\n" +
                "  </FCUBS_HEADER>\n" +
                "  <FCUBS_BODY>\n" +
                "    <Transaction-Details>\n" +
                "      <XREF>"+ (reference) + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss")) +"</XREF>\n" +
                "      <FCCREF />\n" +
                "      <MODULE />\n" +
                "      <PRD>FBPM</PRD>\n" +
                "      <BRN>"+studentTransactionRequestDTO.getCustomerBranch()+"</BRN>\n" +
                "      <TXNBRN>"+studentTransactionRequestDTO.getCustomerBranch()+"</TXNBRN>\n" +
                "      <TXNACC>"+studentTransactionRequestDTO.getCustomerAccount()+"</TXNACC>\n" +
                "      <TXNCCY>"+studentTransactionRequestDTO.getCurrency()+"</TXNCCY>\n" +
                "      <TXNAMT>"+studentTransactionRequestDTO.getTransactionAmount()+"</TXNAMT>\n" +
                "      <TXNTRN/>\n" +
                "      <SCODE>MOBAPP</SCODE>\n" +
                "      <OFFSETBRN>"+studentTransactionRequestDTO.getBillerAccountBranch()+"</OFFSETBRN>\n" +
                "      <OFFSETACC>"+studentTransactionRequestDTO.getBillerAccount()+"</OFFSETACC>\n" +
                "      <OFFSETCCY>"+studentTransactionRequestDTO.getCurrency()+"</OFFSETCCY>\n" +
                "      <OFFSETAMT>"+studentTransactionRequestDTO.getTransactionAmount()+"</OFFSETAMT>\n" +
                "      <OFFSETTRN/>\n" +
                "      <XRATE>1</XRATE>\n" +
                "      <LCYAMT>0.00</LCYAMT>\n" +
                "      <TXNDATE>"+ LocalDate.now() +"</TXNDATE>\n" +
                "      <INSTRDATE/>\n" +
                "      <INSTRCD/>\n" +
                "      <CRINSTCD/>\n" +
                "      <DRINSTCD/>\n" +
                "      <RELCUST/>\n" +
                "      <MISHEAD/>\n" +
                "      <REMACCOUNT/>\n" +
                "      <VALDATE>"+LocalDate.now() +"</VALDATE>\n" +
                "      <REMBANK/>\n" +
                "      <REMBRANCH/>\n" +
                "      <ROUTINGNO/>\n" +
                "      <ENDPOINT/>\n" +
                "      <SERIALNO/>\n" +
                "      <ROUTECODE/>\n" +
                "      <MAKERID/>\n" +
                "      <MAKERSTAMP/>\n" +
                "      <CHECKERID/>\n" +
                "      <CHECKERSTAMP/>\n" +
                "      <RECSTAT/>\n" +
                "      <AUTHSTAT/>\n" +
                "      <REPREASON/>\n" +
                "      <MODNO>0</MODNO>\n" +
                "      <ESN>0</ESN>\n" +
                "      <EVNTCD/>\n" +
                "      <TIMERECV/>\n" +
                "      <THEIRCHGS/>\n" +
                "      <THEIRCHGS1/>\n" +
                "      <THEIRCHGS2/>\n" +
                "      <THEIRCHGS3/>\n" +
                "      <THEIRCHGS4/>\n" +
                "      <THEIRACC/>\n" +
                "      <THEIRACC1/>\n" +
                "      <THEIRACC2/>\n" +
                "      <THEIRACC3/>\n" +
                "      <THEIRACC4/>\n" +
                "      <BENFNAME/>\n" +
                "      <BENFADDR1>"+studentTransactionRequestDTO.getPaymentReason()+"</BENFADDR1>\n" +
                "      <BENFADDR2>"+LocalDate.now() .getYear()+"</BENFADDR2>\n" +
                "      <BENFADDR3>"+studentTransactionRequestDTO.getTerm()+"</BENFADDR3>\n" +
                "      <BENFADDR4>"+studentTransactionRequestDTO.getSchoolClass()+"</BENFADDR4>\n" +
                "      <DOCNUMBER> "+studentTransactionRequestDTO.getStudentName()+"</DOCNUMBER>\n" +
                "      <MOBILE_NUMBER>"+studentTransactionRequestDTO.getMobileNumber()+"</MOBILE_NUMBER>\n" +
                "      <EMAIL_ID>"+studentTransactionRequestDTO.getEmail()+"</EMAIL_ID>\n" +
                "      <Message-Details>\n" +
                "        <INSTRCD/>\n" +
                "        <BENFADDR1>"+studentTransactionRequestDTO.getPaymentReason()+"</BENFADDR1>\n" +
                "        <BENFADDR2>"+ LocalDate.now() .getYear()+"</BENFADDR2>\n" +
                "        <BENFADDR3>"+studentTransactionRequestDTO.getTerm()+"</BENFADDR3>\n" +
                "        <BENFADDR4>"+studentTransactionRequestDTO.getSchoolClass()+"</BENFADDR4>\n" +
                "      </Message-Details>\n" +
                "      <Charge-Details>\n" +
                "        <CHGCOMP>TRANSFER FEE</CHGCOMP>\n" +
                "        <WAIVER>N</WAIVER>\n" +
                "        <CHGAMT>"+studentTransactionRequestDTO.getChargeAmount()+"</CHGAMT>\n" +
                "        <CHGCCY>"+studentTransactionRequestDTO.getCurrency()+"</CHGCCY>\n" +
                "        <TXNCODE>CHG</TXNCODE>\n" +
                "      </Charge-Details>\n" +
                "      <Charge-Details>\n" +
                "        <CHGCOMP>TAX</CHGCOMP>\n" +
                "        <WAIVER>"+studentTransactionRequestDTO.getWaiver()+"</WAIVER>\n" +
                "        <CHGAMT>"+studentTransactionRequestDTO.getChargeAmount()+"</CHGAMT>\n" +
                "        <CHGCCY>"+studentTransactionRequestDTO.getCurrency()+"</CHGCCY>\n" +
                "        <TXNCODE>IMT</TXNCODE>\n" +
                "      </Charge-Details>\n" +
                "      <FT-Details>\n" +
                "        <ULTBENDET1>"+studentTransactionRequestDTO.getPaymentReason()+"</ULTBENDET1>\n" +
                "        <ULTBENDET2>CBZ BANK</ULTBENDET2>\n" +
                "        <ULTBENDET3>"+studentTransactionRequestDTO.getPaymentReason()+"</ULTBENDET3>\n" +
                "        <ULTBENDET4>"+studentTransactionRequestDTO.getStudentName()+"</ULTBENDET4>\n" +
                "        <ULTBENDET5>"+studentTransactionRequestDTO.getEmail()+"</ULTBENDET5>\n" +
                "        <ULTBENDET6 />\n" +
                "      </FT-Details>\n" +
                "    </Transaction-Details>\n" +
                "  </FCUBS_BODY>\n" +
                "</CREATETRANSACTION_FSFS_REQ>"+
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

        response = response.replaceAll("soap:", "");
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");
//        System.out.println(response);


        JSONObject jsonObject = XML.toJSONObject(response);
//       System.out.println(jsonObject.toString());
//        ObjectMapper om = new ObjectMapper();
       // Root root = om.readValue(jsonObject.toString(), Root.class);

       // System.out.println(root.getEnvelope());

//        return root.getEnvelope().getBody().getSubmitTransactionResponse().getSubmitTransactionResult().getCreatetransactionFsfsRes();
return jsonObject.toString();
    }


}
