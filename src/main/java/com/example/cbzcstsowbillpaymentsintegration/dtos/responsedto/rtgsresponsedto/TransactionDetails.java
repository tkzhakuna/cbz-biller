package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.rtgsresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransactionDetails {
    @JsonProperty("XREF")
    private String xref;

    @JsonProperty("FCCREF")
    private String fccref;

    @JsonProperty("LCYCHGAMT")
    private String lcyChgAmt;
}
