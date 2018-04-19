package cn.xp.jike;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;


public class App extends Application {

    private static App application;

    public static Context getContext() {
        return application;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
