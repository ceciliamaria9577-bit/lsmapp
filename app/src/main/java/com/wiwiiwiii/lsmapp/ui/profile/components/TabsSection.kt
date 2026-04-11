package com.wiwiiwiii.lsmapp.ui.profile.components
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TabsSection(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {

    val tabs = listOf("Logros", "Estadísticas", "Amigos")

    Column {

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            tabs.forEachIndexed { index, title ->

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { onTabSelected(index) }
                ) {

                    Text(
                        text = title,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    if (selectedTab == index) {
                        Box(
                            modifier = Modifier
                                .width(20.dp)
                                .height(3.dp)
                                .background(color = MaterialTheme.colorScheme.onSurface, RoundedCornerShape(10.dp))
                        )
                    }
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp)
            .height(3.dp),
            color = MaterialTheme.colorScheme.onSurface)
    }
}