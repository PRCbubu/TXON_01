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

public class distance_converter extends AppCompatActivity
{
    private void toastMsg(String Msg)
    {
        Toast.makeText(getApplicationContext(), Msg, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void displayResult(Double result)
    {
        TextView ans = (TextView) findViewById(R.id.AnswerDist);
        ans.setText("Distance is: "+String.format(Locale.getDefault(), "%.1f", result));
    }

    private Double convertDist(Double value, String units, Boolean flag)
    {
        HashMap<String, Double> distRate = new HashMap<>();

        distRate.put("Millimeter", 1000.00);
        distRate.put("Decimeter", 100.00);
        distRate.put("Centimeter", 10.00);
        distRate.put("Meter", 1.00);
        distRate.put("Dekameter", 1.00/10.00);
        distRate.put("Hectometer", 1.00/100.00);
        distRate.put("Kilometer", 1.00/1000.00);
        distRate.put("Feet", 3.2808399);
        distRate.put("Inch", 39.3700787);

        Double ans;

        if(flag.equals(true))
        {
            ans =  value/ distRate.get(units);
            return ans;
        }

        for(Map.Entry<String, Double> exchanges : distRate.entrySet())
        {
            if(exchanges.getKey().equals(units))
            {
                ans = value * exchanges.getValue();
                return ans;
            }
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_converter);

        Spinner tempSpinner1 = (Spinner) findViewById(R.id.distIn);
        Spinner tempSpinner2 = (Spinner) findViewById(R.id.distOut);

        EditText edit1 = (EditText) findViewById(R.id.DistValue);

        if(edit1.getText().toString().length() == 0)
            edit1.setText("0.0");

        //Log.i("info",Double.toString(givenAmt));

        Button convert = (Button) findViewById(R.id.ConvertDist);

        convert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Double givenValue = Double.parseDouble(edit1.getText().toString());

                if(tempSpinner1.getSelectedItem().toString().equals(tempSpinner2.getSelectedItem().toString()))
                    toastMsg("You don't need to convert");
                else if (tempSpinner1.getSelectedItem().toString().equals("Gram"))
                {
                    displayResult(convertDist(givenValue, tempSpinner2.getSelectedItem().toString(), false));
                }
                else
                {
                    Double convertFrom = convertDist(givenValue, tempSpinner1.getSelectedItem().toString(), true);
                    displayResult(convertDist(convertFrom, tempSpinner2.getSelectedItem().toString(), false));
                }
            }
        });
    }
}