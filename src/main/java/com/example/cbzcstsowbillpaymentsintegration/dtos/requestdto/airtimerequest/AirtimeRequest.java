package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.airtimerequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AirtimeRequest {
    private String transactionAccount;
    private String transactionCurrency;
    private String transactionAmount;
    private String beneficaryAddress1;
    private String beneficaryAddress2;
    private String beneficaryAddress3;
    private String beneficaryAddress4;
    private String mobileNumber;
    private String email;
}
