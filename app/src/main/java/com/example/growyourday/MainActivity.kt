package com.example.growyourday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.growyourday.ui.screens.HomeScreen
import com.example.growyourday.ui.theme.GrowYourDayTheme
import com.example.growyourday.util.NotificationHelper
import com.example.growyourday.viewmodel.TodoViewModel


class MainActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 하루 알림 예약 (오후 12시)
        NotificationHelper(this).scheduleDailyReminder()

        setContent {
            GrowYourDayTheme  {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFFF8E7)
                ) {
                    HomeScreen(viewModel = todoViewModel)
                }

            }
        }
    }
}
