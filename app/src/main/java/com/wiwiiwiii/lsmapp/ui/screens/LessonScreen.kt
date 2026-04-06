package com.wiwiiwiii.lsmapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.data.model.Lesson
import com.wiwiiwiii.lsmapp.data.model.LessonStep
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import com.wiwiiwiii.lsmapp.ui.viewmodel.ProgressViewModel
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.wiwiiwiii.lsmapp.ui.theme.ButtonGray
import com.wiwiiwiii.lsmapp.ui.theme.DarkGray
import com.wiwiiwiii.lsmapp.ui.theme.White


@Composable
fun LessonScreen(
    lesson: Lesson,
    navController: NavController,
    progressViewModel: ProgressViewModel
) {
    var stepsList by remember { mutableStateOf(lesson.steps.toMutableList()) }
    var currentStep by remember { mutableStateOf(0) }
    val step = stepsList.getOrNull(currentStep) ?: return
    val nextStep = stepsList.getOrNull(currentStep + 1)
    var showFeedback by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    var wrongSteps by remember { mutableStateOf(listOf<LessonStep>()) }
    var correctAnswers by remember { mutableStateOf(0) }
    var totalQuestions by remember { mutableStateOf(0) }
    var isReviewMode by remember { mutableStateOf(false) }

    var unlockedLevel by remember { mutableStateOf(1) }
    var progressMap by remember {
        mutableStateOf(mutableMapOf<Int, Int>())
    }

    var startTime by remember { mutableStateOf<Long?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {

        println("WRONG STEPS: ${wrongSteps.size}")

        LaunchedEffect(step) {
            if (
                (step.type == "LEARN" || step.type == "QUIZ" || step.type == "MATCH")
                && startTime == null
            ) {
                startTime = System.currentTimeMillis()
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {

            //  TOP BAR
            TopBar(
                progress = (currentStep + 1) / stepsList.size.toFloat(),
                onBack = { navController.popBackStack() }
            )

            //  CONTENIDO
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {

                when (step.type) {

                    "INTRO" -> IntroStep(step)

                    "LEARN" -> LearnStep(step)

                    "QUIZ" -> key(step) {
                        QuizStep(step) { correct ->

                            if (!isReviewMode) {
                                totalQuestions++
                            }

                            if (correct) {
                                if (!isReviewMode) {
                                    correctAnswers++
                                }
                            } else {
                                if (!isReviewMode) {
                                    wrongSteps = wrongSteps + step
                                }
                            }

                            isCorrect = correct
                            showFeedback = true
                        }
                    }

                    "MATCH" -> key(step, isReviewMode) {
                        MatchStep(step) { correct ->

                            if (!isReviewMode) {
                                totalQuestions++
                            }

                            if (correct) {
                                if (!isReviewMode) {
                                    correctAnswers++
                                }
                            } else {
                                if (!isReviewMode) {
                                    wrongSteps = wrongSteps + step
                                }
                            }

                            isCorrect = correct
                            showFeedback = true
                        }
                    }

                    "RESULT" -> ResultStep(
                        correctAnswers = correctAnswers,
                        totalQuestions = totalQuestions,
                        startTime = startTime ?: System.currentTimeMillis(),                        onFinish = { navController.popBackStack()
                        },
                        onLessonCompleted = {
                            progressViewModel.completeLesson(lesson.id)
                        },
                        onAddPoints = { points ->
                            progressViewModel.addPoints(points)
                        }
                    )
                }
            }

            //  BOTÓN CONTINUAR
            if (step.type == "INTRO" || step.type == "LEARN") {
                ContinueButton {
                    currentStep++
                }
            }
        }

        //  FEEDBACK (abajo)
        if (showFeedback) {
            FeedbackBox(
                isCorrect = isCorrect,
                onContinue = {

                    showFeedback = false

                    val isAboutToFinish = nextStep?.type == "RESULT"

                    if (isAboutToFinish && wrongSteps.isNotEmpty() && !isReviewMode) {

                        isReviewMode = true

                        val reviewSteps = wrongSteps.toMutableList()

                        reviewSteps.add(LessonStep(type = "RESULT"))

                        stepsList = reviewSteps

                        currentStep = 0
                        wrongSteps = emptyList()

                    } else {

                        currentStep++
                    }
                }
            )
        }
    }
}

@Composable
fun TopBar(progress: Float, onBack: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = onBack) {
            Icon(Icons.Default.Close, contentDescription = null)
        }

        LinearProgressIndicator(
            progress = progress,
            color = Color(0xFF616161),
            trackColor = Color(0xFFBDBDBD),
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
        )
    }
}

@Composable
fun IntroStep(step: LessonStep) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = step.text ?: "",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 36.sp,
            color = DarkGray,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LearnStep(step: LessonStep) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(step.title ?: "", style = MaterialTheme.typography.titleLarge,
            fontSize = 24.sp,
            color = DarkGray,
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
                .background(Color.LightGray, RoundedCornerShape(16.dp))
        ) {
            Image(
                painterResource(resId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text("Descripción de la seña.",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = White
            )
        }
    }
}

@Composable
fun QuizStep(
    step: LessonStep,
    onAnswer: (Boolean) -> Unit
) {

    Column {

        Text(step.question ?: "",
            fontSize = 24.sp,
            color = DarkGray,
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
                        .background(Color(0xFFEFEFEF), RoundedCornerShape(16.dp))
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

        Spacer(Modifier.height(16.dp))

        step.options?.forEach { option ->

            Button(
                onClick = {
                    if (!answered) {
                        answered = true
                        onAnswer(option == step.correct)
                    }
                },
                enabled = !answered, // ← esto bloquea visualmente también
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonGray)
            ) {
                Text(option, fontSize = 24.sp)
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun FeedbackBox(
    isCorrect: Boolean,
    onContinue: () -> Unit
)
{
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (isCorrect) Color(0xFFD4EDDA) else Color(0xFFF8D7DA)
                )
                .padding(16.dp)
        ) {

            Text(
                if (isCorrect) "¡Correcto!" else "Incorrecto",
                fontSize = 24.sp,
                style = MaterialTheme.typography.titleMedium,
                color = DarkGray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            Button(
                onClick = onContinue,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonGray),
                ) {
                Text("Continuar",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun MatchStep(
    step: LessonStep,
    onResult: (Boolean) -> Unit
) {

    var selectedWord by remember { mutableStateOf<String?>(null) }
    var selectedImage by remember { mutableStateOf<String?>(null) }

    var matchedPairs by remember { mutableStateOf(listOf<Pair<String, String>>()) }
    var isLocked by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Une los pares",
            color = DarkGray,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(16.dp))

        //  IMÁGENES ARRIBA (UNA SOLA FILA)
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            step.pairs?.forEach { pair ->

                val context = LocalContext.current
                val resId = context.resources.getIdentifier(
                    pair.image,
                    "drawable",
                    context.packageName
                )

                val isSelected = selectedImage == pair.image
                val isMatched = matchedPairs.any { it.second == pair.image }

                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .background(
                            when {
                                isMatched -> Color(0xFFBDBDBD) // gris oscuro (correcto)
                                isSelected -> Color(0xFFE0E0E0) // gris medio (selección)
                                else -> Color(0xFFF5F5F5) // gris claro
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
            }
        }

        Spacer(Modifier.height(24.dp))

        //  PALABRAS ABAJO
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            step.pairs?.forEach { pair ->

                val isSelected = selectedWord == pair.word
                val isMatched = matchedPairs.any { it.first == pair.word }

                Box(
                    modifier = Modifier
                        .background(
                            when {
                                isMatched -> Color(0xFFBDBDBD) // gris oscuro
                                isSelected -> Color(0xFFE0E0E0) // gris medio
                                else -> Color(0xFFF5F5F5) // gris claro
                            },
                            RoundedCornerShape(12.dp)
                        )
                        .clickable(enabled = !isLocked) {
                            selectedWord =
                                if (selectedWord == pair.word) null else pair.word
                        }
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(pair.word)
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

                //  IMPORTANTE: desbloquear para seguir jugando
                isLocked = false

                //  validar con la lista NUEVA
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

@Composable
fun ContinueButton(onClick: () -> Unit) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = ButtonGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Continuar",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold)
    }
}

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
            color = DarkGray,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(24.dp))

        //  CÍRCULO
        Box(contentAlignment = Alignment.Center) {

            CircularProgressIndicator(
                progress = progress,
                color = Color(0xFF616161),
                trackColor = Color(0xFFBDBDBD),
                strokeWidth = 10.dp,
                modifier = Modifier.size(200.dp)
            )

            Text("$percentage%",
                fontSize = 26.sp,
                color = DarkGray)
        }

        Spacer(Modifier.height(24.dp))

        //  TARJETA
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Puntos",
                    fontSize = 22.sp,
                    color = DarkGray)
                Text("$points",
                    fontSize = 20.sp,
                    color = DarkGray)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Tiempo",
                    fontSize = 22.sp,
                    color = DarkGray)
                Text(timeText,
                    fontSize = 20.sp,
                    color = DarkGray)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Aciertos",
                    fontSize = 22.sp,
                    color = DarkGray)
                Text("$correctAnswers/$totalQuestions",
                    fontSize = 20.sp,
                    color = DarkGray)
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
            colors = ButtonDefaults.buttonColors(containerColor = ButtonGray)
            ) {
            Text("Continuar",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold)
        }
    }
}