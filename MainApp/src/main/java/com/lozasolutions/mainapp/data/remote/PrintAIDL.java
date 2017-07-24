package com.lozasolutions.mainapp.data.remote;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.lozasolutions.printerapp.IPrintService;
import com.lozasolutions.printerapp.BillInfo;
import com.lozasolutions.printerapp.PrintResult;

import java.util.concurrent.TimeUnit;

import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by Loza on 17/07/2017.
 */

public class PrintAIDL implements PrintRepository {

    private Context context;

    public PrintAIDL(Context context){
        this.context = context;
    }

    @Override
    public Observable<PrintResult> print(final BillInfo billInfo) {
        return Observable.fromEmitter(new Action1<Emitter<PrintResult>>() {

            @Override
            public void call(final Emitter<PrintResult> emitter) {

                ServiceConnection namesServiceConnection = new ServiceConnection() {

                    IPrintService printService;

                    @Override
                    public void onServiceConnected(ComponentName className, IBinder service) {

                        printService = IPrintService.Stub.asInterface(service);
                        try {
                            emitter.onNext(printService.print(billInfo));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            emitter.onError(e);
                        }

                        try {
                            printService.exit();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            emitter.onError(e);
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName className) {
                        printService = null;
                    }
                };

                Intent serviceIntent2 = new Intent()
                        .setComponent(new ComponentName(
                                "com.lozasolutions.namesapp",
                                "com.lozasolutions.namesapp.PrintService"));
                context.startService(serviceIntent2);
                context.bindService(serviceIntent2, namesServiceConnection, BIND_AUTO_CREATE);

            }
        }, Emitter.BackpressureMode.LATEST).timeout(5, TimeUnit.SECONDS);
    }


}
