package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto;

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

    @JsonProperty("CHGAMT")
    private Double chgAmt;

    @JsonProperty("CHGCCY")
    private String chgCcy;

    @JsonProperty("TXNCODE")
    private String txnCode;
}
