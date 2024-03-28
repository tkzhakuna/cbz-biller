package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.billersrequestdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillerAccountValidationRequest {
    private String fieldName;
    private String fieldValue;
}
