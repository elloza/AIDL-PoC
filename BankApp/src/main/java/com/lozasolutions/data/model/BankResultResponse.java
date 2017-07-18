package com.lozasolutions.data.model;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Loza on 17/07/2017.
 */

public class BankResultResponse {

    String base;
    Date date;
    HashMap<String,Float> rates;


    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public HashMap<String, Float> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, Float> rates) {
        this.rates = rates;
    }
}
