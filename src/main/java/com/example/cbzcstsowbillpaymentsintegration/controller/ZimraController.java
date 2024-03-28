package com.example.cbzcstsowbillpaymentsintegration.controller;


import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto.ZimraAccountCreationRequest;
import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto.ZimraAccountValidationaRequest;
import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto.ZimraAssessmentRequest;
import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto.ZimraTransactionRequestDto;
import com.example.cbzcstsowbillpaymentsintegration.service.ZimraService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ZimraController {
    private final ZimraService zimraService;

    @PostMapping(path= "/validate",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> validateDetails(@RequestBody ZimraAccountValidationaRequest validationaRequest) throws JsonProcessingException {
        log.info("Rest request to find  information for {}",validationaRequest);

        return new ResponseEntity<>(zimraService.validate(validationaRequest), HttpStatus.OK);
    }

    @PostMapping(path= "/assessment",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> assessment(@RequestBody ZimraAssessmentRequest assessmentRequest) throws JsonProcessingException {
        log.info("Rest request to find  information for {}",assessmentRequest);

        return new ResponseEntity<>(zimraService.assessment(assessmentRequest), HttpStatus.OK);
    }

    @PostMapping(path= "/prepayment-validation",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> prepaymentValidation(@RequestBody ZimraAccountValidationaRequest prepaymentValidation) throws JsonProcessingException {
        log.info("Rest request to find prepayment  information for {}",prepaymentValidation);

        return new ResponseEntity<>(zimraService.prepaymentValidation(prepaymentValidation), HttpStatus.OK);
    }

    @PostMapping(path= "/account-creation",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> accountCreationValidation(@RequestBody ZimraAccountCreationRequest accountCreationRequest) throws JsonProcessingException {
        log.info("Rest request for account creation for {}",accountCreationRequest);

        return new ResponseEntity<>(zimraService.createAccount(accountCreationRequest), HttpStatus.OK);
    }

    @PostMapping( value = "/post-transaction",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> postTransaction(@RequestBody ZimraTransactionRequestDto transactionRequestDto) throws JsonProcessingException {
        log.info("Rest request to post transaction for {}",transactionRequestDto);

        return new ResponseEntity<>(zimraService.postTransaction(transactionRequestDto), HttpStatus.OK);
    }

//    @PostMapping( value = "/post-rtgs-transaction",produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<String> postRtgsTransaction(@RequestBody ZimraRtgsTransactionRequestDto transactionRequestDto) throws JsonProcessingException {
//        log.info("Rest request to post rtgs transaction for {}",transactionRequestDto);
//
//        return new ResponseEntity<>(zimraService.postRtgsTransaction(transactionRequestDto), HttpStatus.OK);
//    }
//
//    @GetMapping( value = "/tax-codes-request",produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<String> taxCodesRequest() throws JsonProcessingException {
//        log.info("Rest request to request Tax Codes");
//
//        return new ResponseEntity<>(zimraService.taxCodesRequest(), HttpStatus.OK);
//    }
//    @GetMapping( value = "/office-codes-request",produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<String> officeCodesRequest() throws JsonProcessingException {
//        log.info("Rest request to request Office Codes");
//
//        return new ResponseEntity<>(zimraService.officeCodesRequest(), HttpStatus.OK);
//    }
//    @PostMapping( value = "/tax-codes-selection",produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<String> selectTaxCodes(@RequestBody TaxCodesRequest taxCodesRequest) throws JsonProcessingException {
//        log.info("Rest request to select Tax Codes {}",taxCodesRequest);
//
//        return new ResponseEntity<>(zimraService.selectTaxCodes(taxCodesRequest), HttpStatus.OK);
//    }
}
