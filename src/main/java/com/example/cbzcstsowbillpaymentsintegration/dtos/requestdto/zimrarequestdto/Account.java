package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto;

import com.example.cbzcstsowbillpaymentsintegration.enums.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Account {
    @JsonProperty(value = "NUMBER")
    public String number;
    @JsonProperty(value = "CURRENCY")
    public Currency currency;

}
