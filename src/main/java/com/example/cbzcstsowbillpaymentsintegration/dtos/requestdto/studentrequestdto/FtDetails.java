package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FtDetails {
    @JsonProperty("ULTBENDET1")
    private String ultbenDet1;

    @JsonProperty("ULTBENDET2")
    private String ultbenDet2;

    @JsonProperty("ULTBENDET3")
    private String ultbenDet3;

    @JsonProperty("ULTBENDET4")
    private String ultbenDet4;

    @JsonProperty("ULTBENDET5")
    private String ultbenDet5;

    @JsonProperty("ULTBENDET6")
    private String ultbenDet6;
}
