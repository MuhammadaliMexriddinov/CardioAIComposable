package uz.catsidroid.cardioaicomposable.presentation.settings


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import timber.log.Timber
import uz.catsidroid.cardioaicomposable.R
import uz.catsidroid.cardioaicomposable.data.LocationData
import uz.catsidroid.cardioaicomposable.ui.theme.CardioAIComposableTheme
import uz.catsidroid.cardioaicomposable.utils.myDistance

@Composable
fun LocationScreen(navController: NavController) {

    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    var x: Double = 0.0
    var y: Double = 0.0

     var formatted1 = remember { mutableStateOf("24") }
     var formatted2 = remember { mutableStateOf("45") }
     var formatted3 = remember { mutableStateOf("48") }
     var formatted4 = remember { mutableStateOf("56") }
     var formatted5 = remember { mutableStateOf("61") }
     var formatted6 = remember { mutableStateOf("89") }
     var formatted7 = remember { mutableStateOf("94") }



    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val locationProvider: String = LocationManager.GPS_PROVIDER
    try {
        val lastKnownLocation: Location? = locationManager.getLastKnownLocation(locationProvider)
        lastKnownLocation?.let {
            val latitude = it.latitude
            val longitude = it.longitude
            // Use latitude and longitude here

            val d1 = myDistance(
                latitude,
                longitude,
                41.34863610705355,
                69.17355590331238
            )
             //formatted1.value = String.format("%.2f", d1)
             formatted1.value =d1.toString()


            val d2 = myDistance(
                latitude,
                longitude,
                41.27334921745024,
                69.20968725396779
            )
//             formatted2.value = String.format("%.2f", d1)
            formatted2.value =d2.toString()
            val d3 = myDistance(
                latitude,
                longitude,
                41.33097877335287,
                69.27158557672034
            )
//            formatted3.value = String.format("%.2f", d1)
            formatted3.value =d3.toString()
            val d4 = myDistance(
                latitude,
                longitude,
                41.26951717848392,
                69.2170344994405
            )
            // formatted4.value = String.format("%.2f", d1)
            formatted4.value =d4.toString()

            val d5 = myDistance(
                latitude,
                longitude,
                41.310051163596434,
                69.29967641293375
            )
           //  formatted5.value = String.format("%.2f", d1)
            formatted5.value =d5.toString()

            val d6 = myDistance(
                latitude,
                longitude,
                41.32436976459786,
                69.29998233274422
            )

            // formatted6.value = String.format("%.2f", d1)

            formatted6.value =d6.toString()

            val d7 = myDistance(
                latitude,
                longitude,
                41.301834919314146,
                69.28340448409745
            )

            // formatted7.value = String.format("%.2f", d1
            formatted7.value =d7.toString()



        }
    } catch (e: SecurityException) {
        e.printStackTrace()
    }

    val data: List<LocationData> = listOf(
        LocationData(
            stringResource(id = R.string.loca1_0),
            stringResource(id = R.string.loca1_1),
            stringResource(id = R.string.loca1_2),
            stringResource(
                id = R.string.distance
            ) + " " + formatted1.value + " km"
        ),
        LocationData(
            stringResource(id = R.string.loca2_0),
            stringResource(id = R.string.loca2_1),
            stringResource(id = R.string.loca2_2),
            stringResource(
                id = R.string.distance
            ) + " " + formatted2.value + " km"
        ),
        LocationData(
            stringResource(id = R.string.loca3_0),
            stringResource(id = R.string.loca3_1),
            stringResource(id = R.string.loca3_2),
            stringResource(
                id = R.string.distance
            ) + " " + formatted3.value + " km"
        ),
        LocationData(
            stringResource(id = R.string.loca4_0),
            stringResource(id = R.string.loca4_1),
            stringResource(id = R.string.loca4_2),
            stringResource(
                id = R.string.distance
            ) + " " + formatted4.value + " km"
        ),
        LocationData(
            stringResource(id = R.string.loca5_0),
            stringResource(id = R.string.loca5_1),
            stringResource(id = R.string.loca5_2),
            stringResource(
                id = R.string.distance
            ) + " " + formatted5.value + " km"
        ),
        LocationData(
            stringResource(id = R.string.loca6_0),
            stringResource(id = R.string.loca6_1),
            stringResource(id = R.string.loca6_2),
            stringResource(
                id = R.string.distance
            ) + " " + formatted6.value + " km"
        ),
        LocationData(
            stringResource(id = R.string.loca7_0),
            stringResource(id = R.string.loca7_1),
            stringResource(id = R.string.loca7_2),
            stringResource(
                id = R.string.distance
            ) + " " + formatted7.value + " km"
        ),
    )



    CardioAIComposableTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .height(76.dp)
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                              navController.navigate("main_screen")
                    },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }

                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Tibbiyot Markazlari",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,

                    )
            }

            LazyColumn() {
                items(data) {
                    LocationItem(
                        fullName = it.fullName,
                        orientation = it.orientation,
                        phoneNumber = it.phoneNumber,
                        distance = it.distance
                    )
                }
            }
        }
    }

}

