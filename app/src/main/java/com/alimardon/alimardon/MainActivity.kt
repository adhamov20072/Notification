package com.alimardon.alimardon

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.alimardon.alimardon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()
        binding.button.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                comingNotification()
            }
        }
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                R.string.Channel_id.toString(),
                R.string.Channel_name.toString(),
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.setShowBadge(true)
            val NotificationManager=getSystemService(NotificationManager::class.java)
            NotificationManager.createNotificationChannel(channel)
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun comingNotification(){
        val intent=Intent(this,MainActivity2::class.java)
        val pintent=PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_IMMUTABLE)
        val Notification=NotificationCompat.Builder(this,getString(R.string.Channel_id))
            .setContentTitle("Nima gaaaaaap")
            .setContentText("Qale ishla")
            .setChannelId(R.string.Channel_id.toString())
            .setContentIntent(pintent)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()
        val NotificationManagerCompat=NotificationManagerCompat.from(this)
        NotificationManagerCompat.notify(3,Notification)
    }
}