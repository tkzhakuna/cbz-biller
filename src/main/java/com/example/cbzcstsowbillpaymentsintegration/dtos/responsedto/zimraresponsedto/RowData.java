package com.example.cbzcstsowbillpaymentsintegration.dtos.responsedto.zimraresponsedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RowData {
    @JsonProperty("STATUS")
    private Status status;

    @JsonProperty("ROW")
    private List<Row> rows;
}
