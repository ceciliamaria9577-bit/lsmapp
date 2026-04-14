package com.wiwiiwiii.lsmapp.ui.lesson.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.draw.clip
import com.wiwiiwiii.lsmapp.data.model.LessonStep
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.ui.text.font.FontWeight

@Composable
fun BuildWordKeyboard(
    step: LessonStep,
    onAnswer: (Boolean) -> Unit
) {
    var answer by remember { mutableStateOf("") }
    var answered by remember { mutableStateOf(false) }

    val correct = step.correct ?: ""
    val letters = remember(step) {
        (step.letters ?: emptyList()).shuffled()
    }

    val context = LocalContext.current
    val imageRes = context.resources.getIdentifier(
        step.media,
        "drawable",
        context.packageName
    )

    Column(modifier = Modifier.fillMaxSize()) {

        //  BLOQUE FIJO (NO SE MUEVE)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {

            Text(step.question ?: "",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(12.dp))

            if (imageRes != 0) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // RESPUESTA FIJA
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

        //  BLOQUE FLEXIBLE (NO EMPUJA ARRIBA)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Top
        ) {

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                letters.forEach { letter ->
                    Button(
                        onClick = {
                            if (!answered) {
                                answer += letter

                                if (answer.length >= correct.length) {
                                    answered = true
                                    onAnswer(answer == correct)
                                }
                            }
                        }
                    ) {
                        Text(letter)
                    }
                }
            }
        }
    }
}