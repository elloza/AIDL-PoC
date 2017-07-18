package com.lozasolutions.data;


import com.lozasolutions.bankapp.BankResult;

import rx.Observable;

/**
 * Created by Loza on 17/07/2017.
 */

public interface BankRepository {

    Observable<BankResult> getBankResultRate(String from, String to);

}
