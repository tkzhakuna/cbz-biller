package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaxCodeRequest {
    @JsonProperty("TAX_CODE_REQUEST")
    private String taxCodeRequest;

}
