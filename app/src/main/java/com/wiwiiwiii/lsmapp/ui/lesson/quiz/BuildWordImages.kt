package com.wiwiiwiii.lsmapp.ui.lesson.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import com.wiwiiwiii.lsmapp.data.model.LessonStep
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.ui.text.font.FontWeight

@Composable
fun BuildWordImages(
    step: LessonStep,
    onAnswer: (Boolean) -> Unit
) {
    var answer by remember { mutableStateOf("") }
    var answered by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val correct = step.correct ?: ""

    val options = remember(step) {
        (step.options ?: emptyList()).shuffled()
    }
    Column(modifier = Modifier.fillMaxSize()) {

        //  BLOQUE FIJO
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp) // menos porque no hay imagen grande arriba
        ) {

            Text(step.question ?: "",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(correct.length) { index ->
                        val char = answer.getOrNull(index)?.toString() ?: ""

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                    RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(char)
                        }
                    }
                }

                IconButton(
                    onClick = {
                        if (!answered && answer.isNotEmpty()) {
                            answer = answer.dropLast(1)
                        }
                    }
                ) {
                    Icon(Icons.Default.Backspace, contentDescription = "Borrar")
                }
            }
        }

        //  GRID FIJO ABAJO
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(options) { option ->

                val imageRes = context.resources.getIdentifier(
                    option.media,
                    "drawable",
                    context.packageName
                )

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = MaterialTheme.colorScheme.surface,)
                        .clickable {
                            if (!answered) {
                                answer += option.letter ?: ""

                                if (answer.length >= correct.length) {
                                    answered = true
                                    onAnswer(answer == correct)
                                }
                            }
                        }
                ) {
                    if (imageRes != 0) {
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}