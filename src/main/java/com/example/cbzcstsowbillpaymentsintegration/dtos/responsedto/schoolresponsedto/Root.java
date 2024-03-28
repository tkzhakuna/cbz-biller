package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Root {
        @JsonProperty("Envelope")
        public Envelope envelope;
    }

