package com.example.rialto.scheduler;

import com.example.rialto.dto.ObjectPrice;
import com.example.rialto.service.BinanceService;
import com.example.rialto.util.RsiCalc;
import okhttp3.OkHttpClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;


@Component
public class ShedClass {

    // Тикер для которого будет расчитываться RSI
    private String rsiFor = "MATICUSDT";

    private RsiCalc rsiCalc;

    public ShedClass() {
        this.rsiCalc = new RsiCalc();
    }

    // Массив тикеров монет, данные которых необходимо выводить на экран
    // Программа получает от Api Binance все тикеры, но выводит только те, что есть в массиве
    // При необходимости дополнительных тикеров внести их в массив


    String[] array = new String[]
            {"BTCUSDT", "MATICUSDT", "BNBUSDT", "LINKUSDT", "ADAUSDT", "XRPUSDT", "ETHUSDT"};

    // Итератор (через какой промежуток времени повторять запрос к Api Binance 10_000 - > 10 сек)

    @Scheduled(fixedDelay = 5_000)
    public void shedStart() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.binance.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        BinanceService binanceService = retrofit.create(BinanceService.class);

        Call<List<ObjectPrice>> tickerPriceCall = binanceService.getPrice();


        try {
            Response<List<ObjectPrice>> response = tickerPriceCall.execute();
            List<ObjectPrice> tickerPrice = response.body();

            // Вывод тикеров из массива на экран

            int number = 1;

            if (tickerPrice != null) {
                for (ObjectPrice otp : tickerPrice) {
                    for (int i = 0; i < array.length; i++) {
                        if (otp.getSymbol().equals(array[i])) {
                            String symbol = otp.getSymbol();
                            double price = otp.getPrice();
                            if (otp.getSymbol().equals(rsiFor)) {
                                System.out.println(number + "\t" + symbol + ":" + "\t" + price
                                        + "\t" + "RSI -> " + rsiCalc.calc(price));
                                number++;
                            } else
                                System.out.println(number + "\t" + symbol + ":" + "\t" + price);
                            number++;
                        }
                    }
                }
                System.out.println("-----------------------------");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}