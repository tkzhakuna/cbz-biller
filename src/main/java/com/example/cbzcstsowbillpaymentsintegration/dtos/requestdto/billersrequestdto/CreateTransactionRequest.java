package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.billersrequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTransactionRequest {
    @JsonProperty("FCUBS_HEADER")
    private FcubsHeader fcubsHeader;

    @JsonProperty("FCUBS_BODY")
    private FcubsBody fcubsBody;
}
