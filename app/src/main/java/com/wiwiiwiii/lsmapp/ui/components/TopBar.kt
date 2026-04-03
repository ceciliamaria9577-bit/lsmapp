package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.wiwiiwiii.lsmapp.R


@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Row {
            Image(
                painter = painterResource(id = R.drawable.fish),
                contentDescription = "Pez",
                modifier = Modifier.size(34.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("666")
        }

        Row {
            Image(
                painter = painterResource(id = R.drawable.fire),
                contentDescription = "Racha",
                modifier = Modifier.size(28.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("777")
        }
    }
}