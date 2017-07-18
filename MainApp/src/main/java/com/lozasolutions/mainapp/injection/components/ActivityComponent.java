package com.lozasolutions.mainapp.injection.components;

/**
 * Created by Loza on 17/07/2017.
 */

import com.lozasolutions.mainapp.injection.modules.ActivityModule;
import com.lozasolutions.mainapp.injection.scopes.PerActivity;
import com.lozasolutions.mainapp.ui.mainscreen.MainActivity;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}