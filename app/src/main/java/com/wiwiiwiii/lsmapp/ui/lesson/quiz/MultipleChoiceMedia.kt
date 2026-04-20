package com.wiwiiwiii.lsmapp.ui.lesson.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.wiwiiwiii.lsmapp.data.model.LessonStep

@Composable
fun MultipleChoiceMedia(
    step: LessonStep,
    onAnswer: (Boolean) -> Unit
) {
    var answered by remember { mutableStateOf(false) }

    val options = remember(step.options) {
        (step.options ?: emptyList()).shuffled()
    }

    val context = LocalContext.current

    Column {

        Text(step.question ?: "",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(options) { option ->

                val imageRes = context.resources.getIdentifier(
                    option.media,
                    "drawable",
                    context.packageName
                )

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            if (!answered) {
                                answered = true
                                onAnswer(option.text == step.correct)
                            }
                        }
                ) {

                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = option.text,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                }
            }
        }
    }
}