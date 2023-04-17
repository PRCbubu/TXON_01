package com.example.unitconterto;
import android.annotation.SuppressLint;
import android.os.Bundle;
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
    private void toastMsg(String Msg)
    {
        Toast.makeText(getApplicationContext(), Msg, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void displayResult(Double result)
    {
        TextView ans = (TextView) findViewById(R.id.AnswerCurrency);
        ans.setText("Amount is: "+String.format(Locale.getDefault(), "%.2f", result));
    }

    private Double convertCurrency(Double amt, String country, Boolean flag)
    {
        HashMap<String, Double> exchangeRate = new HashMap<>();

        exchangeRate.put("USD", 1.0);
        exchangeRate.put("INR", 81.85);
        exchangeRate.put("EUR", 0.9);
        exchangeRate.put("YEN", 133.78);
        exchangeRate.put("GBP", 0.81);
        exchangeRate.put("AUD", 1.49);
        exchangeRate.put("CAD", 1.34);

        if(flag.equals(true))
        {
            return (1/exchangeRate.get(country))*amt;
        }

        for(Map.Entry<String, Double> exchanges : exchangeRate.entrySet())
        {
            if(exchanges.getKey().equals(country))
            {
                //toastMsg("Executed successfully");
                return amt * exchanges.getValue();
            }
        }
        return null;
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

        //Log.i("info",Double.toString(givenAmt));

        Button convert = (Button) findViewById(R.id.ConvertCurr);

        convert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Double givenAmt = Double.parseDouble(edit1.getText().toString());

                if(currSpinner1.getSelectedItem().toString().equals(currSpinner2.getSelectedItem().toString()))
                    toastMsg("You don't need to convert");
                else if (currSpinner1.getSelectedItem().toString().equals("USD"))
                {
                    displayResult(convertCurrency(givenAmt, currSpinner2.getSelectedItem().toString(), false));
                }
                else
               {
                   Double convertFrom = convertCurrency(givenAmt, currSpinner1.getSelectedItem().toString(), true);
                   displayResult(convertCurrency(convertFrom, currSpinner2.getSelectedItem().toString(), false));
               }
            }
        });
    }
}
