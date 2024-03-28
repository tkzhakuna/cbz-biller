package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FcubsBody {
    @JsonProperty("Transaction-Details")
    private TransactionDetails transactionDetails;
}
