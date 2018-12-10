package com.trevorpc.weekendwarrior4.client;

import android.util.Log;

import com.google.gson.Gson;
import com.trevorpc.weekendwarrior4.model.NetworkHelper;
import com.trevorpc.weekendwarrior4.model.weather.Main;
import com.trevorpc.weekendwarrior4.model.weather.Weather;
import com.trevorpc.weekendwarrior4.model.weather.WeatherResponse;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


import static android.content.ContentValues.TAG;


public class httpHelper {

    OkHttpClient client;
    private Request request;
    WeatherResponse weatherResponse;

    public httpHelper(String place) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        String nation = "US";

        place = place + "," + nation;

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(NetworkHelper.BASE_URL)
                .addPathSegment("data")
                .addPathSegment("2.5")
                .addPathSegment("weather")
                .addQueryParameter("q", place)
                .addQueryParameter("APPID", NetworkHelper.API_KEY)
                .build();

        Log.d("TAG", "httpHelper: end`" + url.toString());


        client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        Log.d("TAG", "client completed");

        request = new Request.Builder()
                .url(url)
                .build();

        Log.d("TAG", "httpHelper: complete");

    }

    public void execute()
    {
        Log.d("TAG", "execute: ");


        Log.d(TAG, "weatherResponse created ");
        try {
            String response = client.newCall(request).execute().body().string();
            Log.d("TAG", "execute: " + response);
            Gson gson = new Gson();
            weatherResponse = gson.fromJson(response, WeatherResponse.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("TAG", "execute: done");

    }

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }
}
