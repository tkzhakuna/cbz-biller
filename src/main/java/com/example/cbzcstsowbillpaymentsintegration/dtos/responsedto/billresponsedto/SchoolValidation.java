package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.billresponsedto;

import com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolValidation {
    @JsonProperty("STATUS")
    private Status status;

    @JsonProperty("CUSTOMER_NAME")
    private String customerName;

    @JsonProperty("ACCOUNT_NUMBER")
    private String accountNumber;

    @JsonProperty("CUSTOMER_NUMBER")
    private String customerNumber;

    @JsonProperty("BRANCH_CODE")
    private String branchCode;

    @JsonProperty("CURRENCY")
    private String currency;

}
