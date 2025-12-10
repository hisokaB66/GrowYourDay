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
    // 현재 progress에 맞는 성장 단계(숫자)를 계산
    val step = getTreeStep(progress)

    // ▼▼▼▼▼ 1. 단계에 맞는 문구를 선택합니다. ▼▼▼▼▼
    val motivationalText = when (step) {
        1 -> "새로운 시작! 오늘도 성장할 준비 완료!"
        2 -> "조금씩 자라고 있어요. 가능성이 가득하네요!"
        3 -> "에너지가 넘치네요! 성장 속도가 놀라워요!"
        4 -> "거의 다 왔어요! 오늘도 멋진 당신 🌟"
        else -> "완벽한 하루! 당신의 노력이 꽃을 피웠어요!"
    }
    // ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲

    // ▼▼▼▼▼ 2. Column을 사용해 이미지와 텍스트를 세로로 배치합니다. ▼▼▼▼▼
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 이미지가 바뀔 때 부드러운 애니메이션 효과를 주는 Crossfade
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
                    .fillMaxWidth(0.5f) // 이미지 크기를 조금 조정
                    .aspectRatio(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 동기 부여 텍스트
        Text(
            text = motivationalText,
            style = MaterialTheme.typography.bodyMedium, // 테마에 맞는 텍스트 스타일
            textAlign = TextAlign.Center
        )
    }
    // ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲
}

/**
 * 완료율(progress)을 기반으로 현재 나무의 성장 단계를 반환하는 함수 (이 부분은 수정 없음)
 */
private fun getTreeStep(progress: Float): Int {
    return when {
        progress == 0f -> 1
        progress > 0f && progress < 0.3f -> 2
        progress >= 0.3f && progress < 0.6f -> 3
        progress >= 0.6f && progress < 1.0f -> 4
        else -> 5
    }
}
