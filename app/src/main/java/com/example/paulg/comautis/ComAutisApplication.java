package com.example.paulg.comautis;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;

/**
 * Created by paulg on 15/01/2018.
 */

public class ComAutisApplication extends Application implements
        HasActivityInjector
         {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjectorActivity;

    public void onCreate() {
        super.onCreate();

        /*DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this);*/
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjectorActivity;
    }

}
