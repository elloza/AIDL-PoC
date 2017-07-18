package com.lozasolutions.mainapp.injection.scopes;

/**
 * Created by Loza on 17/07/2017.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * A scoping annotation to permit dependencies conform to the life of the
 * {@link com.lozasolutions.mainapp.injection.components.ConfigPersistentComponent}
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigPersistent {
}
