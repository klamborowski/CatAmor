import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import net.danlew.android.joda.JodaTimeAndroid;

import pl.klamborowski.catamor.BuildConfig;
import pl.klamborowski.catamor.R;

/**
 * Created by Artur on 23.10.2016.
 */

public class CatAmorApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

        Logger.init(getString(R.string.app_name)).logLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE);
        JodaTimeAndroid.init(getApplicationContext());
    }
}
