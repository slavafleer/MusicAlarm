package com.slavafleer.musicalarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

// Here an user can stop the music alarm or to snooze it.
public class PlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        SharedPreferences sharedPreferences = getSharedPreferences("MainActivity", MODE_PRIVATE);
        String tone = sharedPreferences.getString("toneId", "0");

        String toneName = "tone_" + tone;
        int toneId = getResources().getIdentifier(toneName, "raw", getPackageName());

        mediaPlayer = MediaPlayer.create(this, toneId);
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        imageButtonStop_onClick(null);

        super.onDestroy();
    }

    // Open new service with 3 min delay.
    public void imageButtonSnooze_onClick(View view) {

        mediaPlayer.stop();

        Intent serviceIntent = new Intent(this, AlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getService(
                this, 0, serviceIntent, PendingIntent.FLAG_ONE_SHOT);

        // Delay.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 3);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.clock_snooze_white)
                .setContentTitle("Alarm was snoozed.")
                .setOngoing(true); // not removable notification
        Notification notification = builder.build();
        NotificationManagerCompat.from(this).notify(0, notification);

        Toast.makeText(this, "The alarm was snoozed.", Toast.LENGTH_LONG).show();
        finish();
    }

    // Stop the music and close PlayerActivity.
    public void imageButtonStop_onClick(View view) {

        mediaPlayer.stop();
        finish();
    }
}
