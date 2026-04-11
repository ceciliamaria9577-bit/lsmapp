package com.wiwiiwiii.lsmapp.ui.lesson.steps

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun LearnStep(step: LessonStep) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(step.title ?: "", style = MaterialTheme.typography.titleLarge,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(16.dp))

        val context = LocalContext.current
        val imageName = step.image ?: ""
        val resId = context.resources.getIdentifier(
            imageName,
            "drawable",
            context.packageName
        )

        Box(
            modifier = Modifier
                .size(300.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Image(
                painterResource(resId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(Modifier.height(26.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text("Descripción de la seña.",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary            )
        }
    }
}