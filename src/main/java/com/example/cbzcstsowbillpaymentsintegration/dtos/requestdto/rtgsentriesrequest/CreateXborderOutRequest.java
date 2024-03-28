package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.rtgsentriesrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateXborderOutRequest {
    @JsonProperty("FCUBS_HEADER")
    private FCUBS_HEADER_Request fcubsHeader;

    @JsonProperty("FCUBS_BODY")
    private FCUBS_BODY_XborderOut_Request fcubsBody;
}
