package com.lozasolutions.mainapp.injection.components;

/**
 * Created by Loza on 17/07/2017.
 */


import com.lozasolutions.mainapp.injection.modules.ActivityModule;
import com.lozasolutions.mainapp.injection.scopes.ConfigPersistent;

import dagger.Component;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link com.lozasolutions.mainapp.base.BaseActivity} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

}
