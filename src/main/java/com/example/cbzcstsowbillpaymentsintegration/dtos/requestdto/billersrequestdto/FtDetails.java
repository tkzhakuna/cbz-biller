package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.billersrequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FtDetails {
    @JsonProperty("ULTBENDET1")
    private String ultBenDet1;
}
