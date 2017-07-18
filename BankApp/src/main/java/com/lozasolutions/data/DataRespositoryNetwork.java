package com.lozasolutions.data;

import com.google.gson.Gson;
import com.lozasolutions.bankapp.BankResult;
import com.lozasolutions.data.model.BankResultResponse;
import com.lozasolutions.data.remote.BankRemoteService;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Loza on 17/07/2017.
 */

@Singleton
public class DataRespositoryNetwork implements BankRepository {

    private final BankRemoteService bankService;
    private Gson gson;

    @Inject
    public DataRespositoryNetwork(BankRemoteService bankService, Gson gson) {
        this.bankService = bankService;
        this.gson = gson;
    }

    @Override
    public Observable<BankResult> getBankResultRate(String from, String to) {
        return bankService.getLatest(from+","+to).map(new Func1<BankResultResponse, BankResult>() {
            @Override
            public BankResult call(BankResultResponse bankResultResponse) {
                //TODO yes... this is a little weird but AIDL is not friend of auto value so we will send the JSON
                return new BankResult(gson.toJson(bankResultResponse));
            }
        });
    }
}