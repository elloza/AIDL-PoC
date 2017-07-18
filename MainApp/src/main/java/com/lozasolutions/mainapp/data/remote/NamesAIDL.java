package com.lozasolutions.mainapp.data.remote;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.lozasolutions.namesapp.INamesService;

import java.util.concurrent.TimeUnit;

import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by Loza on 17/07/2017.
 */

public class NamesAIDL implements NamesRepository {

    private Context context;

    public NamesAIDL(Context context){
        this.context = context;
    }

    @Override
    public Observable<String> getQuote(final Integer number) {
        return Observable.fromEmitter(new Action1<Emitter<String>>() {

            @Override
            public void call(final Emitter<String> emitter) {

                ServiceConnection namesServiceConnection = new ServiceConnection() {

                    INamesService namesService;

                    @Override
                    public void onServiceConnected(ComponentName className, IBinder service) {

                        namesService = INamesService.Stub.asInterface(service);
                        try {
                            emitter.onNext(namesService.getQuote(number).getQuote());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            emitter.onError(e);
                        }

                        try {
                            namesService.exit();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            emitter.onError(e);
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName className) {
                        namesService = null;
                    }
                };

                Intent serviceIntent2 = new Intent()
                        .setComponent(new ComponentName(
                                "com.lozasolutions.namesapp",
                                "com.lozasolutions.namesapp.NamesService"));
                context.startService(serviceIntent2);
                context.bindService(serviceIntent2, namesServiceConnection, BIND_AUTO_CREATE);

            }
        }, Emitter.BackpressureMode.LATEST).timeout(5, TimeUnit.SECONDS);
    }


}
