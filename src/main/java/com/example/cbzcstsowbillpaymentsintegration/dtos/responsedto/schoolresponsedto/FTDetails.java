package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class FTDetails {
    @JsonProperty("ULTBENDET4")
    private String ultBendet4;
    @JsonProperty("ULTBENDET3")
    private String ultBendet3;
    @JsonProperty("ULTBENDET2")
    private String ultBendet2;
    @JsonProperty("ULTBENDET1")
    private String ultBendet1;
    @JsonProperty("ULTBEN6")
    private String ultBen6;
    @JsonProperty("ULTBENDET5")
    private String ultBendet5;

}
