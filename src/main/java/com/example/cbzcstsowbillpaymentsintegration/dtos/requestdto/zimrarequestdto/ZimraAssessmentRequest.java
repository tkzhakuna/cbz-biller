package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto;

import com.example.cbzcstsowbillpaymentsintegration.enums.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ZimraAssessmentRequest {
    @JsonProperty(value = "TIN")
    private String tin;
    @JsonProperty(value = "CCY")
    private Currency currency;
    @JsonProperty(value = "ASSNO")
    private String assesmentNumber;
}
