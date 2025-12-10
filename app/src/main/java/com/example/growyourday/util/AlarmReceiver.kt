package com.example.growyourday.util

import android.content.BroadcastReceiver // <-- 이 import 문 한 줄이 모든 것을 해결합니다!
import android.content.Context
import android.content.Intent

// BroadcastReceiver 클래스를 상속받아 AlarmReceiver를 정의합니다.
class AlarmReceiver : BroadcastReceiver() {

    // 브로드캐스트 메시지(예: 알람 시간 도달)를 수신하면 이 onReceive 함수가 자동으로 호출됩니다.
    override fun onReceive(context: Context, intent: Intent) {
        // 이 안에서 알림(Notification)을 띄우거나 다른 작업을 수행할 수 있습니다.
        // 예를 들어, "물 줄 시간입니다!" 같은 알림을 생성하는 코드가 여기에 들어갑니다.

        // "TODO: 알림 생성 로직 구현"
        println("알람이 울렸습니다! onReceive가 호출되었습니다.")
    }
}
