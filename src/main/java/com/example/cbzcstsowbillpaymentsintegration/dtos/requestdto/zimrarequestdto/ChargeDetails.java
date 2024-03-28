package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargeDetails {
    @JsonProperty("CHGCOMP")
    private String chgComp;

    @JsonProperty("WAIVER")
    private String waiver;

}
