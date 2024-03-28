package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.billresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolAccountValidationResponse {

    @JsonProperty("SCHOOL_VALIDATION")
    private SchoolValidation schoolValidation;

}
