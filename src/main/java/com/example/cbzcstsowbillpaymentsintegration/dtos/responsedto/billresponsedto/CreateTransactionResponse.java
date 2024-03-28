package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.billresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTransactionResponse {
    @JsonProperty("FCUBS_HEADER")
    private FcubsHeader fcubsHeader;

    @JsonProperty("FCUBS_BODY")
    private FcubsBody fcubsBody;

    @JsonProperty("FCUBS_WARNING_RESP")
    private FcubsWarningResponse fcubsWarningResponse;
}
