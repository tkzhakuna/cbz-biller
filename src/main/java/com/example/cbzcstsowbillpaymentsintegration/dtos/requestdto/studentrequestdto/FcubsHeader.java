package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FcubsHeader {
    @JsonProperty("SOURCE")
    private String source;

    @JsonProperty("UBSCOMP")
    private String ubcomp;

    @JsonProperty("USERID")
    private String userId;

    @JsonProperty("BRANCH")
    private String branch;

    @JsonProperty("MODULEID")
    private String moduleId;

    @JsonProperty("SERVICE")
    private String service;

    @JsonProperty("OPERATION")
    private String operation;

    @JsonProperty("FUNCTIONID")
    private String functionId;

    @JsonProperty("ACTION")
    private String action;

    @JsonProperty("MSGSTAT")
    private String msgStat;
}
