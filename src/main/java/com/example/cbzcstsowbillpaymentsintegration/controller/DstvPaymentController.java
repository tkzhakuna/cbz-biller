package com.example.cbzcstsowbillpaymentsintegration.controller;

import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.dstvrequest.DstvRequest;
import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto.StudentRequestDto;
import com.example.cbzcstsowbillpaymentsintegration.service.DstvPaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/dstv")

public class DstvPaymentController {
    private final DstvPaymentService dstvPaymentService;
    @PostMapping( value = "/pay-dstv",produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> payDstv(@RequestBody DstvRequest dstvRequest) throws JsonProcessingException {
        log.info("Rest request to pay Dstv {}",dstvRequest);

        return new ResponseEntity<>(dstvPaymentService.payDstv(dstvRequest), HttpStatus.OK);
    }
}
