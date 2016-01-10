package com.slavafleer.musicalarm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Slava on 09/01/2016.
 */
public class AlarmService extends IntentService {

    public AlarmService() {
        super("AlarmService");
    }

    // When service triggered, open PlayerActivity that plays the music.
    @Override
    protected void onHandleIntent(Intent intent) {

        // Remove notification.
        NotificationManager notificationManager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(0);

        Intent playerIntent = new Intent(this, PlayerActivity.class);
        playerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Need for opening activity.
        startActivity(playerIntent);
    }
}
