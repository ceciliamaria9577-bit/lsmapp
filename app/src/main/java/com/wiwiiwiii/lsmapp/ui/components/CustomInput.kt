package com.wiwiiwiii.lsmapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
                RoundedCornerShape(50))
            .padding(horizontal = 24.dp, vertical = 10.dp)
    ) {

        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.width(8.dp))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(placeholder, fontSize = 16.sp, color = Color.Gray)
                }
                innerTextField()
            }
        )
    }
}