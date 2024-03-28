package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zimraresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WARNING {
    @JsonProperty("WCODE")
    private String wcode;

    @JsonProperty("WDESC")
    private String wdesc;
}
