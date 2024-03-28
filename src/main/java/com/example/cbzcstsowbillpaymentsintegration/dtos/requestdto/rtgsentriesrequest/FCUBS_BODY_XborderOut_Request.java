package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.rtgsentriesrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FCUBS_BODY_XborderOut_Request {
    @JsonProperty("Contract-Master-Full")
    private ContractMasterFull contractMasterFull;
}
