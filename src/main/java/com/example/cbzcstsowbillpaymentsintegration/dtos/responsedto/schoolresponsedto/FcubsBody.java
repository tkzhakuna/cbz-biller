package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FcubsBody {
    @JsonProperty("Transaction-Details")
    private TransactionDetails transactionDetails;

    @JsonProperty("FCUBS_ERROR_RESP")
    private FcubsErrorResp fcubsErrorResp;
}
