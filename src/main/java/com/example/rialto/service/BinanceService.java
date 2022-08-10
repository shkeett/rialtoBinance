package com.example.rialto.service;

import com.example.rialto.dto.ObjectPrice;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;


public interface BinanceService {

    @GET("v3/ticker/price?")
    public Call<List<ObjectPrice>> getPrice();

}