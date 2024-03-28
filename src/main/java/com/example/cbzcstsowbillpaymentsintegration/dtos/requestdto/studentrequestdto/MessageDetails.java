package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDetails {
    @JsonProperty("INSTRCD")
    private String instrcd;

    @JsonProperty("BENFADDR1")
    private String benfAddr1;

    @JsonProperty("BENFADDR2")
    private String benfAddr2;

    @JsonProperty("BENFADDR3")
    private String benfAddr3;

    @JsonProperty("BENFADDR4")
    private String benfAddr4;
}
