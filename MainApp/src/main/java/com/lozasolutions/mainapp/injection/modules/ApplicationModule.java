package com.lozasolutions.mainapp.injection.modules;

/**
 * Created by Loza on 17/07/2017.
 */

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.lozasolutions.mainapp.data.remote.BankAIDL;
import com.lozasolutions.mainapp.data.remote.BankRepository;
import com.lozasolutions.mainapp.data.remote.NamesAIDL;
import com.lozasolutions.mainapp.data.remote.NamesRepository;
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
    NamesRepository provideNamesRepository(@ApplicationContext Context context) {
        return new NamesAIDL(context);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

}