package uz.catsidroid.cardioaicomposable.presentation.splash

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import cafe.adriel.voyager.androidx.AndroidScreen
import kotlinx.coroutines.delay
import uz.catsidroid.cardioaicomposable.R
import uz.catsidroid.cardioaicomposable.data.SharedPref
import uz.catsidroid.cardioaicomposable.utils.MyLanguage
import java.util.Locale

@Composable
fun SplashScreen(navController: NavController) {

    var currentLanguage by remember { mutableStateOf("uz") }

    changeLanguage(currentLanguage)

    LaunchedEffect(key1 = true) {
//        delay(2000)
//        navController.navigate(
//            route = "intro1_screen",
//            navOptions = NavOptions.Builder()
//                .setPopUpTo(saveState = false, route = "splash_screen", inclusive = true).build()
//        )

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.splash1), contentScale = ContentScale.Crop)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)

            )
            Text(
                text = "Cardio AI",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,

                )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Image(
//                painter = painterResource(id = R.drawable.shap),
//                contentDescription = "",
//                modifier = Modifier.fillMaxSize()
//
//            )

            Text(
                text = "Выберите  язык:\nTilni tanlang:",
                color = Color.White,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(onClick = {
                currentLanguage = "uz"
                MyLanguage.lan="uz"
                if (!SharedPref.getInstance().getStart()){
                    SharedPref.getInstance().setStarts(true)
                    navController.navigate(
                        route = "intro1_screen",
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(saveState = false, route = "splash_screen", inclusive = true).build()
                    )
                }
                else {
                    navController.navigate(
                        route = "main_screen",
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(saveState = false, route = "splash_screen", inclusive = true).build()
                    )
                }



            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 42.dp), colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Red
            )) {
                Text(text = "O`zbekcha")
            }
            Button(
                onClick = {
                    currentLanguage = "ru"
                    MyLanguage.lan="ru"
                    if (!SharedPref.getInstance().getStart()){
                        SharedPref.getInstance().setStarts(true)

                        navController.navigate(
                            route = "intro1_screen",
                            navOptions = NavOptions.Builder()
                                .setPopUpTo(saveState = false, route = "splash_screen", inclusive = true).build()
                        )
                    }
                    else {
                        navController.navigate(
                            route = "main_screen",
                            navOptions = NavOptions.Builder()
                                .setPopUpTo(saveState = false, route = "splash_screen", inclusive = true).build()
                        )
                    }



                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 42.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Red
                )
            ) {
                Text(text = "Русский")
            }

            Spacer(modifier = Modifier.height(36.dp))

        }
    }



}

@Composable
@SuppressLint("ObsoleteSdkInt")
fun changeLanguage(language: String) {

    val activity = LocalContext.current as Activity
    val config = activity.resources.configuration
    val locale = Locale(language)
    Locale.setDefault(locale)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        config.setLocale(locale)
    else
        config.locale = locale

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        activity.createConfigurationContext(config)
    activity.resources.updateConfiguration(config, activity.resources.displayMetrics)
}




