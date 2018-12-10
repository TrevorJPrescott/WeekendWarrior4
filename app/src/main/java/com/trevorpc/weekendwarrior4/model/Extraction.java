package com.trevorpc.weekendwarrior4.model;

import android.util.Log;

import com.trevorpc.weekendwarrior4.model.weather.WeatherResponse;

public class Extraction
{
    WeatherResponse weatherResponse;



    public float getRawTemperature( WeatherResponse weatherResponse)
    {
        float rawTemperature = weatherResponse.getMain().getTemp();

        Log.d("TAG", "getRawTemperature: " + rawTemperature);
        return rawTemperature;

    }

    public Double toCelsius(WeatherResponse weatherResponse)
    {
        double temp = (double) getRawTemperature(weatherResponse);
        temp = temp - 273.15;
        Log.d("TAG", "toCelsius: "+ temp);
        return temp;
    }

    public Double ToFahrenheit(WeatherResponse weatherResponse){
        double temp = toCelsius(weatherResponse);
        temp = temp*9/5 +32;
        return temp;
    }

}
