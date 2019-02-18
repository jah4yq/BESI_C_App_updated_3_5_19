package edu.virginia.ece.inertia.besic.besi_c;/*
package edu.virginia.cs.mooncake.wada;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.Notification;
//import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Handler;
import android.content.Context;

import static android.support.v4.app.ActivityCompat.startActivity;




public class Notification_receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent myIntent = new Intent(Notification_receiver.this,WaitingRoomActivity.class);

        startActivity(myIntent);

        //Intent myIntent = new Intent(ClockfaceActivity.this,)

        //Toast.makeText(Notification_receiver.this,"Test1",Toast.LENGTH_SHORT).show();


    }

















    /*
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(this,"Test1",Toast.LENGTH_SHORT).show();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context, WaitingRoomActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setAutoCancel(true);

        notificationManager.notify(100, builder.build());

        //startActivity(new Intent(this, WaitingRoomActivity.class));

    }
    */
//}

