package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zimraresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZimraTransactionResponse {
    @JsonProperty("FCUBS_HEADER")
    private FCUBS_HEADER_Response fcubsHeader;

    @JsonProperty("FCUBS_BODY")
    private FCUBS_BODY_Response fcubsBody;

}
