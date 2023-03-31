package com.example.practiceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText wBillTotal;
    private EditText wNumberPeople;
    private TextView wTipAmount;
    private TextView wTotalWithTip;
    private double totalWithTipAmount;
    private TextView wTotalPP;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wBillTotal =findViewById(R.id.billTotalWithTax);
        wNumberPeople=findViewById(R.id.NumberOfPeople);
        wTipAmount =findViewById(R.id.tipAmount);
        wTotalWithTip =findViewById(R.id.TotalWithTip);
        wTotalPP =findViewById(R.id.TotalPerPerson);
        radioGroup=findViewById(R.id.radiogrp);
    }
    public void radioButtonClicked(View v)
    {
        Log.d(TAG,"Total bill is"+ wBillTotal.getText().toString());
        if(wBillTotal.getText().toString().matches(""))
        {
            Log.d(TAG,"bill total with tax is empty"+ wBillTotal.getText().toString());
            Toast.makeText(this, "bill total with tax is empty", Toast.LENGTH_SHORT).show();
            radioGroup.clearCheck();
            return;
        }
        double billTotalInt=Double.parseDouble(wBillTotal.getText().toString());
        int tipPercent = 0;
        Log.d(TAG,"Tip percent is:-"+((RadioButton) v).getText());
        if(v.getId()==R.id.Percent12)
        {
            tipPercent=12;
            //Toast.makeText(this, "You chose 12%", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.Percent15)
        {
            tipPercent=15;
            //Toast.makeText(this, "You chose 15%", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.Percent18)
        {
            tipPercent=18;
            //Toast.makeText(this, "You chose 18%", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.Percent20)
        {
            tipPercent=20;
            //Toast.makeText(this, "You chose 20%", Toast.LENGTH_SHORT).show();
        }
        else
        {
            tipPercent=0;
            Log.d(TAG,"Unknown percent selected");
            return;
        }
        double actualtip=billTotalInt*tipPercent/100;

        wTipAmount.setText("$"+String.valueOf((double) Math.round(actualtip * 100) / 100));
        totalWithTipAmount=billTotalInt+actualtip;
        wTotalWithTip.setText("$"+String.valueOf((double) Math.round(totalWithTipAmount * 100) / 100));
    }
    public void calculateShare(View v)
    {
        if(wNumberPeople.getText().toString().matches("0")||wNumberPeople.getText().toString().matches(""))
        {
            Toast.makeText(this, "Number of people cannot be 0", Toast.LENGTH_SHORT).show();
            wTotalPP.setText("");
            return;
        }
        Double numberpeople=Double.parseDouble(wNumberPeople.getText().toString());
        wTotalPP.setText("$"+String.valueOf((double) Math.round(totalWithTipAmount/numberpeople * 100) / 100));
    }
    public void clear(View v)
    {
        wBillTotal.setText("");
        wNumberPeople.setText("");
        wTipAmount.setText("");
        wTotalWithTip.setText("");
        wTotalPP.setText("");
        radioGroup.clearCheck();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("TipAmount",wTipAmount.getText().toString());
        outState.putString("TotalWithTip",wTotalWithTip.getText().toString());
        outState.putString("TotalPP",wTotalPP.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        wTipAmount.setText(savedInstanceState.getString("TipAmount"));
        wTotalWithTip.setText(savedInstanceState.getString("TotalWithTip"));
        wTotalPP.setText(savedInstanceState.getString("TotalPP"));

    }


}