package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto;

import com.example.cbzcstsowbillpaymentsintegration.enums.Currency;
import com.example.cbzcstsowbillpaymentsintegration.enums.Waiver;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class ZimraRtgsTransactionRequestDto {
 private String userId;
 private String accountBranch;
private String orderingCustomer1;
private String orderingCustomer2;
private String orderingCustomer4;
private String transactionCurrency;
private String transactionAmount;
private String drCurrency;
private String beneficiary1;
private String beneficiary2;
private String paymentDetails;
private String drAccountNumber;
 }
