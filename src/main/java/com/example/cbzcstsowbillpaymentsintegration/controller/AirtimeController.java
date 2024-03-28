package com.example.cbzcstsowbillpaymentsintegration.controller;


import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.airtimerequest.AirtimeRequest;
import com.example.cbzcstsowbillpaymentsintegration.service.AirtimeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/airtime")
public class AirtimeController {

    private final AirtimeService airtimeService;


    @PostMapping( value = "/buy-airtime",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> buyAirtime(@RequestBody AirtimeRequest airtimeRequest) throws JsonProcessingException {
        log.info("Rest request to purchase airtime for {}",airtimeRequest);

        return new ResponseEntity<>(airtimeService.purchaseAirtime(airtimeRequest), HttpStatus.OK);
    }

    @PostMapping( value = "/buy-usd-airtime",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> buyUSDAirtime(@RequestBody AirtimeRequest airtimeRequest) throws JsonProcessingException {
        log.info("Rest request to purchase airtime for {}",airtimeRequest);

        return new ResponseEntity<>(airtimeService.purchaseUsdAirtime(airtimeRequest), HttpStatus.OK);
    }

}
