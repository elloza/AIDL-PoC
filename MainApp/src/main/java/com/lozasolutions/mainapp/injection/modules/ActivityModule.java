package com.lozasolutions.mainapp.injection.modules;

import android.app.Activity;
import android.content.Context;

import com.lozasolutions.mainapp.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Loza on 17/07/2017.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}