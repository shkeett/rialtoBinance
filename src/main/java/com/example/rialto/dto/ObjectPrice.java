package com.example.rialto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ObjectPrice {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("ts")
    private double price;
}
