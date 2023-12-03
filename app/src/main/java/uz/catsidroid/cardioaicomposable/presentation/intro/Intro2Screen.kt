package uz.catsidroid.cardioaicomposable.presentation.intro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import uz.catsidroid.cardioaicomposable.R

@Composable
fun Intro2Screen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color("#FEF9F7".toColorInt()))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp),
                border = BorderStroke(12.dp, color = Color("#E9CABE".toColorInt())),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
                //colors = CardDefaults.cardColors(containerColor = Yellow)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.second),
                    contentDescription = ""
                )


            }

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom,
        ) {

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp, vertical = 18.dp),
                //border = BorderStroke(12.dp, color = Color("#E9CABE".toColorInt())),
                colors = CardDefaults.cardColors(containerColor = Color("#FC8154".toColorInt())),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Circle(Color("#F4AC86".toColorInt()))
                        Spacer(modifier = Modifier.width(8.dp))
                        Circle(Color("#F5F1F9".toColorInt()))
                        Spacer(modifier = Modifier.width(8.dp))
                        Circle(Color("#F4AC86".toColorInt()))
                    }
                    Text(
                        text = stringResource(id = R.string.intro2_1),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color("#713A00".toColorInt()),
                        textAlign = TextAlign.Center

                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(id = R.string.intro2_2),
                        fontSize = 16.sp,
                        color = Color("#713A00".toColorInt()),
                        textAlign = TextAlign.Center

                    )
                    Spacer(modifier = Modifier.height(76.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .height(52.dp)
                                .width(128.dp)
                                .background(
                                    color = Color("#713A00".toColorInt()),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .clickable {
                                    navController.navigate(
                                        route = "intro3_screen",
                                        navOptions = NavOptions
                                            .Builder()
                                            .setPopUpTo(
                                                saveState = false,
                                                route = "intro1_screen",
                                                inclusive = true
                                            )
                                            .build()
                                    )
                                }
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = stringResource(id = R.string.next),
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )

                        }
                    }
                }


            }

        }
    }
}


