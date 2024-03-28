package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Warning {
    @JsonProperty("WCODE")
    private String wCode;

    @JsonProperty("WDESC")
    private String wDesc;
}
