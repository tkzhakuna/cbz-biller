package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class ZimraTransactionRequestDto {

    private String xRef;
    private String brn;
    private String txnBrn;
    private String txnAcc;
    private String txnCcy;
    private BigDecimal txnAmt;
    private String offSetBrn;
    private String offsetAcc;
    private String offsetCcy;
    private String offsetAmt;
    private String txnDate;
    private LocalDate valDate;
    private String benfAddr1;
    private String benfAddr2;
    private String benfAddr3;
    private String benfAddr4;
    private String mobileNumber;
    private String email;
    private BigDecimal chgAmt;
    private String chgCcy;
    private String ultBendet5;







}
