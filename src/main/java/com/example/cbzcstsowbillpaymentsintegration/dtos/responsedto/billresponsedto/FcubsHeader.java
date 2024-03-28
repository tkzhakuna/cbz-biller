package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.billresponsedto;

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

    @JsonProperty("MSGID")
    private String msgId;

    @JsonProperty("CORRELID")
    private String correlId;

    @JsonProperty("USERID")
    private String userId;

    @JsonProperty("ENTITY")
    private String entity;

    @JsonProperty("BRANCH")
    private String branch;

    @JsonProperty("MODULEID")
    private String moduleId;

    @JsonProperty("SERVICE")
    private String service;

    @JsonProperty("OPERATION")
    private String operation;

    @JsonProperty("DESTINATION")
    private String destination;

    @JsonProperty("FUNCTIONID")
    private String functionId;

    @JsonProperty("ACTION")
    private String action;

    @JsonProperty("MSGSTAT")
    private String msgStat;
}
