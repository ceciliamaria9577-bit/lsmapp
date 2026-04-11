package com.wiwiiwiii.lsmapp.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.wiwiiwiii.lsmapp.R
import com.wiwiiwiii.lsmapp.ui.viewmodel.ProgressViewModel
import androidx.compose.ui.graphics.ColorFilter


@Composable
fun TopBar(progressViewModel: ProgressViewModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Row {
            Image(
                painter = painterResource(id = R.drawable.fish),
                contentDescription = "Pez",
                modifier = Modifier.size(34.dp),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)                )

            Spacer(modifier = Modifier.width(4.dp))

            Text("${progressViewModel.points}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

        Row {
            Image(
                painter = painterResource(id = R.drawable.fire),
                contentDescription = "Racha",
                modifier = Modifier.size(28.dp),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text("777",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}