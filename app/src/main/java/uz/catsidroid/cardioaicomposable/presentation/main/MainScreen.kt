package uz.catsidroid.cardioaicomposable.presentation.main

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DownloadForOffline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.catsidroid.cardioaicomposable.R
import uz.catsidroid.cardioaicomposable.data.remote.api.KardioApi
import uz.catsidroid.cardioaicomposable.presentation.dialog.ShowResultDialog
import uz.catsidroid.cardioaicomposable.presentation.splash.changeLanguage
import uz.catsidroid.cardioaicomposable.ui.theme.CardioAIComposableTheme
import uz.catsidroid.cardioaicomposable.utils.MyLanguage
import java.io.File
import java.io.FileOutputStream
import java.util.Locale
import java.util.concurrent.TimeUnit

@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavController) {

    var currentLanguage by remember { mutableStateOf(MyLanguage.lan) }

    changeLanguage(currentLanguage)

    var isLoading by remember {
        mutableStateOf(false)
    }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    val txtResponse = remember { mutableStateOf(false) }
    val copyTextResponse = remember { mutableStateOf(" ") }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(modifier = Modifier.fillMaxSize()) {
                Card(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .padding(start = 72.dp, end = 72.dp, top = 18.dp)
                        .align(Alignment.TopCenter),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ),
                    colors = CardDefaults.cardColors(containerColor = Color("#FF946D".toColorInt())),
                ) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            IconButton(onClick = {navController.navigate("location_screen") }) {
                                Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")
                            }
                        }
                        var isShowAbout by remember { mutableStateOf(false) }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            IconButton(onClick = { isShowAbout = true }) {
                                Icon(imageVector = Icons.Default.Info, contentDescription = "")
                            }
                            if (isShowAbout) {
                                Dialog(
                                    onDismissRequest = { isShowAbout = false }
                                ) {
                                    Surface(
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .wrapContentHeight(),
                                        shape = MaterialTheme.shapes.large
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .padding(16.dp)
                                                .verticalScroll(rememberScrollState())
                                                .weight(weight = 1f, fill = false),
                                            verticalArrangement = Arrangement.Bottom,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = stringResource(id = R.string.info_0),
                                                fontSize = 22.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black
                                            )
                                            Text(
                                                text = stringResource(id = R.string.info),
                                                color = Color.Black,
                                                fontSize = 16.sp,
                                                textAlign = TextAlign.Center,
                                            )
                                            Button(
                                                onClick = { isShowAbout = false },
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color("#FD8254".toColorInt()),
                                                )
                                            ) {
                                                Text(text = "OK")
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            var isShowDialog by remember { mutableStateOf(false) }


                            changeLanguage(currentLanguage)
                            IconButton(onClick = {
                                isShowDialog = true
                            }) {
                                Icon(imageVector = Icons.Default.Language, contentDescription = "")
                            }
                            if (isShowDialog) {
                                Dialog(
                                    onDismissRequest = { isShowDialog = false }
                                ) {
                                    Surface(
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .wrapContentHeight(),
                                        shape = MaterialTheme.shapes.large
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(16.dp),
                                            verticalArrangement = Arrangement.Bottom,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "Выберите  язык:\nTilni tanlang:",
                                                color = Color.Red,
                                                fontSize = 22.sp,
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Button(
                                                onClick = {
                                                    currentLanguage = "uz"
                                                    isShowDialog = false
                                                }, colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color.White,
                                                    contentColor = Color.Red
                                                ),
                                                modifier = Modifier.width(120.dp)
                                            ) {
                                                Text(text = "O`zbekcha")
                                            }

                                            Button(
                                                onClick = {
                                                    currentLanguage = "ru"
                                                    isShowDialog = false
                                                }, colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color.White,
                                                    contentColor = Color.Red
                                                ),
                                                modifier = Modifier.width(120.dp)
                                            ) {
                                                Text(text = "Русский")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            var isShowFaq by remember { mutableStateOf(false) }
                            IconButton(onClick = { isShowFaq = true }) {
                                Icon(
                                    imageVector = Icons.Default.DownloadForOffline,
                                    contentDescription = ""
                                )
                                if (isShowFaq) {
                                    Dialog(
                                        onDismissRequest = { isShowAbout = false }
                                    ) {
                                        Surface(
                                            modifier = Modifier
                                                .wrapContentWidth()
                                                .wrapContentHeight(),
                                            shape = MaterialTheme.shapes.large
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .padding(16.dp)
                                                    .verticalScroll(rememberScrollState())
                                                    .weight(weight = 1f, fill = false),
                                                verticalArrangement = Arrangement.Bottom,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Text(
                                                    text = stringResource(id = R.string.faq1),
                                                    fontSize = 22.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.Black,
                                                    textAlign = TextAlign.Center
                                                )
                                                Spacer(modifier = Modifier.height(12.dp))
                                                Text(
                                                    text = stringResource(id = R.string.faq2_0),
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.Black,
                                                    textAlign = TextAlign.Center
                                                )
                                                Text(
                                                    text = stringResource(id = R.string.faq2),
                                                    color = Color.Black,
                                                    fontSize = 16.sp,
                                                    textAlign = TextAlign.Center,
                                                )
                                                Text(
                                                    text = stringResource(id = R.string.faq3_0),
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.Black
                                                )
                                                Text(
                                                    text = stringResource(id = R.string.faq3),
                                                    color = Color.Black,
                                                    fontSize = 16.sp,
                                                    textAlign = TextAlign.Center,
                                                )
                                                Text(
                                                    text = stringResource(id = R.string.faq4_0),
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.Black
                                                )
                                                Text(
                                                    text = stringResource(id = R.string.faq4),
                                                    color = Color.Black,
                                                    fontSize = 16.sp,
                                                    textAlign = TextAlign.Center,
                                                )
                                                Button(
                                                    onClick = { isShowFaq = false },
                                                    colors = ButtonDefaults.buttonColors(
                                                        containerColor = Color("#FD8254".toColorInt()),
                                                    )
                                                ) {
                                                    Text(text = "OK")
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }

                }

                Image(
                    painter = painterResource(id = R.drawable.mainimage),
                    contentDescription = "",
                    modifier = Modifier
                        .height(250.dp)
                        .width(250.dp)
                        .align(Alignment.Center)
                        .padding(top = 12.dp)

                )
            }


        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),

            horizontalAlignment = Alignment.CenterHorizontally,
            //   verticalArrangement = Arrangement.Center
        ) {

            val activity = LocalContext.current as Activity
            var imageUri by remember { mutableStateOf<Uri?>(null) }
            val imageValue = remember { mutableStateOf(R.drawable.baseline_add_a_photo_24) }
            val launcher = rememberLauncherForActivityResult(
                contract =
                ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                imageUri = uri
            }

            Text(
                text = stringResource(id = R.string.main1),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )


            Spacer(modifier = Modifier.height(28.dp))


            imageUri?.let {
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver, it)

                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver, it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }
            }

            Card(
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp),
                border = BorderStroke(12.dp, color = Color("#FFAB8C".toColorInt())),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                colors = CardDefaults.cardColors(containerColor = Color("#FF946D".toColorInt())),
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        launcher.launch("image/*")

                    }) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                            .padding(12.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.baseline_add_a_photo_24),
                        contentDescription = ""
                    )

                    bitmap.value?.let {
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center),
                            contentScale = ContentScale.Crop,
                            bitmap = it.asImageBitmap(),
                            contentDescription = ""
                        )
                    }
                }


            }
            bitmap.value?.let {
                Text(
                    text = "Отправить ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(
                                bounded = false,
                                radius = 10.dp
                            ),
                            onClick = {
                                isLoading = true

                                val filesDir = context.filesDir
                                val file = File(filesDir, "image.png")
                                val inputStream =
                                    activity.contentResolver.openInputStream(imageUri!!)
                                val outputStream = FileOutputStream(file)
                                inputStream!!.copyTo(outputStream)
                                val requestBody = file.asRequestBody("image/".toMediaTypeOrNull())
                                val part = MultipartBody.Part.createFormData(
                                    "cardi_img",
                                    file.name,
                                    requestBody
                                )


                                val myClient = OkHttpClient
                                    .Builder()
                                    .readTimeout(60, TimeUnit.SECONDS)
                                    .connectTimeout(60, TimeUnit.SECONDS)
                                    .build()

                                val retrofit =
                                    Retrofit
                                        .Builder()
                                        .baseUrl("http://195.158.16.43:6788/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .client(myClient)
                                        .build()
                                        .create(KardioApi::class.java)

                                CoroutineScope(Dispatchers.IO).launch {
                                    CoroutineScope(Dispatchers.IO)
                                        .async {
                                            val response = retrofit.uploadImage(part)
                                            copyTextResponse.value =
                                                response.body()?.result.toString()
                                        }
                                        .await()

                                    isLoading = false
                                    txtResponse.value = !txtResponse.value
                                }
                            },


                            ),
                )
            }

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }

            if (txtResponse.value) {
                ShowResultDialog(copyTextResponse.value)
            } else {
                //ShowResultDialog("404 error")
            }

        }
    }
}

