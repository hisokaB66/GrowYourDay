package com.example.growyourday

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.example.growyourday.ui.screens.HomeScreen
import com.example.growyourday.ui.theme.GrowYourDayTheme
import com.example.growyourday.util.NotificationHelper

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔥 1) 알림 권한 요청 필수 (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                100
            )
        }

        // 🔥 2) 알람 설정 실행
        NotificationHelper(this).scheduleDailyReminder()

        setContent {
            GrowYourDayTheme {
                HomeScreen()
            }
        }
    }
}
