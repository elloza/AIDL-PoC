package com.lozasolutions.bankapp;
import com.lozasolutions.bankapp.IBankService;
import com.lozasolutions.bankapp.BankInfo;
import com.lozasolutions.bankapp.IBankResultListener;


interface IBankService {

    void obtainCurrencyRates(in BankInfo bankinfo, IBankResultListener resultListener);
    void exit();
}
