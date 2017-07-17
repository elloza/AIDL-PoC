package com.lozasolutions.bankapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aidan Follestad (lozasolutions)
 */
public class BankService extends Service {

    private void log(String message) {
        Log.v("BankService", message);
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
        public BankResult[] listFiles(String path) throws RemoteException {

            /*
            Permissions

             */
            /*
            Context mCtx = getApplicationContext();
            String perm = "com.lozasolutions.mainapp";
            String errMsg = "fails";
            mCtx.enforceCallingOrSelfPermission(perm, errMsg);

            */


            log("Received list command for: " + path);
            List<BankResult> toSend = new ArrayList<>();
            // Generates a list of 1000 objects that aren't sent back to the binding Activity
            for (int i = 0; i < 1000; i++)
                toSend.add(new BankResult("/example/item" + (i + 1)));
            return toSend.toArray(new BankResult[toSend.size()]);
        }

        @Override
        public void exit() throws RemoteException {
            log("Received exit command.");
            stopSelf();
        }
    };
}
