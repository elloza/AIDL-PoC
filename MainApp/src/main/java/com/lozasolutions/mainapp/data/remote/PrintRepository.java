package com.lozasolutions.mainapp.data.remote;

import com.lozasolutions.bankapp.BankResult;
import com.lozasolutions.printerapp.BillInfo;
import com.lozasolutions.printerapp.PrintResult;

import rx.Observable;

/**
 * Created by Loza on 17/07/2017.
 */

public interface PrintRepository {

    Observable<PrintResult> print(BillInfo billInfo);
}
