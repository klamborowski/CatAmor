package pl.klamborowski.catamor;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by Artur on 23.10.2016.
 */

public class CatAmorApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());

        Fresco.initialize(this);

        Logger.init(getString(R.string.app_name)).logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
        JodaTimeAndroid.init(getApplicationContext());

        Hawk.init(getApplicationContext())
                .build();
    }
}
