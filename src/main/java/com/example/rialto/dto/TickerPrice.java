package com.example.rialto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class TickerPrice {

    @JsonProperty
    private List<ObjectPrice> objects;

}
