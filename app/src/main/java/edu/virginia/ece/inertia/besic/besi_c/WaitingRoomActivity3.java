package edu.virginia.ece.inertia.besic.besi_c;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

import static edu.virginia.ece.inertia.besic.besi_c.utils.FileUtil.saveStringToFile;


public class WaitingRoomActivity3 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memento_waiting_room_3);

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(2000);

        Intent EMA_intent = new Intent(this, WaitingRoomActivity2.class);

        PendingIntent pIntent = PendingIntent.getActivity(this, 30, EMA_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //AlarmManager.cancel(pIntent);

        ((AlarmManager) getSystemService(ALARM_SERVICE)).cancel(pIntent);





    }

    @Override
    protected void onResume() {
        super.onResume();

        long yourmillis = System.currentTimeMillis();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date resultdate2 = new Date(yourmillis);

        String pagesMetricText = "WaitingRoomActivity3" + sdf2.format(resultdate2) + "\n";
        String fileName2 = "pages";
        //FileOutputStream fos = null;

        saveStringToFile(fileName2, pagesMetricText);

        ClockfaceActivity.STATE=6;
        ClockfaceActivity.DISABLE=0;
        ClockfaceActivity.CHECKER=0;


/*
        Intent k = new Intent(this, ControlService.class);
        k.putExtra("stop", "" + System.currentTimeMillis());
        startService(k);
*/
    }


    public void dismissClick(View v) {

        long yourms = System.currentTimeMillis();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date resultdate1 = new Date(yourms);

        String buttonMetricText = "WaitingRoomAcivity3, CANCEL, " + sdf1.format(resultdate1) + "\n";
        String fileName1 = "buttons";
        //FileOutputStream fos = null;

        saveStringToFile(fileName1, buttonMetricText);

        startActivity(new Intent(this, ClockfaceActivity.class));

    }

    public void proceed2EMAClick(View v) {

        long yourms = System.currentTimeMillis();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date resultdate1 = new Date(yourms);

        String buttonMetricText = "WaitingRoomAcivity3, PROCEED, " + sdf1.format(resultdate1) + "\n";
        String fileName1 = "buttons";
        //FileOutputStream fos = null;

        saveStringToFile(fileName1, buttonMetricText);

        startActivity(new Intent(this, dEMAActivity.class));

    }



}