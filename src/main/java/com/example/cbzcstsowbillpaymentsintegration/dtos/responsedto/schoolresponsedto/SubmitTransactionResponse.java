package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto;


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
public class SubmitTransactionResponse {
    @JsonProperty("SubmitTransactionResult")
    private SubmitTransactionResult submitTransactionResult;

}
