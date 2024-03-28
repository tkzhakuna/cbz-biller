package com.example.cbzcstsowbillpaymentsintegration.controller;


import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.billersrequestdto.BillerRequestDto;
import com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto.BillValidation;
import com.example.cbzcstsowbillpaymentsintegration.service.BillPaymentService;
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
@RequestMapping("/api/bills")
public class BillController {

    private final BillPaymentService billPaymentService;

    @GetMapping( "/biller-information/{accountNumber}")
    public ResponseEntity<BillValidation> getBillDetails(@PathVariable String accountNumber) throws JsonProcessingException {
        log.info("Rest request to find biller information for {}",accountNumber);

            return new ResponseEntity<>(billPaymentService.billValidation(accountNumber), HttpStatus.OK);
        }

    @PostMapping( value = "/post-transaction",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> postTransaction(@RequestBody BillerRequestDto billerRequestDto) throws JsonProcessingException {
        log.info("Rest request to find student information for {}",billerRequestDto);

        return new ResponseEntity<>(billPaymentService.postTransaction(billerRequestDto), HttpStatus.OK);
    }

}
