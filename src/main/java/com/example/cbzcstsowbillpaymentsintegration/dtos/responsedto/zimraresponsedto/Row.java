package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zimraresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Row {
    @JsonProperty("TAX_CODE")
    private String taxCode;

    @JsonProperty("TAX_OBLIGATION_TYPE")
    private String taxObligationType;
}
