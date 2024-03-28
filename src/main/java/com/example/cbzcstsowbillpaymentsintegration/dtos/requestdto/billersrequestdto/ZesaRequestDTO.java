package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.billersrequestdto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ZesaRequestDTO {
    private String meterNumber;
    private String referenceNumber;
    private String terminalId;
}
