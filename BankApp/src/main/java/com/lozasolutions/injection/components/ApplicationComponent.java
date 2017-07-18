package com.lozasolutions.injection.components;

import android.app.Application;
import android.content.Context;

import com.lozasolutions.bankapp.BankService;
import com.lozasolutions.data.remote.BankRemoteService;
import com.lozasolutions.injection.ApplicationContext;
import com.lozasolutions.injection.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Loza on 17/07/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BankService syncService);

    @ApplicationContext
    Context context();

    Application application();
    BankRemoteService bankService();

}