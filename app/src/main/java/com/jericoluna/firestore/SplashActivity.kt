package com.jericoluna.firestore

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationManagerCompat

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val androidChannel= NotificationChannel("FireStore",
            "FireStore",NotificationManagerCompat.IMPORTANCE_HIGH);
        androidChannel.enableLights(true)
        androidChannel.enableVibration(true)
        androidChannel.setLightColor(Color.GREEN)
        (getSystemService(Context.NOTIFICATION_SERVICE) as? android.app.NotificationManager)
            ?.createNotificationChannel(androidChannel)




       val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
