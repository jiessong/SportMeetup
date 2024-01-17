package com.example.sportsdating.core;

import android.app.Application;

/**
 *  Initialize the context which is to get persistent data from internal storage or assets folder etc.
 *  source: https://guides.codepath.com/android/Understanding-the-Android-Application-Class
 */
public class SportDatingApplication extends Application {
    private static SportDatingApplication mycontext;
    @Override
    public void onCreate() {
        super.onCreate();
        this.mycontext = this;
    }

    public static SportDatingApplication getMycontext() {
        return mycontext;
    }
}
