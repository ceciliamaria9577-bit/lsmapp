package com.wiwiiwiii.lsmapp.ui.lesson.steps

import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiwiiwiii.lsmapp.R

@Composable
fun ResultStep(
    correctAnswers: Int,
    totalQuestions: Int,
    startTime: Long,
    onFinish: () -> Unit,
    onLessonCompleted: () -> Unit,
    onAddPoints: (Int) -> Unit
) {

    val elapsed = (System.currentTimeMillis() - startTime) / 1000
    val minutes = elapsed / 60
    val seconds = elapsed % 60

    val timeText = "$minutes:${seconds.toString().padStart(2, '0')}"

    val percentage = if (totalQuestions > 0)
        (correctAnswers * 100) / totalQuestions
    else 0

    val progress = percentage / 100f

    val points = correctAnswers * 15

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "¡Lección Completada!",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(24.dp))

        //  CÍRCULO
        Box(contentAlignment = Alignment.Center) {

            CircularProgressIndicator(
                progress = progress,
                color = MaterialTheme.colorScheme.onBackground,
                trackColor = MaterialTheme.colorScheme.surface,
                strokeWidth = 10.dp,
                modifier = Modifier.size(200.dp)
            )

            Text("$percentage%",
                fontSize = 36.sp,
                color = MaterialTheme.colorScheme.primary)        }

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.fish),
                    contentDescription = "Puntos",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
                Text("$points",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary)
            }

            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(60.dp)
                    .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f))
            )

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = "Tiempo",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
                Text(timeText,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary)
            }

            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(60.dp)
                    .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f))
            )

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "Correctos",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
                Text("$correctAnswers/$totalQuestions",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary)
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                onAddPoints(points)
                onLessonCompleted()
                onFinish()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSurface)
        ) {
            Text("Continuar",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}