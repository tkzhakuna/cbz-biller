package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zimraresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZimraAccountValidationResponse {
    @JsonProperty("STATUS")
    private Status status;

    @JsonProperty("BP_NUMBER")
    private String bpNumber;

    @JsonProperty("BP_DESCRIPTION")
    private String bpDescription;
}
