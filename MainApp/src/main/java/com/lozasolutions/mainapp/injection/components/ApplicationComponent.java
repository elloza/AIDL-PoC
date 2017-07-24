package com.lozasolutions.mainapp.injection.components;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.lozasolutions.mainapp.data.remote.BankRepository;
import com.lozasolutions.mainapp.data.remote.PrintRepository;
import com.lozasolutions.mainapp.injection.ApplicationContext;
import com.lozasolutions.mainapp.injection.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Loza on 17/07/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    BankRepository bankRepository();

    PrintRepository namesRepository();

    Gson gson();

}