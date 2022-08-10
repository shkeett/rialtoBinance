package com.example.rialto.util;


import java.util.LinkedList;
import java.util.List;

public class RsiCalc {

    private final int amountRSI = 15;

    private List<Double> prices = new LinkedList<>();

    private void setPrice(double price) {
        prices.add(price);
        if (prices.size() > amountRSI) {
            prices.remove(0);
        }
    }

    public double calc(double price) {

        double calcRsi = 0.0;

        setPrice(price);

        if (prices.size() < amountRSI) {
            return calcRsi;
        } else {

            double differenceUp = 0.0;
            double differenceDown = 0.0;
            double summaDifferUp = 0.0;
            double summaDifferDown = 0.0;


            for (int i = 1; i < prices.size(); i++) {
                double firstPrice = prices.get(i - 1);
                double secondPrice = prices.get(i);

                if (firstPrice > secondPrice) {
                    differenceDown = firstPrice - secondPrice;
                } else {
                    differenceUp = Math.abs(firstPrice - secondPrice);
                }
                summaDifferUp += differenceUp;
                summaDifferDown += differenceDown;

                differenceUp = 0.0;
                differenceDown = 0.0;

            }

            double averageUp = summaDifferUp / (amountRSI-1);
            double averageDown = summaDifferDown / (amountRSI-1);

            double result = 100 - 100 / ((averageUp / averageDown) + 1);   // Вычисление RSI
            calcRsi = (Math.ceil(result * 100)) / 100;       // Округление RSI до 2 знаков после запятой
            return calcRsi;
        }
    }
}