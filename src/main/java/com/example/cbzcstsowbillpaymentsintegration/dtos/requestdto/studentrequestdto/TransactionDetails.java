package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class TransactionDetails {
    @JsonProperty("XREF")
    private String xref;

    @JsonProperty("FCCREF")
    private String fccRef;

    @JsonProperty("MODULE")
    private String module;

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

    @JsonProperty("SCODE")
    private String scode;
    @JsonProperty("Charge-Details")
    private List<ChargeDetails> chargeDetailsList;

    @JsonProperty("Message-Details")
    private MessageDetails messageDetails;

    @JsonProperty("FT-Details")
    private FtDetails ftDetails;

}
