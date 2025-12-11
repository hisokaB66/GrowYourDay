package com.example.growyourday.ui.components

import androidx.compose.animation.Crossfade

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.growyourday.R

@Composable
fun GrowingTree(
    progress: Float,
    modifier: Modifier = Modifier
) {
    val step = getTreeStep(progress)

    val motivationalText = when (step) {
        1 -> "새로운 시작! 오늘도 성장할 준비 완료!"
        2 -> "조금씩 자라고 있어요. 가능성이 가득하네요!"
        3 -> "에너지가 넘치네요! 성장 속도가 놀라워요!"
        4 -> "거의 다 왔어요! 오늘도 멋진 당신 🌟"
        else -> "완벽한 하루! 당신의 노력이 꽃을 피웠어요!"
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Crossfade(
            targetState = step,
            animationSpec = tween(durationMillis = 500),
            label = "Tree Growth Animation"
        ) { currentStep ->
            val imageRes = when (currentStep) {
                1 -> R.drawable.seed
                2 -> R.drawable.sprout
                3 -> R.drawable.stem
                4 -> R.drawable.leaf
                else -> R.drawable.flower
            }
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "성장하는 나무 (현재 ${currentStep}단계)",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = motivationalText,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }

}

private fun getTreeStep(progress: Float): Int {
    return when {
        progress == 0f -> 1
        progress > 0f && progress < 0.3f -> 2
        progress >= 0.3f && progress < 0.6f -> 3
        progress >= 0.6f && progress < 1.0f -> 4
        else -> 5
    }
}
