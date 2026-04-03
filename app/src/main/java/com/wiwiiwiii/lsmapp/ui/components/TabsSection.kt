package com.wiwiiwiii.lsmapp.ui.components
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TabsSection(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {

    val tabs = listOf("Logros", "Estadísticas", "Amigos")

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            tabs.forEachIndexed { index, title ->

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { onTabSelected(index) }
                ) {

                    Text(
                        title,
                        color = if (selectedTab == index) Color.Black else Color.Gray
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    if (selectedTab == index) {
                        Box(
                            modifier = Modifier
                                .width(30.dp)
                                .height(3.dp)
                                .background(Color.Black, RoundedCornerShape(10.dp))
                        )
                    }
                }
            }
        }

        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}