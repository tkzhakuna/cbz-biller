package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto;


import com.example.cbzcstsowbillpaymentsintegration.enums.Currency;
import com.example.cbzcstsowbillpaymentsintegration.enums.Waiver;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class StudentRequestDto {
    private String userId;
    private String transactionBranch;
private String customerBranch;
private String customerAccount;
private Currency transactionCurrency;
private BigDecimal transactionAmount;
private String billerAccountBranch;
private String CBZBillerAccount;
private Currency currency;
private BigDecimal amount;
private String paymentReason;
private String billerAccount;
private String mobileNumber;
private  String studentName;
private String email;
private Waiver waiver;
private BigDecimal chargeAmount;
private String term;
private String schoolClass;





}
