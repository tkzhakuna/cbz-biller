package com.example.cbzcstsowbillpaymentsintegration.controller;


import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.billersrequestdto.ZesaRequestDTO;
import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zesarequest.PurchaseTokenRequest;
import com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zesaresponsedto.MeterValidation;
import com.example.cbzcstsowbillpaymentsintegration.service.ZesaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ZesaController {
    private final ZesaService zesaService;

    @PostMapping( value="/zesa-meter-validation",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> validateMeter(@RequestBody ZesaRequestDTO zesaRequestDTO) throws JAXBException, IOException {
        log.info("Rest request to find zesa information {}",zesaRequestDTO);


        return new ResponseEntity<>(zesaService.findDetails(zesaRequestDTO), HttpStatus.OK);
    }

    @PostMapping( value = "/purchase-token",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> postTransaction(@RequestBody PurchaseTokenRequest purchaseTokenRequest) throws JsonProcessingException {
        log.info("Rest request to purchase Zesa token for {}",purchaseTokenRequest);

        return new ResponseEntity<>(zesaService.purchaseToken(purchaseTokenRequest), HttpStatus.OK);
    }
}
