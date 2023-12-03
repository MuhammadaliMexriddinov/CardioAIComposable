package uz.catsidroid.cardioaicomposable.presentation.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import uz.catsidroid.cardioaicomposable.ui.theme.CardioAIComposableTheme

@Composable
fun ShowResultDialog(text: String) {
    CardioAIComposableTheme {
        Column {
            val openDialog = remember { mutableStateOf(true) }

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Ваш результать ")
                    },
                    text = {
                        Text(text = text)
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(Color("#006262".toColorInt())),
                            onClick = {
                                openDialog.value = false

                            }) {
                            Text("Попробуйте еще раз")
                        }
                    },
                )
            }
        }

    }
}