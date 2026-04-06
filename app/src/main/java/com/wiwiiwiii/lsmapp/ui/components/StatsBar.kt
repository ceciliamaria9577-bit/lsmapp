package com.wiwiiwiii.lsmapp.ui.components
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wiwiiwiii.lsmapp.R
import com.wiwiiwiii.lsmapp.ui.viewmodel.ProgressViewModel

@Composable
fun StatsBar(progressViewModel: ProgressViewModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp) // más margen lateral
            .background(Color(0xFFD9D9D9), RoundedCornerShape(25.dp))
            .padding(vertical = 16.dp), // más alto
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        StatItem(icon = R.drawable.fire, value = "666")
        DividerVertical()
        StatItem(icon = R.drawable.fish, value = "${progressViewModel.points}")
        DividerVertical()
        StatItem(icon = R.drawable.statistics, value = "666")
    }
}

@Composable
fun StatItem(icon: Int, value: String) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(value, fontSize = 18.sp)
    }
}

@Composable
fun DividerVertical() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(20.dp)
            .background(Color.Gray)
    )
}