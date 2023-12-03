package uz.catsidroid.cardioaicomposable.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


class App : Application(){
    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}