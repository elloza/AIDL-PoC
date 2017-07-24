package com.lozasolutions.bankapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.lozasolutions.application.BankApplication;
import com.lozasolutions.data.DataRespositoryNetwork;

import javax.inject.Inject;

import rx.schedulers.Schedulers;

/**
 * @author Álvaro Lozano (lozasolutions)
 */
public class BankService extends Service {

    @Inject
    DataRespositoryNetwork bankRespository;

    private void log(String message) {
        Log.v("BankRemoteService", message);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BankApplication.get(this).getComponent().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log("Received start command.");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        log("Received binding.");
        return mBinder;
    }

    private final IBankService.Stub mBinder = new IBankService.Stub() {
        @Override
        public void obtainCurrencyRates(BankInfo bankInfo, IBankResultListener resultListerner) throws RemoteException {
            /*
            Permissions
             */
            Context mCtx = getApplicationContext();
            String perm = "com.lozasolutions.bankapp.requestdata";
            String errMsg = "You need request data permission";
            mCtx.enforceCallingOrSelfPermission(perm, errMsg);

            try {
                bankRespository.getBankResultRate("", "").subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).toBlocking().firstOrDefault(null);


            }catch (Exception e){
                throw new RemoteException(e.getMessage());
            }
        }

        @Override
        public void exit() throws RemoteException {
            log("Received exit command.");
            stopSelf();
        }
    };
}
