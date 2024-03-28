package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zesaresponsedto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeterValidation {
    @JsonProperty(value="METER_NUMBER")
    private String meterNumber;
    @JsonProperty(value="METER_CCY")
    private String meterCcy;
    @JsonProperty(value="METER_NAME")
    private String meterName;
    @JsonProperty(value="METER_ADDRESS")
    private String meterAddress;
    @JsonProperty(value="RESPONSE_CODE")
    private String responseCode;
    @JsonProperty(value="NARRATIVE")
    private String narrative;
    @JsonProperty(value="REFERENCE_NUMBER")
    private String referenceNumber;
    @JsonProperty(value="TERMINAL_ID")
    private String terminalId;
}