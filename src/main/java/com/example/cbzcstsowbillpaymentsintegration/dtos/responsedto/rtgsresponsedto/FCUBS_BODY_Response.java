package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.rtgsresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FCUBS_BODY_Response {
    @JsonProperty("Transaction-Details")
    private TransactionDetails transactionDetails;

    @JsonProperty("FCUBS_WARNING_RESP")
    private FCUBS_WARNING_RESP fcubsWarningResp;
}
