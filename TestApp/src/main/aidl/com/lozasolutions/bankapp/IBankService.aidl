package com.lozasolutions.bankapp;
import com.lozasolutions.bankapp.BankResult;

interface IBankService {
    BankResult[] listFiles(String path);

    void exit();
}

