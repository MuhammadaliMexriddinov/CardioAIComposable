package uz.catsidroid.cardioaicomposable.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import uz.catsidroid.cardioaicomposable.R


@Composable
fun LocationItem(fullName: String, orientation: String, phoneNumber: String, distance: String) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,

            ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color("#ECE1CF".toColorInt()), contentColor = Color.White
        )
    ) {
        Row {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(4f)
            ) {
                Text(
                    text = fullName,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(12.dp),
                )
                Text(
                    text = orientation,
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Button(
                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                        Color("#F07427".toColorInt())
                    ), modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                ) {
                    Text(text = phoneNumber, color = Color.White)

                }

                Text(
                    text = distance,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(12.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
                    .weight(1.5f),
               horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .width(86.dp)
                        .height(86.dp)
                        .clip(shape = RoundedCornerShape(150.dp))
                        .align(Alignment.CenterHorizontally)
                        .background(
                            Color("#F07427".toColorInt()), shape = RoundedCornerShape(150.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        modifier = Modifier.size(42.dp),
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(id = R.string.map1),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }

    }
}