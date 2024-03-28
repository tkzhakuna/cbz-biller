package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zesarequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class PurchaseTokenRequest {
    private BigDecimal amount;
    private Long meterNumber;
}
