package com.lozasolutions.mainapp.base;

import android.app.Application;
import android.content.Context;

import com.lozasolutions.mainapp.BuildConfig;
import com.lozasolutions.mainapp.injection.components.ApplicationComponent;
import com.lozasolutions.mainapp.injection.components.DaggerApplicationComponent;
import com.lozasolutions.mainapp.injection.modules.ApplicationModule;

import timber.log.Timber;



/**
 * Created by Loza on 17/07/2017.
 */

public class MainApp extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static MainApp get(Context context) {
        return (MainApp) context.getApplicationContext();
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
