package com.wiwiiwiii.lsmapp.ui.lesson.steps

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.wiwiiwiii.lsmapp.data.model.LessonStep
import com.wiwiiwiii.lsmapp.ui.library.alphabet.getAlphabetItem
import androidx.compose.runtime.*

@Composable
fun LearnStep(step: LessonStep) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            step.title ?: "",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        val context = LocalContext.current

        Box(
            modifier = Modifier
                .size(300.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
        ) {

            if (!step.video.isNullOrEmpty()) {

                val exoPlayer = remember {
                    androidx.media3.exoplayer.ExoPlayer.Builder(context).build().apply {
                        setMediaItem(
                            androidx.media3.common.MediaItem.fromUri(step.video)
                        )
                        prepare()
                        playWhenReady = true
                    }
                }

                var isFinished by remember { mutableStateOf(false) }
                var showSettings by remember { mutableStateOf(false) }
                var isReady by remember { mutableStateOf(false) }

                // Listener + liberar memoria
                DisposableEffect(exoPlayer) {
                    val listener = object : androidx.media3.common.Player.Listener {
                        override fun onPlaybackStateChanged(state: Int) {
                            if (state == androidx.media3.common.Player.STATE_READY) {
                                isReady = true
                            }
                            if (state == androidx.media3.common.Player.STATE_ENDED) {
                                isFinished = true
                            }
                        }
                    }

                    exoPlayer.addListener(listener)

                    onDispose {
                        exoPlayer.removeListener(listener)
                        exoPlayer.release()
                    }
                }

                Box(modifier = Modifier.fillMaxSize()) {

                    // VIDEO SIEMPRE PRESENTE (evita pantalla negra)
                    AndroidView(
                        factory = {
                            androidx.media3.ui.PlayerView(it).apply {
                                player = exoPlayer
                                useController = false
                                setBackgroundColor(android.graphics.Color.TRANSPARENT)
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )

                    // THUMBNAIL ENCIMA (mientras carga)
                    if (!isReady && !step.thumbnail.isNullOrEmpty()) {
                        coil.compose.AsyncImage(
                            model = step.thumbnail,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    // BOTÓN REPLAY
                    if (isFinished && isReady) {
                        IconButton(
                            onClick = {
                                exoPlayer.seekTo(0)
                                exoPlayer.play()
                                isFinished = false
                            },
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Repetir",
                                modifier = Modifier.size(64.dp)
                            )
                        }
                    }

                    // CONFIGURACIÓN
                    if (isReady) {
                        IconButton(
                            onClick = { showSettings = !showSettings },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Configuración"
                            )
                        }
                    }

                    // MENÚ VELOCIDAD
                    if (showSettings && isReady) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(8.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(8.dp)
                        ) {
                            Column {
                                listOf(0.5f, 1f, 1.5f, 2f).forEach { speed ->
                                    Text(
                                        text = "${speed}x",
                                        modifier = Modifier
                                            .padding(6.dp)
                                            .clickable {
                                                exoPlayer.setPlaybackSpeed(speed)
                                                showSettings = false
                                            }
                                    )
                                }
                            }
                        }
                    }
                }

            } else {
                //  IMAGEN LOCAL (fallback)
                val imageName = step.image ?: ""
                val resId = context.resources.getIdentifier(
                    imageName,
                    "drawable",
                    context.packageName
                )

                if (resId != 0) {
                    Image(
                        painterResource(resId),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        Spacer(Modifier.height(26.dp))

        //  DESCRIPCIÓN
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        ) {
            val alphabetItem = step.letters
                ?.firstOrNull()
                ?.let { letter -> getAlphabetItem(letter) }

            Text(
                text = alphabetItem?.description ?: "Descripción no disponible",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

    Log.d("LearnStep", "Letters: ${step.letters}")
}