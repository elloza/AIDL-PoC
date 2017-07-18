package com.lozasolutions.application;

import android.app.Application;
import android.content.Context;

import com.lozasolutions.bankapp.BuildConfig;
import com.lozasolutions.injection.components.ApplicationComponent;
import com.lozasolutions.injection.components.DaggerApplicationComponent;
import com.lozasolutions.injection.modules.ApplicationModule;

import timber.log.Timber;

/**
 * Created by Loza on 17/07/2017.
 */

public class BankApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static BankApplication get(Context context) {
        return (BankApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}