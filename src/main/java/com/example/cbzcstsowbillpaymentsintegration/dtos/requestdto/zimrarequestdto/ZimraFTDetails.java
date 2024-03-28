package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZimraFTDetails {
    @JsonProperty("ULTBENDET1")
    private String ultbenDet1;

    @JsonProperty("ULTBENDET2")
    private String ultbenDet2;
}
