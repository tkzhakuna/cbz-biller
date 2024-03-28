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
public class FcubsHeader {
    @JsonProperty("BRANCH")
    public String bRANCH;
    @JsonProperty("CORRELID")
    public String cORRELID;
    @JsonProperty("MSGSTAT")
    public String mSGSTAT;
    @JsonProperty("MODULEID")
    public String mODULEID;
    public String xmlns;
    @JsonProperty("ENTITY")
    public String eNTITY;
    @JsonProperty("ACTION")
    public String aCTION;
    @JsonProperty("OPERATION")
    public String oPERATION;
    @JsonProperty("UBSCOMP")
    public String uBSCOMP;
    @JsonProperty("DESTINATION")
    public String dESTINATION;
    @JsonProperty("SOURCE")
    public String sOURCE;
    @JsonProperty("USERID")
    public String uSERID;
    @JsonProperty("FUNCTIONID")
    public String fUNCTIONID;
    @JsonProperty("SERVICE")
    public String sERVICE;
    @JsonProperty("MSGID")
    public long mSGID;
}
