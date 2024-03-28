package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.rtgsentriesrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractMasterFull {
    @JsonProperty("SOURCE_CODE")
    private String sourceCode;

    @JsonProperty("TRANSFER_TYPE")
    private String transferType;
    @JsonProperty("INTERNAL_REMARKS")
    private String internalRemarks;
}
