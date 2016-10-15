package com.abhay23.blrdroiddevfestdemo.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Marks a component or a dependency to be available only to the activity it has been created in.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME) public @interface ActivityScope {
}