package com.example.suraj.petuniverse;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by deepank on 3/19/2016.
 */
public class MyApplication extends Application {
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
