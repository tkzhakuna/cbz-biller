package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.rtgsresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FCUBS_HEADER_Response {
    @JsonProperty("SOURCE")
    private String source;

    @JsonProperty("MSGID")
    private String msgId;


    @JsonProperty("MSGSTAT")
    private String msgStat;
}
