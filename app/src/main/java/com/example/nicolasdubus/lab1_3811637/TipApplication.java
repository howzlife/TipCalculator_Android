package com.example.nicolasdubus.lab1_3811637;

import android.app.Application;

/**
 * Created by nicolasdubus on 2016-02-28.
 */
public class TipApplication extends Application {

    private double defaultTipPercentage = 0.15;
    private double defaultTaxPercentage = 0.13;
    private String currency = "$";

    public String getCurrency() {
        return this.currency;
    }

    public double getDefaultTipPercentage() {
        return defaultTipPercentage;
    }

    public double getDefaultTaxPercentage() {
        return defaultTaxPercentage;
    }

    public void setCurrency(String newCurrency) {
        this.currency = newCurrency;
    }

    public void setDefaultTipPercentage(double percentage) {
        this.defaultTipPercentage = percentage;
    }

    public void setDefaultTaxPercentage(double percentage) {
        this.defaultTaxPercentage = percentage;
    }
}
