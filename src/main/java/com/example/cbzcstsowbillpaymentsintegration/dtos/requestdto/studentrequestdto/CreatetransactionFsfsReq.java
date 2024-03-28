package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatetransactionFsfsReq {
    @JsonProperty("FCUBS_HEADER")
    private FcubsHeader fcubsHeader;

    @JsonProperty("FCUBS_BODY")
    private FcubsBody fcubsBody;
}
