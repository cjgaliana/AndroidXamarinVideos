package com.cjgaliana.xamarinvideos;

import android.app.Application;
import android.content.Context;

/**
 * Created by camilo on 15/06/2016.
 */
public class AndroidApplication extends Application {


    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize stuff

    }
}
