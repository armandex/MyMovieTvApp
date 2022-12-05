package aguinaga.armando.mymovietvapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import aguinaga.armando.mymovietvapp.BuildConfig

@HiltAndroidApp
class MyApplication: Application() {


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}