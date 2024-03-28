package com.example.cbzcstsowbillpaymentsintegration.dtos.requestdto.zimrarequestdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Status {
    @JsonProperty(value = "CODE")
    public String code;
    @JsonProperty(value = "NAME")
    public String name;
}