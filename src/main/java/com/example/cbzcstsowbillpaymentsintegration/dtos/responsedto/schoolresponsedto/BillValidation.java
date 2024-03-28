package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto;


import com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.Status;
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
public class BillValidation {
    @JsonProperty(value="STATUS")
    private Status status;
    @JsonProperty(value="FIELD_VALUE")
    private String fieldValue;
    @JsonProperty(value="FIELD_DESCRIPTION")
    private String fieldDescription;
    @JsonProperty(value="FIELD_CCY")
    private String fieldCcy;


}
