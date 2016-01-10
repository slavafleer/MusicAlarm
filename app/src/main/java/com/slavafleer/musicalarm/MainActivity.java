package com.slavafleer.musicalarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.slavafleer.musicalarm.adapter.ToneAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private RecyclerView mRecyclerViewTones;
    private ArrayList<Tone> mTones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerViewTones = (RecyclerView) findViewById(R.id.recyclerViewTones);

        // Tones Initialisation.
        String toneNames[] = getResources().getStringArray(R.array.tones);
        for(int i = 1; i <= toneNames.length; i++) {
            mTones.add(new Tone(i, toneNames[i - 1]));
        }

        mRecyclerViewTones.setLayoutManager(new LinearLayoutManager(this));

        ToneAdapter toneAdapter = new ToneAdapter(this, mTones);
        mRecyclerViewTones.setAdapter(toneAdapter);
    }

    // Start Service for playing selected music on alarm time.
    public void buttonSetTAlarmTime_onClick(View view) {

        // Open TimePickerDialog.
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, hour, minute, true);
        timePickerDialog.show();
    }

    // Runs when TimePickerDialog closed.
    // Start the service with timer.
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Intent serviceIntent = new Intent(this, AlarmService.class);

        PendingIntent pendingIntent = PendingIntent.getService(
                this, 0, serviceIntent, PendingIntent.FLAG_ONE_SHOT);

        // Chosen time.
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        // TODO: for debugging
        calendar.add(Calendar.SECOND, 5);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        Toast.makeText(this, "The alarm was enabled.", Toast.LENGTH_LONG).show();

        // Show Action Bar Notification (While service is On)
        // The notification would show alarm time, would not be removable
        // and click on it would get MainActivity.
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String formatedTime = dateFormat.format(calendar.getTime());

        // TODO: right now when app not closed but notification was clicked
        // TODO: another MainActivity opens. need to fix it.
        Intent mainIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingMainIntent = PendingIntent.getActivity(
                this, 0, mainIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.alarm_clock_white)
                .setContentTitle("Set On " + formatedTime)
                .setContentIntent(pendingMainIntent)
                .setOngoing(true); // not removable notification
        Notification notification = builder.build();
        NotificationManagerCompat.from(this).notify(0, notification);
    }

    // Stop Service.
    public void buttonCancelAlarm_onClick(View view) {

        Intent serviceIntent = new Intent(this, AlarmService.class);

        PendingIntent pendingIntent = PendingIntent.getService(
                this, 0, serviceIntent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
    }


}
