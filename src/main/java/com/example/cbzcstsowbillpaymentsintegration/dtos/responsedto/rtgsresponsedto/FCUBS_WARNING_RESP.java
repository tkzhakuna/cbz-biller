package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.rtgsresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FCUBS_WARNING_RESP {
    @JsonProperty("WARNING")
    private WARNING warning;
}
