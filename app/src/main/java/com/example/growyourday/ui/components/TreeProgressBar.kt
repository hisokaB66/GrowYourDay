package com.example.growyourday.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TreeProgressBar(progress: Float) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Box(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth()
                .background(
                    color = Color(0xFFB2DFDB),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = progress)
                    .background(
                        color = Color(0xFF00796B),
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Growth: ${(progress * 100).toInt()}%")
    }
}
