package com.example.h.test2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notify = findViewById(R.id.button);

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

    }

    public void sendNotification() {
        NotificationCompat.Builder mBuilder = new
                NotificationCompat.Builder(MainActivity.this, "new_notify")
                .setContentTitle("Medicine Reminder")
                .setContentText("It's medicine time!!");

        Intent resultIntent = new Intent(MainActivity.this, Main2Activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0,resultIntent, 0);
        mBuilder.setContentIntent(pendingIntent);

        int mNotificationId = 001;
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(mNotificationId, mBuilder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("new_notify",
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0, mBuilder.build());
    }
}
