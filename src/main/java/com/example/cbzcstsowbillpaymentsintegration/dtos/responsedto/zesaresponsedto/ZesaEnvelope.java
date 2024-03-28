package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zesaresponsedto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@RequiredArgsConstructor
@Getter
@Setter
@ToString
@JsonRootName("Envelope")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZesaEnvelope {
    @JsonProperty(value = "Body")
    private ZesaBody zesaBody;;
}


