package com.lozasolutions.printerapp;
import com.lozasolutions.printerapp.BillInfo;
import com.lozasolutions.printerapp.PrintResult;

interface IPrintService {
    PrintResult print(in BillInfo billInfo);
    void exit();
}
