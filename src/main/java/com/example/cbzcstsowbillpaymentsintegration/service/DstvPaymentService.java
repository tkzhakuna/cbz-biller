package com.example.cbzcstsowbillpaymentsintegration.service;

import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.dstvrequest.DstvRequest;
import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zesarequest.PurchaseTokenRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ConnectTimeoutException;
import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class DstvPaymentService {
    private final WebClient webClient;
    @Value("${connections.username}")
    private String username;
    @Value("${connections.password}")
    private String password;
    @Value("${connections.wsdl}")
    private String url;
    public String payDstv( DstvRequest dstvRequest) throws JsonProcessingException {
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
                "   <CREATETRANSACTION_FSFS_REQ>\n" +
        "  <FCUBS_HEADER>\n" +
        " <SOURCE>MOBAPP</SOURCE>\n" +
        " <UBSCOMP>FCUBS</UBSCOMP>\n" +
        " <USERID>CBZMOBILE</USERID>\n" +
        " <BRANCH>023</BRANCH>\n" +
        " <MODULEID>RT</MODULEID>\n" +
        " <SERVICE>FCUBSRTService</SERVICE>\n" +
        "  <OPERATION>CreateTransaction</OPERATION>\n" +
                "  <FUNCTIONID>1006</FUNCTIONID>\n" +
        "  <ACTION>NEW</ACTION>\n" +
        " <MSGSTAT>SUCCESS</MSGSTAT>\n" +
        "  </FCUBS_HEADER>\n" +
        " <FCUBS_BODY>\n" +
        "  <Transaction-Details>\n" +
        "   <XREF>"+reference+"</XREF>\n" +
        "  <FCCREF />\n" +
        "   <MODULE />\n" +
        "  <PRD>FBDS</PRD>\n" +
        "  <BRN>023</BRN>\n" +
        " <TXNBRN>023</TXNBRN>\n" +
        "  <TXNACC>"+dstvRequest.getTransactionAccount()+"</TXNACC>\n" +
        "    <TXNCCY>"+dstvRequest.getTransactionCurrency()+"</TXNCCY>\n" +
        "  <TXNAMT>"+dstvRequest.getTransactionAmount()+"</TXNAMT>\n" +
        "  <TXNTRN />\n" +
        " <SCODE>FCAT</SCODE>\n" +
        " <OFFSETBRN>055</OFFSETBRN>\n" +
        "  <OFFSETACC>240400570</OFFSETACC>\n" +
        "  <OFFSETCCY>"+dstvRequest.getTransactionCurrency()+"</OFFSETCCY>\n" +
        "  <OFFSETAMT>"+dstvRequest.getTransactionAmount()+"</OFFSETAMT>\n" +
        " <OFFSETTRN />\n" +
        " <XRATE />\n" +
        " <LCYAMT>0.00</LCYAMT>\n" +
        "   <TXNDATE>"+ LocalDate.now() +"</TXNDATE>\n" +
        "   <INSTRDATE />\n" +
        "  <INSTRCD />\n" +
        " <CRINSTCD />\n" +
        "  <DRINSTCD />\n" +
        "     <RELCUST />\n" +
        "    <MISHEAD />\n" +
                "     <REMACCOUNT />\n" +
        "  <VALDATE>"+LocalDate.now()+"</VALDATE>\n" +
        "  <REMBANK />\n" +
        "    <REMBRANCH />\n" +
                "   <ROUTINGNO />\n" +
        "  <ENDPOINT />\n" +
        "  <SERIALNO />\n" +
        "  <ROUTECODE />\n" +
        "  <MAKERID />\n" +
        "  <MAKERSTAMP />\n" +
        "  <CHECKERID />\n" +
        "  <CHECKERSTAMP />\n" +
        "    <RECSTAT />\n" +
        "    <AUTHSTAT />\n" +
        "   <REPREASON />\n" +
                "   <MODNO>0</MODNO>\n" +
        "   <ESN>0</ESN>\n" +
        "   <EVNTCD />\n" +
                " <TIMERECV />\n" +
        " <THEIRCHGS />\n" +
        "  <THEIRCHGS1 />\n" +
        "  <THEIRCHGS2 />\n" +
        "  <THEIRCHGS3 />\n" +
        "  <THEIRCHGS4 />\n" +
        "  <THEIRACC />\n" +
        "  <THEIRACC1 />\n" +
        "  <THEIRACC2 />\n" +
        "  <THEIRACC3 />\n" +
        "  <THEIRACC4 />\n" +
        "  <BENFNAME />\n" +
        " <BENFADDR1>"+dstvRequest.getBeneficaryAddress1()+"</BENFADDR1>\n" +
        " <BENFADDR2>"+dstvRequest.getTransactionAccount()+"</BENFADDR2>\n" +
        " <BENFADDR3>"+dstvRequest.getMobileNumber()+"</BENFADDR3>\n" +
        " <BENFADDR4>"+dstvRequest.getTransactionAmount()+"</BENFADDR4>\n" +
        " <MOBILE_NUMBER>"+dstvRequest.getMobileNumber()+"</MOBILE_NUMBER>\n" +
        " <EMAIL_ID>"+dstvRequest.getEmail()+"</EMAIL_ID>\n" +
        "  <Message-Details>\n" +
        "   <INSTRCD />\n" +
        "  <BENFADDR1>"+dstvRequest.getBeneficaryAddress1()+"</BENFADDR1>\n" +
        "  <BENFADDR2>"+dstvRequest.getTransactionAccount()+"</BENFADDR2>\n" +
        "  <BENFADDR3>"+dstvRequest.getMobileNumber()+"</BENFADDR3>\n" +
        "   <BENFADDR4>"+dstvRequest.getTransactionAmount()+"</BENFADDR4>\n" +
        "  </Message-Details>\n" +
        " <Charge-Details>\n" +
        "  <CHGCOMP>TRANSFER FEE</CHGCOMP>\n" +
        "  <WAIVER>N</WAIVER>\n" +
        "  <CHGAMT>3.00</CHGAMT>\n" +
        "   <CHGCCY>USD</CHGCCY>\n" +
        "   <TXNCODE>CHG</TXNCODE>\n" +
        "  </Charge-Details>\n" +
        "  <Charge-Details>\n" +
        "   <CHGCOMP>TAX</CHGCOMP>\n" +
        "  <WAIVER>N</WAIVER>\n" +
        "  <CHGAMT>000.29</CHGAMT>\n" +
        "  <CHGCCY>USD</CHGCCY>\n" +
        "  <TXNCODE>IMT</TXNCODE>\n" +
        "  </Charge-Details>\n" +
        "   <FT-Details>\n" +
        "      <ULTBENDET1>DSTV2335169</ULTBENDET1>\n" +
        "     <ULTBENDET2>CBZ BANK</ULTBENDET2>\n" +
        "      <ULTBENDET3 />\n" +
        "     <ULTBENDET4>Claudius Marufu </ULTBENDET4>\n" +
        "     <ULTBENDET5>2420311@cbz.co.zw</ULTBENDET5>\n" +
        "    <ULTBENDET6 />\n" +
        "  </FT-Details>\n" +
        " </Transaction-Details>\n" +
        "  </FCUBS_BODY>\n" +
        " </CREATETRANSACTION_FSFS_REQ>]]>\n" +
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
//        System.out.println(jsonObject.toString());
//        ObjectMapper om = new ObjectMapper();
        // Root root = om.readValue(jsonObject.toString(), Root.class);

        // System.out.println(root.getEnvelope());

//        return root.getEnvelope().getBody().getSubmitTransactionResponse().getSubmitTransactionResult().getCreatetransactionFsfsRes();
        return jsonObject.toString();
    }


}
