package uz.catsidroid.cardioaicomposable.presentation.main

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DownloadForOffline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import uz.catsidroid.cardioaicomposable.ui.theme.CardioAIComposableTheme

@SuppressLint("RememberReturnType")
@Composable
fun MenuItems() {
   CardioAIComposableTheme {

       var expanded by remember { mutableStateOf(false) }
       Box(
           modifier = Modifier
               .background(Color.Blue)
               .animateContentSize()
               .height(if (expanded) 400.dp else 200.dp)
               .fillMaxWidth()
               .clickable(
                   interactionSource = remember { MutableInteractionSource() },
                   indication = null
               ) {
                   expanded = !expanded
               }

       ) {

       }

   }
}