package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FCUBS_BODY {
    @JsonProperty("Transaction-Details")
    private TransactionDetails transactionDetails;
}
