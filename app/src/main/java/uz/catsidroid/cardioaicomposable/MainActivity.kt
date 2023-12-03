package uz.catsidroid.cardioaicomposable

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.size.Size
import uz.catsidroid.cardioaicomposable.presentation.intro.Intro1Screen
import uz.catsidroid.cardioaicomposable.presentation.intro.Intro2Screen
import uz.catsidroid.cardioaicomposable.presentation.intro.Intro3Screen
import uz.catsidroid.cardioaicomposable.presentation.main.MainScreen
import uz.catsidroid.cardioaicomposable.presentation.main.MenuItems
import uz.catsidroid.cardioaicomposable.presentation.main.StartScreen
import uz.catsidroid.cardioaicomposable.presentation.settings.LocationScreen
import uz.catsidroid.cardioaicomposable.presentation.splash.SplashScreen
import uz.catsidroid.cardioaicomposable.ui.theme.CardioAIComposableTheme
import java.util.Locale

class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            CardioAIComposableTheme {
               Navigation()
           //     SplashScreen(navController = rememberNavController())
            }
        }
    }


}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("intro1_screen") {
            Intro1Screen(navController = navController)
        }
        composable("intro2_screen") {
            Intro2Screen(navController = navController)
        }
        composable("intro3_screen") {
            Intro3Screen(navController = navController)
        }
        composable("start_screen") {
            StartScreen(navController = navController)
        }
        composable("main_screen"){
            MainScreen(navController=navController)
        }
        composable("location_screen"){
            LocationScreen(navController=navController)
        }
    }
}

