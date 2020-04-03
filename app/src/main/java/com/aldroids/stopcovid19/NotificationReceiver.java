package com.aldroids.stopcovid19;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE_MORE = 109;
    public static final int REQUEST_CODE_HELP = 110;
    public static final String KEY_INTENT_VIEW = "keyintentview";
    public static final String KEY_INTENT_SETTINGS = "keyintentsettings";
    @Override
    public void onReceive(Context context, Intent intent) {


        context.startService(new Intent(context,Foreverservice.class));

       Intent viewIntent = new Intent(context,Notification.class);
        TaskStackBuilder stackBuilder =TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(viewIntent);
        PendingIntent viewPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);


        Intent settingIntent = new Intent(context,Settings.class);
        TaskStackBuilder stackBuilder1 =TaskStackBuilder.create(context);
        stackBuilder1.addNextIntentWithParentStack(settingIntent);
        PendingIntent settingsPendingIntent = stackBuilder1.getPendingIntent(2,PendingIntent.FLAG_UPDATE_CURRENT);


        if (Build.VERSION.SDK_INT< Build.VERSION_CODES.LOLLIPOP) {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, MainActivity.CHANNNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.coronavirus))
                    .setContentTitle("Have you been in contact with someone ?")
                    .setContentText("Remember to wash your hands...")
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.image3)))
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setAutoCancel(true)
                    .setContentIntent(viewPendingIntent)
                    .setDefaults(NotificationCompat.DEFAULT_SOUND)
                    .addAction(android.R.drawable.ic_menu_directions, "View", viewPendingIntent)
                    .addAction(android.R.drawable.ic_menu_compass, "Adjust Setting", settingsPendingIntent);
            //.addAction(action)


            //finally displaying the notification
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(MainActivity.NOTIFICATION_ID, mBuilder.build());
        }else {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, MainActivity.CHANNNEL_ID)
                    .setSmallIcon(R.drawable.ic_stat_name)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.coronavirus))
                    .setColor(context.getResources().getColor(android.R.color.holo_green_dark))
                    .setContentTitle("Have you been in contact with someone ?")
                    .setContentText("Remember to wash your hands...")
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.image3)))
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setAutoCancel(true)
                    .setContentIntent(viewPendingIntent)
                    .setDefaults(NotificationCompat.DEFAULT_SOUND)
                    .addAction(android.R.drawable.ic_menu_directions, "View", viewPendingIntent)
                    .addAction(android.R.drawable.ic_menu_compass, "Adjust Setting", settingsPendingIntent);
            //.addAction(action)


            //finally displaying the notification
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(MainActivity.NOTIFICATION_ID, mBuilder.build());
        }
        //if help button is clicked
       /** if (intent.getIntExtra(MainActivity.KEY_INTENT_HELP, -1) == MainActivity.REQUEST_CODE_HELP) {
            Toast.makeText(context, "You Clicked Help", Toast.LENGTH_LONG).show();
        }

        //if more button is clicked
        if (intent.getIntExtra(MainActivity.KEY_INTENT_MORE, -1) == MainActivity.REQUEST_CODE_MORE) {
            Toast.makeText(context, "You Clicked More", Toast.LENGTH_LONG).show();
        }**/
    }
}
