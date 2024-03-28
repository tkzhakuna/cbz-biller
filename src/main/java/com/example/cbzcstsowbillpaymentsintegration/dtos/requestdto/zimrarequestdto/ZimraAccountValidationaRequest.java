package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ZimraAccountValidationaRequest {

    @JsonProperty(value = "ASSNO")
    private String assNo;
    @JsonProperty(value = "TIN")
    private String tin;
    @JsonProperty(value = "CCY")
    private String currency;
}
