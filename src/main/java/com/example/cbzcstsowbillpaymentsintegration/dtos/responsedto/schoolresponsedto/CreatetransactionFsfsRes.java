package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@RequiredArgsConstructor
public class CreatetransactionFsfsRes {
    @JsonProperty("FCUBS_HEADER")
    private FcubsHeader fcubsHeader;
    @JsonProperty("FCUBS_BODY")
    private FcubsBody fcubsBody;


}
