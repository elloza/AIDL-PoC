package com.lozasolutions.mainapp.data.remote;

import com.lozasolutions.bankapp.BankResult;

import rx.Observable;

/**
 * Created by Loza on 17/07/2017.
 */

public interface BankRepository {

    Observable<BankResult> getBankLatestRates(String from, String to);
}
