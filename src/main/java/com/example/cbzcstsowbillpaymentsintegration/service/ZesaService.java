package com.example.cbzcstsowbillpaymentsintegration.service;

import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.billersrequestdto.ZesaRequestDTO;
import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zesarequest.PurchaseTokenRequest;
import com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zesaresponsedto.MeterValidation;
import com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zesaresponsedto.ZesaRoot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ConnectTimeoutException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import javax.persistence.EntityNotFoundException;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.XML;
import org.json.JSONObject;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ZesaService {
    private final WebClient webClient;
    @Value("${connections.username}")
    private String username;
    @Value("${connections.password}")
    private String password;
    @Value("${connections.wsdl}")
    private String url;


    public String findDetails(ZesaRequestDTO zesaRequestDTO) throws JAXBException, IOException {
        String FRE_HEADER_XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n";
        var reference = RandomUtils.nextInt(10000, 90000);

        String requestXML = "   <soapenv:Body>\n" +
                "      <tem:SubmitTransaction>\n" +
                "         <tem:Username>" + username + "</tem:Username>\n" +
                "         <tem:Password>" + password + "</tem:Password>\n" +
                "         <tem:aSystem>Flexcube</tem:aSystem>\n" +
                "         <tem:TransactionType>310000</tem:TransactionType>\n" +
                "         <tem:RequestXml>\n" +
                "         <![CDATA[\n" +
                "         <METER_VALIDATION>\n" +
                "        <METER_NUMBER>" + zesaRequestDTO.getMeterNumber() + "</METER_NUMBER>\n" +
                "        <REFERENCE_NUMBER>" + (reference) + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss")) + "</REFERENCE_NUMBER>\n" +
                "        <TERMINAL_ID>CBZMOBILE</TERMINAL_ID>\n" +
                "         </METER_VALIDATION>\n" +
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

        response=response.replaceAll("soap:","");
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");

        JSONObject jsonObject = XML.toJSONObject(response);
//        System.out.println(jsonObject.toString());
//        ObjectMapper om = new ObjectMapper();
//        ZesaRoot zesaRoot = om.readValue(jsonObject.toString(), ZesaRoot.class);


        return jsonObject.toString(); //zesaRoot.getZesaEnvelope().getZesaBody().getZesaSubmitTransactionResponse().getZesaSubmitTransactionResult().getMeterValidation();

    }


    public String purchaseToken(PurchaseTokenRequest purchaseTokenRequest) throws JsonProcessingException {
        String FRE_HEADER_XML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n";
        var reference = RandomUtils.nextInt(10000, 90000);

        String requestXML = "<soapenv:Body>\n" +
                "      <tem:SubmitTransaction>\n" +
                "         <tem:Username>" + username + "</tem:Username>\n" +
                "         <tem:Password>" + password + "</tem:Password>\n" +
                "         <tem:aSystem>Flexcube</tem:aSystem>\n" +
                "         <tem:TransactionType>360000</tem:TransactionType>\n" +
                "         <tem:RequestXml>\n" +
                "         <![CDATA[\n" +
                "<TOKEN_ENQUIRY>\n" +
                "  <METER_NUMBER>"+purchaseTokenRequest.getMeterNumber()+"</METER_NUMBER>\n" +
                "  <REFERENCE_NUMBER>"+ (reference) + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss")) + "</REFERENCE_NUMBER>\n" +
                "  <TERMINAL_ID>CBZMOBILE</TERMINAL_ID>\n" +
                "  <AMOUNT>"+purchaseTokenRequest.getAmount()+"</AMOUNT>\n" +
                "</TOKEN_ENQUIRY>"+
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
//        System.out.println(jsonObject.toString());
//        ObjectMapper om = new ObjectMapper();
        // Root root = om.readValue(jsonObject.toString(), Root.class);

        // System.out.println(root.getEnvelope());

//        return root.getEnvelope().getBody().getSubmitTransactionResponse().getSubmitTransactionResult().getCreatetransactionFsfsRes();
        return jsonObject.toString();
    }


}
