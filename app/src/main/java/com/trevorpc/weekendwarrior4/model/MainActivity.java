package com.trevorpc.weekendwarrior4.model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.trevorpc.weekendwarrior4.R;
import com.trevorpc.weekendwarrior4.client.httpHelper;
import com.trevorpc.weekendwarrior4.model.weather.WeatherResponse;

public class MainActivity extends AppCompatActivity {
    EditText etZip;
    TextView tvDisplay;
    boolean celcius = true;
    boolean hot = true;
    String fTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etZip = findViewById(R.id.etZip);
        tvDisplay = findViewById(R.id.tvDisplay);


    }

    public void click(View view) {
        Log.d("TAG", "click: ");
        httpHelper helper = new httpHelper(etZip.getText().toString());
        helper.execute();
        WeatherResponse weather = helper.getWeatherResponse();
        Extraction extra = new Extraction();
        Log.d("TAG", "click: ");
        if (extra.getRawTemperature(weather) < 333.15) {
            hot = false;
        }

        if (celcius) {
            double temperature = extra.toCelsius(weather);
            fTemp = temperature + " degrees Celsius ";

        } else {
            double temperature = extra.ToFahrenheit(weather);
            fTemp = temperature + " degrees Fahrenheit ";
        }
        tvDisplay.setText(fTemp);

    }
}