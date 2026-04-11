package com.wiwiiwiii.lsmapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wiwiiwiii.lsmapp.data.model.Lesson
import com.wiwiiwiii.lsmapp.data.model.LessonStep
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import com.wiwiiwiii.lsmapp.ui.viewmodel.ProgressViewModel
import com.wiwiiwiii.lsmapp.ui.lesson.components.*
import com.wiwiiwiii.lsmapp.ui.lesson.steps.*
import com.wiwiiwiii.lsmapp.ui.lesson.quiz.*
import com.wiwiiwiii.lsmapp.ui.lesson.model.StepType

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

    fun handleAnswer(correct: Boolean, step: LessonStep) {

        if (!isReviewMode) {
            totalQuestions++
        }

        if (correct) {
            if (!isReviewMode) correctAnswers++
        } else {
            if (!isReviewMode) wrongSteps = wrongSteps + step
        }

        isCorrect = correct
        showFeedback = true
    }

    Box(modifier = Modifier.fillMaxSize()) {

        println("WRONG STEPS: ${wrongSteps.size}")

        LaunchedEffect(step) {
            if (
                (step.type == StepType.LEARN ||
                        step.type == StepType.QUIZ ||
                        step.type == StepType.MATCH)
                && startTime == null
            ) {
                startTime = System.currentTimeMillis()
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (isReviewMode) {
                Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.fillMaxWidth()
                ) {
                }
            }
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

                    StepType.INTRO -> IntroStep(step)

                    StepType.LEARN -> LearnStep(step)

                    StepType.QUIZ -> key(step) {
                        QuizStep(step) { correct ->
                            handleAnswer(correct, step)
                        }
                    }

                    StepType.MATCH -> key(step, isReviewMode) {
                        MatchStep(step) { correct ->
                            handleAnswer(correct, step)
                        }
                    }

                    StepType.RESULT -> ResultStep(
                        correctAnswers = correctAnswers,
                        totalQuestions = totalQuestions,
                        startTime = startTime ?: System.currentTimeMillis(),
                        onFinish = { navController.popBackStack() },
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
            if (step.type == StepType.INTRO || step.type == StepType.LEARN) {
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

                    val isAboutToFinish = nextStep?.type == StepType.RESULT

                    if (isAboutToFinish && wrongSteps.isNotEmpty() && !isReviewMode) {

                        isReviewMode = true

                        val reviewSteps = wrongSteps.toMutableList()

                        reviewSteps.add(LessonStep(type = StepType.RESULT))

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
