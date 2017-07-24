package com.lozasolutions.mainapp.injection.modules;

/**
 * Created by Loza on 17/07/2017.
 */

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.lozasolutions.mainapp.data.remote.BankAIDL;
import com.lozasolutions.mainapp.data.remote.BankRepository;
import com.lozasolutions.mainapp.data.remote.PrintAIDL;
import com.lozasolutions.mainapp.data.remote.PrintRepository;
import com.lozasolutions.mainapp.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    BankRepository provideBankRepository(@ApplicationContext Context context) {
        return new BankAIDL(context);
    }

    @Provides
    @Singleton
    PrintRepository provideNamesRepository(@ApplicationContext Context context) {
        return new PrintAIDL(context);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

}