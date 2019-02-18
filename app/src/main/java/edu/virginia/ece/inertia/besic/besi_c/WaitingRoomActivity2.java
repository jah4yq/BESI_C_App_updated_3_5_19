package edu.virginia.ece.inertia.besic.besi_c;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import java.util.Calendar;


public class WaitingRoomActivity2 extends Activity {

    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memento_waiting_room_2);

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);

        Intent EMA_intent = new Intent(this, WaitingRoomActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(this, 20, EMA_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //AlarmManager.cancel(pIntent);

        ((AlarmManager) getSystemService(ALARM_SERVICE)).cancel(pIntent);

    }


    @Override
    protected void onResume() {
        super.onResume();

        ClockfaceActivity.STATE=5;
        ClockfaceActivity.DISABLE=0;
        ClockfaceActivity.CHECKER=0;


/*
        Intent k = new Intent(this, ControlService.class);
        k.putExtra("stop", "" + System.currentTimeMillis());
        startService(k);
*/
    }

    public void snoozeClick(View v) {

        time = System.currentTimeMillis();
        //Toast.makeText(this, "EMA_FLAG = TRUE", Toast.LENGTH_SHORT).show();

        Calendar EMA_cal = Calendar.getInstance();

        //EMA_cal.setTimeInMillis(EMA_time + 1800000L);
        EMA_cal.setTimeInMillis(time + 600000L);

        Intent EMA_intent = new Intent(this, WaitingRoomActivity3.class);

        PendingIntent pIntent = PendingIntent.getActivity(this, 30, EMA_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, EMA_cal.getTimeInMillis(), pIntent);

        startActivity(new Intent(this, ClockfaceActivity.class));

    }

    public void proceed2EMAClick(View v) {
        startActivity(new Intent(this, dEMAActivity.class));

    }

    public void dismissClick(View v) {
        startActivity(new Intent(this, ClockfaceActivity.class));

    }


}