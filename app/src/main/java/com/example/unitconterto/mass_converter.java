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

import kotlin.collections.DoubleIterator;

public class mass_converter extends AppCompatActivity
{
    private void toastMsg(String Msg)
    {
        Toast.makeText(getApplicationContext(), Msg, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void displayResult(Double result)
    {
        TextView ans = (TextView) findViewById(R.id.AnswerMass);
        ans.setText("Mass is: "+String.format(Locale.getDefault(), "%.1f", result));
    }

    private Double convertMass(Double value, String units, Boolean flag)
    {
        HashMap<String, Double> massRate = new HashMap<>();

        massRate.put("Milligram", 1000.00);
        massRate.put("Decigram", 100.00);
        massRate.put("Centigram", 10.00);
        massRate.put("Gram", 1.00);
        massRate.put("Dekagram", 1.00/10.00);
        massRate.put("Hectogram", 1.00/100.00);
        massRate.put("Kilogram", 1.00/1000.00);
        massRate.put("Tonne", massRate.get("Kilogram")/1000.00);

        Double ans;

        if(flag.equals(true))
        {
            ans =  value/ massRate.get(units);
            return ans;
        }

        for(Map.Entry<String, Double> exchanges : massRate.entrySet())
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
        setContentView(R.layout.activity_mass_converter);

        Spinner tempSpinner1 = (Spinner) findViewById(R.id.massIn);
        Spinner tempSpinner2 = (Spinner) findViewById(R.id.massOut);

        EditText edit1 = (EditText) findViewById(R.id.MassValue);

        if(edit1.getText().toString().length() == 0)
            edit1.setText("0.0");

        //Log.i("info",Double.toString(givenAmt));

        Button convert = (Button) findViewById(R.id.ConvertMass);

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
                    displayResult(convertMass(givenValue, tempSpinner2.getSelectedItem().toString(), false));
                }
                else
                {
                    Double convertFrom = convertMass(givenValue, tempSpinner1.getSelectedItem().toString(), true);
                    displayResult(convertMass(convertFrom, tempSpinner2.getSelectedItem().toString(), false));
                }
            }
        });
    }
}