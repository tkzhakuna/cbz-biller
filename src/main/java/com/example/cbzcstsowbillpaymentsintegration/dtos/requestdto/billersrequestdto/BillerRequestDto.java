package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.billersrequestdto;

import com.example.cbzcstsowbillpaymentsintegration.enums.Currency;
import com.example.cbzcstsowbillpaymentsintegration.enums.Waiver;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class BillerRequestDto {
    private String userId;
    private String transactionBranch;
    private String customerBranch;
    private String customerAccount;
    private com.example.cbzcstsowbillpaymentsintegration.enums.Currency transactionCurrency;
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

}
