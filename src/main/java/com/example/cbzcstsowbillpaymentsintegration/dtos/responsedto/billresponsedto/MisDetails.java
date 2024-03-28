package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.billresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class MisDetails {

    @JsonProperty("BRANCH")
    private String branch;

    @JsonProperty("CUSTNO")
    private String custNo;

    @JsonProperty("CCY")
    private String ccy;

    @JsonProperty("RTFLAG")
    private String rtFlag;

    @JsonProperty("TXNMIS1LBL1")
    private String txnMis1Lbl1;

    @JsonProperty("TXNMIS2LBL2")
    private String txnMis2Lbl2;

    @JsonProperty("TXNMIS3LBL3")
    private String txnMis3Lbl3;

    @JsonProperty("TXNMIS4LBL4")
    private String txnMis4Lbl4;

    @JsonProperty("TXNMIS5LBL5")
    private String txnMis5Lbl5;

}
