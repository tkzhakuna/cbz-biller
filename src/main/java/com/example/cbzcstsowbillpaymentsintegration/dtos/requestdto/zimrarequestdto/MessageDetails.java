package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto;

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
}
