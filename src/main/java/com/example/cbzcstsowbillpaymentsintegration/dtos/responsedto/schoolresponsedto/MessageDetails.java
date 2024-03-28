package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class MessageDetails {
    @JsonProperty("INSTRCD")
    public String iNSTRCD;
    @JsonProperty("PAYDET2")
    public String pAYDET2;
    @JsonProperty("PAYDET1")
    public String pAYDET1;
    @JsonProperty("PAYDET4")
    public String pAYDET4;
    @JsonProperty("PAYDET3")
    public String pAYDET3;
}
