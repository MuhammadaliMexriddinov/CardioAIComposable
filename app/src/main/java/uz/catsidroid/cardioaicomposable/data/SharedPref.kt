package uz.catsidroid.cardioaicomposable.data

import android.content.Context
import android.content.SharedPreferences
import uz.catsidroid.cardioaicomposable.app.App
import java.util.UUID


/**
Mobile Developer
Creator:Mekhriddinov Muhammadali
 */
class SharedPref private constructor() {

    private val preferences: SharedPreferences =
        App.context!!.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)


    companion object {
        private lateinit var myPref: SharedPref

        fun getInstance(): SharedPref {
            if (!this::myPref.isInitialized) {
                myPref = SharedPref()
            }
            return myPref
        }
    }


    fun setStarts(s: Boolean) {
        preferences.edit().putBoolean("SET", s).apply()
    }

    fun getStart() = preferences.getBoolean("SET", false)
}