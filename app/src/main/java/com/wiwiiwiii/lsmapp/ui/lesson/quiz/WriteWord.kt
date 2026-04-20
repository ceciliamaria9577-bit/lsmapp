package com.wiwiiwiii.lsmapp.ui.lesson.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiwiiwiii.lsmapp.data.model.LessonStep
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight

@Composable
fun WriteWord(
    step: LessonStep,
    onAnswer: (Boolean) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var answered by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column {

        Text(step.question ?: "",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current

        val imageRes = context.resources.getIdentifier(
            step.media,
            "drawable",
            context.packageName
        )

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (!answered) {
                    answered = true

                    keyboardController?.hide()

                    onAnswer(text.uppercase() == step.correct)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Verificar",
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}