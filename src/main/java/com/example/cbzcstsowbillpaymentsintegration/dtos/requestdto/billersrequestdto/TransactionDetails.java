package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.billersrequestdto;

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


    @JsonProperty("Charge-Details")
    private List<ChargeDetails> chargeDetailsList;

    @JsonProperty("FT-Details")
    private FtDetails ftDetails;
}

