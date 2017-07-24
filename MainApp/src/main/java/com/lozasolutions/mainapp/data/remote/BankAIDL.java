package com.lozasolutions.mainapp.data.remote;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.lozasolutions.bankapp.BankInfo;
import com.lozasolutions.bankapp.BankResult;
import com.lozasolutions.bankapp.IBankResultListener;
import com.lozasolutions.bankapp.IBankService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by Loza on 17/07/2017.
 */

public class BankAIDL implements BankRepository {

    private Context context;

    public BankAIDL(Context context){
        this.context = context;
    }

    @Override
    public Observable<BankResult> getBankLatestRates(String from, String to) {
        return requestBankInfo(from,to);
    }

    private Observable<BankResult> requestBankInfo(final String from, final String to){

        return Observable.fromEmitter(new Action1<Emitter<BankResult>>() {

            @Override
            public void call(final Emitter<BankResult> emitter) {

                ServiceConnection bankConnectionService = new ServiceConnection() {

                    IBankService bankService;
                    IBankResultListener listener = new IBankResultListener() {
                        @Override
                        public void sendResult(BankResult result) throws RemoteException {
                            emitter.onNext(result);
                        }

                        @Override
                        public IBinder asBinder() {
                            return null;
                        }
                    };

                    @Override
                    public void onServiceConnected(ComponentName className, IBinder service) {

                        bankService = IBankService.Stub.asInterface(service);
                        try {
                            bankService.obtainCurrencyRates(new BankInfo(""),listener);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            emitter.onError(e);
                        }

                    }

                    @Override
                    public void onServiceDisconnected(ComponentName className) {
                        emitter.onError(new Exception("Disconnected"));
                        bankService = null;
                    }
                };

                Intent serviceIntent = new Intent()
                        .setComponent(new ComponentName(
                                "com.lozasolutions.bankapp",
                                "com.lozasolutions.bankapp.BankService"));
                context.startService(serviceIntent);
                context.bindService(serviceIntent, bankConnectionService, BIND_AUTO_CREATE);

            }
        }, Emitter.BackpressureMode.LATEST).timeout(5, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(Schedulers.io());

    }

}
