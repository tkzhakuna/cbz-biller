package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Error {
    @JsonProperty("ECODE")
    private String eCode;
    @JsonProperty("EDESC")
    private String edesc;


}
