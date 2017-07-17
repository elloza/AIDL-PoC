package com.lozasolutions.testapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lozasolutions.bankapp.BankResult;
import com.lozasolutions.bankapp.IBankService;
import com.lozasolutions.namesapp.INamesService;
import com.lozasolutions.namesapp.NameQuote;

public class MainActivity extends AppCompatActivity {

    private IBankService bankService;
    private INamesService namesService;
    private TextView mLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLog = (TextView) findViewById(R.id.log);


        Intent serviceIntent = new Intent()
                .setComponent(new ComponentName(
                        "com.lozasolutions.bankapp",
                        "com.lozasolutions.bankapp.BankService"));
        mLog.setText("Starting Bank service…\n");
        startService(serviceIntent);
        mLog.append("Binding service…\n");
        bindService(serviceIntent, bankConnectionService, BIND_AUTO_CREATE);


        Intent serviceIntent2 = new Intent()
                .setComponent(new ComponentName(
                        "com.lozasolutions.namesapp",
                        "com.lozasolutions.namesapp.NamesService"));
        mLog.append("Starting Names service…\n");
        startService(serviceIntent2);
        mLog.append("Binding service…\n");
        bindService(serviceIntent2, namesServiceConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection bankConnectionService = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            mLog.append("Service binded!\n");
            bankService = IBankService.Stub.asInterface(service);

            performListingBank();
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            bankService = null;
            // This method is only invoked when the service quits from the other end or gets killed
            // Invoking exit() from the AIDL interface makes the Service kill itself, thus invoking this.
            mLog.append("Service disconnected.\n");
        }
    };

    private ServiceConnection namesServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            mLog.append("Service binded!\n");
            namesService = INamesService.Stub.asInterface(service);

            performListingNames();
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            bankService = null;
            // This method is only invoked when the service quits from the other end or gets killed
            // Invoking exit() from the AIDL interface makes the Service kill itself, thus invoking this.
            mLog.append("Service disconnected.\n");
        }
    };


    private void performListingBank() {
        mLog.append("Requesting file listing…\n");
        long start = System.currentTimeMillis();
        long end = 0;
        try {
            BankResult[] results = bankService.listFiles("/sdcard/testing");
            end = System.currentTimeMillis();
            int index = 0;
            mLog.append("Received " + results.length + " results:\n");
            for (BankResult o : results) {
                if (index > 20) {
                    mLog.append("\t -> Response truncated!\n");
                    break;
                }
                mLog.append("\t -> " + o.getPath() + "\n");
                index++;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mLog.append("File listing took " + (((double) end - (double) start) / 1000d) + " seconds, or " + (end - start) + " milliseconds.\n");
        try {
            bankService.exit();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void performListingNames() {
        mLog.append("Requesting file listing…\n");
        long start = System.currentTimeMillis();
        long end = 0;
        try {
            NameQuote[] results = namesService.listFiles("/sdcard/testing");
            end = System.currentTimeMillis();
            int index = 0;
            mLog.append("Received " + results.length + " results:\n");
            for (NameQuote o : results) {
                if (index > 20) {
                    mLog.append("\t -> Response truncated!\n");
                    break;
                }
                mLog.append("\t -> " + o.getPath() + "\n");
                index++;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mLog.append("File listing took " + (((double) end - (double) start) / 1000d) + " seconds, or " + (end - start) + " milliseconds.\n");
        try {
            namesService.exit();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
