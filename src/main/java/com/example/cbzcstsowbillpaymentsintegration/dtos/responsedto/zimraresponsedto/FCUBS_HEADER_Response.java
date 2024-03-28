package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zimraresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FCUBS_HEADER_Response {
    @JsonProperty("SOURCE")
    private String source;

    @JsonProperty("UBSCOMP")
    private String ubcomp;


    @JsonProperty("MSGSTAT")
    private String msgStat;
}
