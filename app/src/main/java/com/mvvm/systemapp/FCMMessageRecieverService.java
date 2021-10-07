package com.mvvm.systemapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.RingtoneManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;


public class FCMMessageRecieverService extends FirebaseMessagingService{

//    This is Notification Class to Recieve Notification From Firebase
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();
        final String CHANNEL_ID = "PUMP MACHINE NOTIFICATIONS";
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "MyNotification",
                NotificationManager.IMPORTANCE_HIGH);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notification = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                //.setSmallIcon(R.drawable.codible_256)
                .setAutoCancel(true);

        notification.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        NotificationManagerCompat.from(this).notify(1, notification.build());
    }

}