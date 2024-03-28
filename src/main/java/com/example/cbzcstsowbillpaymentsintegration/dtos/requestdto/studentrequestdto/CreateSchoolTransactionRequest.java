package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.studentrequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSchoolTransactionRequest {
    @JsonProperty("CREATETRANSACTION_FSFS_REQ")
    private CreatetransactionFsfsReq createtransactionFsfsReq;
}
