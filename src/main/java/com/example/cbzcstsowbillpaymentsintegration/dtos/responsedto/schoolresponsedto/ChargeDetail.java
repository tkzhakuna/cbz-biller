package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.schoolresponsedto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
class ChargeDetail {
    public String CHGCOMP;
    public String WAIVER;
    public double CHGAMT;
    public String CHGCCY;
    public String TXNCODE;

}
