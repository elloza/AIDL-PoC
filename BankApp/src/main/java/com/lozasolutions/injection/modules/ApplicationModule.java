package com.lozasolutions.injection.modules;

/**
 * Created by Loza on 17/07/2017.
 */

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.lozasolutions.data.BankRepository;
import com.lozasolutions.data.DataRespositoryNetwork;
import com.lozasolutions.data.remote.BankRemoteService;
import com.lozasolutions.injection.ApplicationContext;

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
    BankRemoteService provideBankRemoteService() {
        return BankRemoteService.Creator.newBankService();
    }

    @Provides
    @Singleton
    BankRepository provideBankRespository(BankRemoteService remoteService, Gson gson) {
        return new DataRespositoryNetwork(remoteService,gson);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

}