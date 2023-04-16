package com.example.unitconterto;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class currency_conversion extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_conversion);

        Spinner first = (Spinner) findViewById(R.id.currencyIn);
        Spinner second = (Spinner) findViewById(R.id.currencyOut);
        TextView secondLine = (TextView) findViewById(R.id.displayToCurrency);
        EditText ans = (EditText) findViewById(R.id.CurrencyAmt);
        TextView answerCurrency = (TextView) findViewById(R.id.AnswerCurrency);

        ans.animate().alpha(0);
        answerCurrency.animate().alpha(0);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.CurrencyUnits, android.R.layout.simple_dropdown_item_1line);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.CurrencyUnits, android.R.layout.simple_dropdown_item_1line);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        first.setAdapter(adapter1);
        second.setAdapter(adapter2);

        first.setOnItemSelectedListener(this);
        second.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        Toast.makeText(this, ""+ adapterView.getItemIdAtPosition(i), Toast.LENGTH_SHORT).show();
        /*if(adapterView.getItemAtPosition())
            Toast.makeText(this, "You don't need to convert currency", Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}
