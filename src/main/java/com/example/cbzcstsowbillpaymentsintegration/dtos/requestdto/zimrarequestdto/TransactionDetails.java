package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDetails {
    @JsonProperty("XREF")
    private String xref;

    @JsonProperty("FCCREF")
    private String fccref;

    @JsonProperty("MODULE")
    private String module;


    @JsonProperty("ULTBENDET5")
    private String ultbenDet5;

    @JsonProperty("ULTBENDET6")
    private String ultbenDet6;
}
