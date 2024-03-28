package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zesaresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class ZesaRoot {
        @JsonProperty("Envelope")
        public ZesaEnvelope zesaEnvelope;
    }

