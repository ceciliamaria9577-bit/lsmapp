package com.wiwiiwiii.lsmapp.ui.lesson.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiwiiwiii.lsmapp.data.model.LessonStep

@Composable
fun QuizStep(
    step: LessonStep,
    onAnswer: (Boolean) -> Unit
) {

    val shuffledOptions = remember(step) {
        step.options?.shuffled()
    }

    Column {

        Text(step.question ?: "",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(16.dp))

        val context = LocalContext.current

        val imageName = step.image ?: ""

        var answered by remember { mutableStateOf(false) }

        val resId = context.resources.getIdentifier(
            imageName,
            "drawable",
            context.packageName

        )

        if (resId != 0) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(300.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(resId),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        Spacer(Modifier.height(36.dp))

        shuffledOptions?.forEach { option ->

            Button(
                onClick = {
                    if (!answered) {
                        answered = true
                        onAnswer(option == step.correct)
                    }
                },
                enabled = !answered,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSurface)
            ) {
                Text(option, fontSize = 24.sp)
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}