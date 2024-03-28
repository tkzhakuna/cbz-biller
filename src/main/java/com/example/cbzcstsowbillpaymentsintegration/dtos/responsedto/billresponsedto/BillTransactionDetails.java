package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.billresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class BillTransactionDetails {
    @JsonProperty("XREF")
    private String xref;

    @JsonProperty("FCCREF")
    private String fccRef;

    @JsonProperty("PRD")
    private String prd;

    @JsonProperty("BRN")
    private String brn;

    @JsonProperty("TXNBRN")
    private String txnBrn;

    @JsonProperty("TXNACC")
    private String txnAcc;

    @JsonProperty("TXNCCY")
    private String txnCcy;

    @JsonProperty("TXNAMT")
    private Double txnAmt;

    @JsonProperty("TXNTRN")
    private String txnTrn;

    @JsonProperty("OFFSETBRN")
    private String offsetBrn;

    @JsonProperty("OFFSETACC")
    private String offsetAcc;

    @JsonProperty("OFFSETCCY")
    private String offsetCcy;

    @JsonProperty("OFFSETAMT")
    private BigDecimal offsetAmt;

    @JsonProperty("OFFSETTRN")
    private String offsetTrn;


    @JsonProperty("Charge-Details")
    private List<ChargeDetails> chargeDetailsList;

    @JsonProperty("Mis-Details")
    private MisDetails misDetails;
}
