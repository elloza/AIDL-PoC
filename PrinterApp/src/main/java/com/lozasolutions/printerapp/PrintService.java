package com.lozasolutions.printerapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * @author Álvaro Lozano (lozasolutions)
 */
public class PrintService extends Service {

    private void log(String message) {
        Log.v("PrintService", message);
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

    private final IPrintService.Stub mBinder = new IPrintService.Stub() {

        @Override
        public PrintResult print(BillInfo billInfo) throws RemoteException {
            return new PrintResult("OK");
        }

        @Override
        public void exit() throws RemoteException {
            log("Received exit command.");
            stopSelf();
        }
    };
}
