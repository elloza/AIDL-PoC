package com.lozasolutions.bankapp;
import com.lozasolutions.bankapp.BankResult;

interface IBankService {
    BankResult obtainCurrencyRates(String from, String to);

    void exit();
}
