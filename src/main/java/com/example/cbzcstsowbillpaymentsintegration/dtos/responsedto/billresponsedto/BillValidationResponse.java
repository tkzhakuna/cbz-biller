package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.billresponsedto;

import com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillValidationResponse {
    @JsonProperty("STATUS")
    private Status status;

    @JsonProperty("FIELD_VALUE")
    private String fieldValue;

    @JsonProperty("FIELD_DESCRIPTION")
    private String fieldDescription;
}
