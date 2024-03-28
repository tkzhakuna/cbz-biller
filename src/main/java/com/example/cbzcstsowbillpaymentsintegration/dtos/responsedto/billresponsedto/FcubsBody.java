package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.billresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FcubsBody {
    @JsonProperty("Transaction-Details")
    private BillTransactionDetails billTransactionDetails;

    @JsonProperty("FCUBS_WARNING_RESP")
    private FcubsWarningResponse fcubsWarningResponse;
}
