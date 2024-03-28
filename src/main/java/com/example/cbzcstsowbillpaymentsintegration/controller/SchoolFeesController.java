package com.example.cbzcstsowbillpaymentsintegration.controller;


import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto.StudentRequestDto;
import com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto.StudentTransactionRequestDTO;
import com.example.cbzcstsowbillpaymentsintegration.service.SchoolFeesService;
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
@RequestMapping("/api/fees")
public class SchoolFeesController {

    private final SchoolFeesService schoolFeesService;

    @GetMapping( value="/student-information/{accountNumber}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getStudentDetails(@PathVariable String accountNumber) throws JsonProcessingException {
        log.info("Rest request to find student information for {}",accountNumber);

            return new ResponseEntity<>(schoolFeesService.studentValidation(accountNumber), HttpStatus.OK);
        }

    @PostMapping( value = "/pay-fees",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> postTransaction(@RequestBody StudentRequestDto studentTransactionRequestDTO) throws JsonProcessingException {
        log.info("Rest request to find student information for {}",studentTransactionRequestDTO);

        return new ResponseEntity<>(schoolFeesService.payFees(studentTransactionRequestDTO), HttpStatus.OK);
    }

}
