package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto;


import com.example.cbzcstsowbillpaymentsintegration.enums.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ZimraAccountCreationRequest {
    @JsonProperty(value = "TIN")
    public Integer tin;
    @JsonProperty(value = "BANK")
    public String bank;
    @JsonProperty(value = "BRANCH")
    public String branch;
    @JsonProperty(value = "ACCOUNTS")
    public Account accounts;
    @JsonProperty(value = "STATUS")
    public Status  status;
    public String getNumber() {
        return accounts.getNumber();
    }
    public Currency getCurrency() {
        return accounts.getCurrency();
    }

    public String getStatusCode() {
        return status.getCode();
    }
    public String getName() {
        return status.getName();
    }

}



