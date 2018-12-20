package com.presentedbykaran.bookshelf;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by karan on 18/12/18.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
