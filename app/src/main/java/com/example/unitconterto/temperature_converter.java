package com.example.unitconterto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class temperature_converter extends AppCompatActivity
{
    private void toastMsg(String Msg)
    {
        Toast.makeText(getApplicationContext(), Msg, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void displayResult(Double result)
    {
        TextView ans = (TextView) findViewById(R.id.AnswerTemperature);
        ans.setText("Temperature is: "+String.format(Locale.getDefault(), "%.1f", result));
    }

    private Double convertTemperature(Double value, String units, Boolean flag)
    {
        HashMap<String, double[]> temperatureRate = new HashMap<>();

        temperatureRate.put("Celsius", new double[]{0.0,100.0});
        temperatureRate.put("Kelvin", new double[]{273.15,373.15});
        temperatureRate.put("Fahrenheit", new double[]{32.00,212.00});
        temperatureRate.put("Rankine", new double[]{491.67,671.67});
        temperatureRate.put("Newton", new double[]{0.00, 33.00});
        temperatureRate.put("Romer", new double[]{7.50, 60.00});
        temperatureRate.put("Delis le", new double[]{-100.00, 50.00});

        Double ans;

        if(flag.equals(true))
        {
            ans =  (value*((temperatureRate.get(units)[1]-temperatureRate.get(units)[0])/(temperatureRate.get("Celsius")[1]-temperatureRate.get("Celsius")[0])))-temperatureRate.get(units)[0];
            //toastMsg(Double.toString(ans));
            return ans;
        }

        for(Map.Entry<String, double[]> exchanges : temperatureRate.entrySet())
        {
            if(exchanges.getKey().equals(units))
            {
                ans = (value*((temperatureRate.get(units)[1]-temperatureRate.get(units)[0])/(temperatureRate.get("Celsius")[1]-temperatureRate.get("Celsius")[0])))+temperatureRate.get(units)[0];
                //toastMsg(Double.toString((temperatureRate.get(units)[1]-temperatureRate.get(units)[0])));
                return ans;
            }
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        Spinner tempSpinner1 = (Spinner) findViewById(R.id.temperatureIn);
        Spinner tempSpinner2 = (Spinner) findViewById(R.id.temperatureOut);

        EditText edit1 = (EditText) findViewById(R.id.TemperatureValue);

        if(edit1.getText().toString().length() == 0)
            edit1.setText("0.0");

        //Log.i("info",Double.toString(givenAmt));

        Button convert = (Button) findViewById(R.id.ConvertTemp);

        convert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Double givenAmt = Double.parseDouble(edit1.getText().toString());

                if(tempSpinner1.getSelectedItem().toString().equals(tempSpinner2.getSelectedItem().toString()))
                    toastMsg("You don't need to convert");
                else if (tempSpinner1.getSelectedItem().toString().equals("Celsius"))
                {
                    displayResult(convertTemperature(givenAmt, tempSpinner2.getSelectedItem().toString(), false));
                }
                else
                {
                    Double convertFrom = convertTemperature(givenAmt, tempSpinner1.getSelectedItem().toString(), true);
                    displayResult(convertTemperature(convertFrom, tempSpinner2.getSelectedItem().toString(), false));
                }
            }
        });
    }
}