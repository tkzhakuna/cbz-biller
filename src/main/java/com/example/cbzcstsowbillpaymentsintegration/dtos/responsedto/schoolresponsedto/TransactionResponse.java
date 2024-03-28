package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class TransactionResponse {
    @JsonProperty("FCUBS_HEADER")
    private FcubsHeader fcubsHeader;

    @JsonProperty("FCUBS_BODY")
    private FcubsBody fcubsBody;

}
