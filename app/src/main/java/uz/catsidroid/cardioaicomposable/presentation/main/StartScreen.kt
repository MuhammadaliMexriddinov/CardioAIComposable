package uz.catsidroid.cardioaicomposable.presentation.main

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import uz.catsidroid.cardioaicomposable.R
import uz.catsidroid.cardioaicomposable.data.SharedPref
import uz.catsidroid.cardioaicomposable.ui.theme.CardioAIComposableTheme

@Composable
fun StartScreen(navController: NavController) {
    var checkedState = remember { mutableStateOf(false) }



    CardioAIComposableTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.start),
                    contentScale = ContentScale.Crop
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(
                    text = "Cardio AI",
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    textAlign = TextAlign.Center,
                    color = Color("#FF8355".toColorInt()),
                    modifier= Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 100.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                if (checkedState.value){
                    ShowDialog(navController)
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .clickable {
                            checkedState.value = !checkedState.value
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(

                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.White,
                            uncheckedColor = Color.Black,
                            checkmarkColor = Color.Black
                        ),
                        checked = checkedState.value,
                        modifier = Modifier.padding(16.dp),
                        onCheckedChange = { checkedState.value = it },
                    )
                    Text(
                        text = stringResource(id = R.string.start1_1),
                        color = Color.White
                    )
                }

            }
        }
    }

}



@Composable
fun ShowDialog(navController: NavController) {
    CardioAIComposableTheme {
        Column {
            val openDialog = remember { mutableStateOf(true)  }

      if (openDialog.value){
          AlertDialog(
              onDismissRequest = {
                  openDialog.value = false
              },
              title = {
                  Text(text = stringResource(id = R.string.start1_1))
              },
              text = {
                  Text(stringResource(id = R.string.start1_2))
              },
              confirmButton = {
                  Button(
                      colors = ButtonDefaults.buttonColors(Color("#006262".toColorInt())),
                      onClick = {



                          navController.navigate(
                              route = "main_screen",
                              navOptions = NavOptions.Builder()
                                  .setPopUpTo(saveState = false, route = "start_screen", inclusive = true).build()
                          )

                          openDialog.value = false


                      }) {
                      Text(stringResource(id = R.string.start1_3))
                  }
              },
              dismissButton = {
                  Button(
                      colors = ButtonDefaults.buttonColors(Color("#F93A37".toColorInt())),
                      onClick = {
                          openDialog.value = false
                      }) {
                      Text(stringResource(id = R.string.start1_4))
                  }
              }
          )
      }
        }

    }
}