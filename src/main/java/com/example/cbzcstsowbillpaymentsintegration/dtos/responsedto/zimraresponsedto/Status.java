package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zimraresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Status {
    @JsonProperty("VALID")
    private String valid;

    @JsonProperty("DESC")
    private String desc;

}
