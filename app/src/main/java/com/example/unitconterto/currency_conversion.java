package com.example.unitconterto;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class currency_conversion extends AppCompatActivity
{
    private void toastMsg()
    {
        Toast.makeText(this, "You don't need to convert", Toast.LENGTH_SHORT).show();
    }

    private void displayResult(Double result)
    {
        TextView ans = (TextView) findViewById(R.id.AnswerCurrency);
        ans.setText(String.format(Locale.getDefault(), ".2f", result));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_conversion);

        Spinner currSpinner1 = (Spinner) findViewById(R.id.currencyIn);
        Spinner currSpinner2 = (Spinner) findViewById(R.id.currencyOut);

        EditText edit1 = (EditText) findViewById(R.id.CurrencyAmt);

        if(edit1.getText().toString().length() == 0)
            edit1.setText("0.0");

        HashMap<String, Double> exchangeRate = new HashMap<>();

        exchangeRate.put("USD", 1.0);
        exchangeRate.put("INR", 81.85);
        exchangeRate.put("EUR", 0.9);
        exchangeRate.put("YEN", 133.78);
        exchangeRate.put("GBP", 0.81);
        exchangeRate.put("AUD", 1.49);
        exchangeRate.put("CAD", 1.34);

        double givenAmt = Double.parseDouble(edit1.getText().toString());
        Log.i("info",Double.toString(givenAmt));

        Button convert = (Button) findViewById(R.id.ConvertCurr);

        convert.setOnClickListener(view ->
        {
            if(currSpinner1.getSelectedItem() == currSpinner2.getSelectedItem())
                toastMsg();
            else if (currSpinner1.getSelectedItem() == "USD")
            {
                for(Map.Entry<String, Double> exchanges : exchangeRate.entrySet())
                {
                    if(exchanges.getKey() == currSpinner2.getSelectedItem())
                    {
                        displayResult(givenAmt * exchanges.getValue());
                        Log.i("info", ""+ givenAmt * exchanges.getValue());
                    }
                }
            }
        });
    }
}
