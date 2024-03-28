package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zimraresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargeDetailsResponse {
    @JsonProperty("CHGCOMP")
    private String chgComp;

    @JsonProperty("WAIVER")
    private String waiver;
}
