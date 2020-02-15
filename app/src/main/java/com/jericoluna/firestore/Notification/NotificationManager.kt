package com.jericoluna.firestore.Notification


import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.jericoluna.firestore.MainActivity
import com.jericoluna.firestore.R
import java.io.InputStream
import java.net.URL

class NotificationManager : FirebaseMessagingService(){

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(NotificationManager::class.java.simpleName,
            remoteMessage.data.toString())

        val style = NotificationCompat.BigPictureStyle()
        style.setBigContentTitle(remoteMessage.data["text"])
        style.setSummaryText(remoteMessage.data["text"])


        val imageNotification = BitmapFactory.decodeStream(
            URL("https://www.gameartguppy.com/wp-content/uploads/2019/04/mascot_android-jetpack-plans.png")
                .getContent() as InputStream
        )

        style.bigPicture(imageNotification)
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("Body",remoteMessage.data["text"])

        val pendingIntent=PendingIntent.getActivity(this,201,intent,PendingIntent.FLAG_UPDATE_CURRENT)


        val notificationBuilder = NotificationCompat.Builder(this,"FireStore")
            .setSmallIcon(R.drawable.ic_cher)
            .setContentTitle(remoteMessage.data["title"])
            .setContentText(remoteMessage.data["smalltext"])
            .setStyle(style)
            //.setStyle(NotificationCompat.BigTextStyle().bigText(remoteMessage.data["text"]))
            .setDefaults(Notification.DEFAULT_VIBRATE)
            .setDefaults(Notification.DEFAULT_LIGHTS)
            .addAction(R.mipmap.ic_launcher,"VER",pendingIntent)



        val notification = NotificationManagerCompat.from(this)
        notification.notify(System.currentTimeMillis().toInt(),notificationBuilder.build())


        //para notificaciones que tengan que actualizar tareas en segundo plano
        val intentMessage=Intent("NOTIFICATION_ACTION")
        intentMessage.putExtra("text",remoteMessage.data["text"])
        LocalBroadcastManager.getInstance(this).sendBroadcast(intentMessage)

    }




}

