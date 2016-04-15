package moe.banana.dagger2example;

import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

@Module
public class Application extends android.app.Application {

    private SharedPreferences mSharedPreferences;

    @Provides
    public SharedPreferences provideSharedPreferences() {
        if (mSharedPreferences == null) {
            mSharedPreferences = getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
        }
        return mSharedPreferences;
    }

}
