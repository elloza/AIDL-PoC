package com.lozasolutions.namesapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * @author Álvaro Lozano (lozasolutions)
 */
public class NamesService extends Service {

    private void log(String message) {
        Log.v("NamesService", message);
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

    private final INamesService.Stub mBinder = new INamesService.Stub() {

        @Override
        public NameQuote getQuote(int number) throws RemoteException {
            return new NameQuote("Implement service");
        }

        @Override
        public void exit() throws RemoteException {
            log("Received exit command.");
            stopSelf();
        }
    };
}
