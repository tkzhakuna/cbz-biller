package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zimraresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDetailsResponse {
    @JsonProperty("XREF")
    private String xref;

    @JsonProperty("FCCREF")
    private String fccref;

    @JsonProperty("BENFNAME")
    private String benfName;

    @JsonProperty("MOBILE_NUMBER")
    private String mobileNumber;

    @JsonProperty("EMAIL_ID")
    private String emailId;


    @JsonProperty("Mis-Details")
    private MisDetails misDetails;
}
