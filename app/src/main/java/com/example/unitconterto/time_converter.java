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

public class time_converter extends AppCompatActivity
{
    private void toastMsg(String Msg)
    {
        Toast.makeText(getApplicationContext(), Msg, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void displayResult(Double result)
    {
        TextView ans = (TextView) findViewById(R.id.AnswerTime);
        ans.setText("Time value is: "+String.format(Locale.getDefault(), "%.2f", result));
    }

    private Double convertForce(Double value, String units, Boolean flag)
    {
        HashMap<String, Double> timeRate = new HashMap<>();

        timeRate.put("Millisecond", 1000.00);
        timeRate.put("Seconds", 1.00);
        timeRate.put("Minutes", 1/60.00);
        timeRate.put("Hours", 1/3600.00);
        timeRate.put("Days", 1/86400.00);

        Double ans;

        if(flag.equals(true))
        {
            ans =  value/ timeRate.get(units);
            return ans;
        }

        for(Map.Entry<String, Double> exchanges : timeRate.entrySet())
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
        setContentView(R.layout.activity_time_converter);

        Spinner tempSpinner1 = (Spinner) findViewById(R.id.timeIn);
        Spinner tempSpinner2 = (Spinner) findViewById(R.id.timeOut);

        EditText edit1 = (EditText) findViewById(R.id.TimeValue);

        if(edit1.getText().toString().length() == 0)
            edit1.setText("0.0");

        //Log.i("info",Double.toString(givenAmt));

        Button convert = (Button) findViewById(R.id.ConvertTime);

        convert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Double givenValue = Double.parseDouble(edit1.getText().toString());

                if(tempSpinner1.getSelectedItem().toString().equals(tempSpinner2.getSelectedItem().toString()))
                    toastMsg("You don't need to convert");
                else if (tempSpinner1.getSelectedItem().toString().equals("Seconds"))
                {
                    displayResult(convertForce(givenValue, tempSpinner2.getSelectedItem().toString(), false));
                }
                else
                {
                    Double convertFrom = convertForce(givenValue, tempSpinner1.getSelectedItem().toString(), true);
                    displayResult(convertForce(convertFrom, tempSpinner2.getSelectedItem().toString(), false));
                }
            }
        });
    }
}