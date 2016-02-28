package com.example.nicolasdubus.lab1_3811637;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.SeekBar.*;
import android.view.View.OnClickListener;


/**
 * Created by nicolasdubus on 2016-02-28.
 */
public class SummaryActivity extends Activity implements OnClickListener, OnSeekBarChangeListener, OnCheckedChangeListener, View.OnFocusChangeListener {

    private SeekBar tipSlider, taxSlider;
    private Switch currencySwitch;
    private TextView tipString, taxString;
    private Button returnToMainButton;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tipSlider = (SeekBar)this.findViewById(R.id.tipSlider);
        taxSlider = (SeekBar)this.findViewById(R.id.taxSlider);
        tipString = (TextView)this.findViewById(R.id.tipString);
        taxString = (TextView)this.findViewById(R.id.taxString);
        currencySwitch = (Switch)this.findViewById(R.id.currencySwitch);
        returnToMainButton = (Button)this.findViewById(R.id.returnToMain);

        tipString.setOnFocusChangeListener(this);
        taxString.setOnFocusChangeListener(this);
        returnToMainButton.setOnClickListener(this);
        tipSlider.setOnSeekBarChangeListener(this);
        taxSlider.setOnSeekBarChangeListener(this);
        currencySwitch.setOnCheckedChangeListener(this);

        double defaultTip = (((TipApplication) this.getApplication()).getDefaultTipPercentage() * 100);
        double defaultTax = (((TipApplication)this.getApplication()).getDefaultTaxPercentage() * 100);

        tipString.setText(String.format("%.0f", defaultTip) + "%");
        taxString.setText(String.format("%.0f", defaultTax) + "%");

        String defaultCurrency = ((TipApplication)this.getApplication()).getCurrency();
        if (defaultCurrency.equals("$")) {
            currencySwitch.setChecked(false);
        } else {
            currencySwitch.setChecked(true);
        }

        tipSlider.setProgress((int)defaultTip);
        taxSlider.setProgress((int)defaultTax);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Double value = Double.valueOf(progress) / 100;
        if (seekBar == tipSlider) {
            tipString.setText(progress + "%");
            ((TipApplication)this.getApplication()).setDefaultTipPercentage(value);
        } else if (seekBar == taxSlider) {
            taxString.setText(progress + "%");
            ((TipApplication)this.getApplication()).setDefaultTaxPercentage(value);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == currencySwitch) {
            if (isChecked) {
                ((TipApplication)this.getApplication()).setCurrency("€");
            } else {
                ((TipApplication)this.getApplication()).setCurrency("$");
            }
        }
    }

    public void onFocusChange(View v, boolean hasFocus) {
        if (v == tipString && hasFocus == false) {
            double tipPercentValue = Double.parseDouble(((TextView)v).getText().toString().replace("%", ""));
            if (tipPercentValue > 50 ) {
                tipPercentValue = 50;
            } else if (tipPercentValue < 0) {
                tipPercentValue = 0;
            }

            tipSlider.setProgress((int)tipPercentValue);
            ((TipApplication)this.getApplication()).setDefaultTipPercentage(tipPercentValue / 100);

        } else if (v == taxString && hasFocus == false) {
            double taxPercentValue = Double.parseDouble(((TextView)v).getText().toString().replace("%", ""));
            if (taxPercentValue > 50 ) {
                taxPercentValue = 50;
            } else if (taxPercentValue < 0) {
                taxPercentValue = 0;
            }

            taxSlider.setProgress((int)taxPercentValue);
            ((TipApplication)this.getApplication()).setDefaultTaxPercentage(taxPercentValue / 100);

        }
    }

    @Override
    public void onClick(View v) {
        if (v == returnToMainButton) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }
}
