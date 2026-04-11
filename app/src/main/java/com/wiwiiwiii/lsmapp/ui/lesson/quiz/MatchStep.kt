package com.wiwiiwiii.lsmapp.ui.lesson.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiwiiwiii.lsmapp.data.model.LessonStep
import com.wiwiiwiii.lsmapp.ui.theme.LocalExtendedColors

@Composable
fun MatchStep(
    step: LessonStep,
    onResult: (Boolean) -> Unit
) {

    var selectedWord by remember { mutableStateOf<String?>(null) }
    var selectedImage by remember { mutableStateOf<String?>(null) }

    var matchedPairs by remember { mutableStateOf(listOf<Pair<String, String>>()) }
    var isLocked by remember { mutableStateOf(false) }

    val shuffledWords = remember(step) {

        val original = step.pairs?.map { it.word } ?: emptyList()

        var shuffled = original.shuffled()

        //  Evitar mismo orden
        if (shuffled == original && shuffled.size > 1) {
            shuffled = original.shuffled()
        }

        shuffled
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            "Une los pares",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        //  LISTA DE FILAS
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            step.pairs?.forEachIndexed { index, pair ->

                val word = shuffledWords?.getOrNull(index) ?: return@forEachIndexed

                val context = LocalContext.current
                val resId = context.resources.getIdentifier(
                    pair.image,
                    "drawable",
                    context.packageName
                )

                val isImageSelected = selectedImage == pair.image
                val isWordSelected = selectedWord == word

                val isImageMatched = matchedPairs.any { it.second == pair.image }
                val isWordMatched = matchedPairs.any { it.first == word }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    //  IMAGEN (izquierda)
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .background(
                                when {
                                    isImageMatched -> MaterialTheme.colorScheme.onSurface
                                    isImageSelected -> MaterialTheme.colorScheme.onBackground
                                    else -> MaterialTheme.colorScheme.surface
                                },
                                RoundedCornerShape(12.dp)
                            )
                            .clickable(enabled = !isLocked) {
                                selectedImage =
                                    if (selectedImage == pair.image) null else pair.image
                            }
                            .padding(8.dp)
                    ) {
                        if (resId != 0) {
                            Image(
                                painterResource(resId),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    //  ESPACIO CENTRAL
                    Spacer(modifier = Modifier.width(40.dp))

                    //  TEXTO (derecha alineado)
                    Box(
                        modifier = Modifier
                            .width(140.dp)
                            .background(
                                when {
                                    isWordMatched -> MaterialTheme.colorScheme.onSurface
                                    isWordSelected -> MaterialTheme.colorScheme.onBackground
                                    else -> MaterialTheme.colorScheme.surface
                                },
                                RoundedCornerShape(12.dp)
                            )
                            .clickable(enabled = !isLocked) {
                                selectedWord =
                                    if (selectedWord == word) null else word
                            }
                            .padding(vertical = 14.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = word,
                            textAlign = TextAlign.Center,
                            color = LocalExtendedColors.current.buttonText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }

    //  VALIDACIÓN AUTOMÁTICA
    LaunchedEffect(selectedWord, selectedImage) {

        if (selectedWord != null && selectedImage != null) {

            isLocked = true

            val correct = step.pairs?.any {
                it.word == selectedWord && it.image == selectedImage
            } == true

            if (correct) {

                val newPairs = matchedPairs + (selectedWord!! to selectedImage!!)

                matchedPairs = newPairs

                selectedWord = null
                selectedImage = null

                isLocked = false

                if (newPairs.size == step.pairs?.size) {
                    isLocked = true
                    onResult(true)
                }

            } else {
                onResult(false)
            }
        }
    }
}