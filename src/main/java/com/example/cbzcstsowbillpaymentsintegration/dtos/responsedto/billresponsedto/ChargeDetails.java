package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.billresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ChargeDetails {
    @JsonProperty("CHGCOMP")
    private String chgComp;

    @JsonProperty("WAIVER")
    private String waiver;

    @JsonProperty("CHGAMT")
    private BigDecimal chgAmt;

    @JsonProperty("CHGCCY")
    private String chgCcy;

    @JsonProperty("TXNCODE")
    private String txnCode;


}

