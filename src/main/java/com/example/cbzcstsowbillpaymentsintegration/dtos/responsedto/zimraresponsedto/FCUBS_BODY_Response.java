package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zimraresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FCUBS_BODY_Response {
    @JsonProperty("Transaction-Details")
    private TransactionDetailsResponse transactionDetails;

    @JsonProperty("FCUBS_WARNING_RESP")
    private FCUBS_WARNING_RESP fcubsWarningResp;
}
