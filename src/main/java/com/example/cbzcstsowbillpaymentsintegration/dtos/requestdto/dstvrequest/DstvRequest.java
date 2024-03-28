package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.dstvrequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DstvRequest {
    String mobileNumber;
    String email;
    private String transactionAccount;
    private String transactionCurrency;
    private String transactionAmount;
    private String beneficaryAddress1;
    private String beneficaryAddress2;
    private String beneficaryAddress3;
    private String beneficaryAddress4;
  }
