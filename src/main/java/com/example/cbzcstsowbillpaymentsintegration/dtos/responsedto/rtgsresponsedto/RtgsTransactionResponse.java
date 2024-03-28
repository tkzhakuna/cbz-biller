package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.rtgsresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RtgsTransactionResponse {
    @JsonProperty("FCUBS_HEADER")
    private FCUBS_HEADER_Response fcubsHeader;

    @JsonProperty("FCUBS_BODY")
    private FCUBS_BODY_Response fcubsBody;
}
