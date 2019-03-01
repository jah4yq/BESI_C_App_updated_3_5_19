package edu.virginia.ece.inertia.besic.besi_c;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static edu.virginia.ece.inertia.besic.besi_c.utils.FileUtil.saveStringToFile;


public class WaitingRoomActivity extends Activity {

    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memento_waiting_room);

        ClockfaceActivity.dEMAEnable = 1;

        Context context = this;
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        // Are we charging / charged?
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;


        //boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;

        if (isCharging){
            startActivity(new Intent(this, ClockfaceActivity.class));
        }
        else {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(2000);
        }




    }

    @Override
    protected void onResume() {
        super.onResume();

        long yourmillis = System.currentTimeMillis();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date resultdate2 = new Date(yourmillis);

        String pagesMetricText = "WaitingRoomActivity" + sdf2.format(resultdate2) + "\n";
        String fileName2 = "pages";
        //FileOutputStream fos = null;

        saveStringToFile(fileName2, pagesMetricText);

        ClockfaceActivity.STATE=4;
        ClockfaceActivity.DISABLE=0;
        ClockfaceActivity.CHECKER=0;


/*
        Intent k = new Intent(this, ControlService.class);
        k.putExtra("stop", "" + System.currentTimeMillis());
        startService(k);
*/
    }

    public void snoozeClick(View v) {

        long yourms = System.currentTimeMillis();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date resultdate1 = new Date(yourms);

        String buttonMetricText = "WaitingRoomAcivity, SNOOZE, " + sdf1.format(resultdate1) + "\n";
        String fileName1 = "buttons";
        //FileOutputStream fos = null;

        saveStringToFile(fileName1, buttonMetricText);

        time = System.currentTimeMillis();
        //Toast.makeText(this, "EMA_FLAG = TRUE", Toast.LENGTH_SHORT).show();

        Calendar EMA_cal = Calendar.getInstance();

        //EMA_cal.setTimeInMillis(EMA_time + 1800000L);
        EMA_cal.setTimeInMillis(time + 600000L);

        Intent EMA_intent = new Intent(this, WaitingRoomActivity2.class);

        PendingIntent pIntent = PendingIntent.getActivity(this, 20, EMA_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, EMA_cal.getTimeInMillis(), pIntent);


        startActivity(new Intent(this, ClockfaceActivity.class));

    }

    public void proceed2EMAClick(View v) {


        long yourms = System.currentTimeMillis();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date resultdate1 = new Date(yourms);

        String buttonMetricText = "WaitingRoomAcivity, PROCEED, " + sdf1.format(resultdate1) + "\n";
        String fileName1 = "buttons";
        //FileOutputStream fos = null;

        saveStringToFile(fileName1, buttonMetricText);

        startActivity(new Intent(this, dEMAActivity.class));

    }


    public void dismissClick(View v) {
        startActivity(new Intent(this, ClockfaceActivity.class));

    }

}