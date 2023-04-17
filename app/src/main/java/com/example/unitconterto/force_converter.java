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

public class force_converter extends AppCompatActivity
{
    private void toastMsg(String Msg)
    {
        Toast.makeText(getApplicationContext(), Msg, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void displayResult(Double result)
    {
        TextView ans = (TextView) findViewById(R.id.AnswerForce);
        ans.setText("Force Value is: "+String.format(Locale.getDefault(), "%.2f", result));
    }

    private Double convertForce(Double value, String units, Boolean flag)
    {
        HashMap<String, Double> forceRate = new HashMap<>();

        forceRate.put("Newton", 1.00);
        forceRate.put("Gram-force", 101.97);
        forceRate.put("Dyne", 100000.00);
        forceRate.put("Poundal", 7.233);
        forceRate.put("Pound-force", 0.22480894);
        forceRate.put("Kilogram-force", 0.10197162);
        forceRate.put("Kilogram", 1.00/1000.00);
        forceRate.put("joule/centimeter", 100.00);

        Double ans;

        if(flag.equals(true))
        {
            ans =  value/ forceRate.get(units);
            return ans;
        }

        for(Map.Entry<String, Double> exchanges : forceRate.entrySet())
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
        setContentView(R.layout.activity_force_converter);

        Spinner tempSpinner1 = (Spinner) findViewById(R.id.forceIn);
        Spinner tempSpinner2 = (Spinner) findViewById(R.id.forceOut);

        EditText edit1 = (EditText) findViewById(R.id.ForceValue);

        if(edit1.getText().toString().length() == 0)
            edit1.setText("0.0");

        //Log.i("info",Double.toString(givenAmt));

        Button convert = (Button) findViewById(R.id.ConvertForce);

        convert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Double givenValue = Double.parseDouble(edit1.getText().toString());

                if(tempSpinner1.getSelectedItem().toString().equals(tempSpinner2.getSelectedItem().toString()))
                    toastMsg("You don't need to convert");
                else if (tempSpinner1.getSelectedItem().toString().equals("Newton"))
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