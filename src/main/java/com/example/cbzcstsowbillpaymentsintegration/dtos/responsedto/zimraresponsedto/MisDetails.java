package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zimraresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MisDetails {
    @JsonProperty("BRANCH")
    private String branch;

    @JsonProperty("CUSTNO")
    private String custNo;
}
