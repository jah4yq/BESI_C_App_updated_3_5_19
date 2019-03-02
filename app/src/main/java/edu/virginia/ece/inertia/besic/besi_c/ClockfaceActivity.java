package edu.virginia.ece.inertia.besic.besi_c;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.virginia.ece.inertia.besic.besi_c.utils.DateTimeUtil;
import edu.virginia.ece.inertia.besic.besi_c.utils.WadaUtils;

import static edu.virginia.ece.inertia.besic.besi_c.utils.FileUtil.saveStringToFile;

//import edu.virginia.cs.mooncake.wada.utils.DateTimeUtil;
//import edu.virginia.cs.mooncake.wada.utils.WadaUtils;

public class ClockfaceActivity extends Activity {
    String file_tag;
    String mac;
    StringBuilder strBuilder,strbldr;
    Handler handler;
    public int EMA_FLAG;
    public long HR_time;
    //Handler handler= new Handler();
    private PowerManager powerManager;
    private PowerManager.WakeLock wakeLock;
    int sensecount;
    public static int SLEEP_FLAG;
    public static int CHECKER = 0;
    public static int SUSPEND_CHECK = 0;
    public static int DISABLE = 0;
    public static int STATE = 0;
    public static Button sleep;
    public static int EMAbuzzer = 0;

    public static int dEMAEnable = 0;


    //public static String PTorCG = "PT";
    public static String PTorCG = "CG";
    int EMA_HOUR = 21;
    int EMA_MINUTE = 30;




    private static final String FILE_NAME = "pain.txt";

    private TextView batteryTxt;
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            batteryTxt.setText("Battery: " + String.valueOf(level) + "%");
        }
    };
/* //UNCOMMENT THIS
    @Override
    protected void onStop() {
        super.onStop();

        CHECKER=1;
 //UNCOMMENT THIS!
        Intent check = new Intent(this, ControlService.class);
        check.putExtra("stop", "" + System.currentTimeMillis());
        startService(check);

    }
*/



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memento_main_clockface);




        //CHECKER=0;

        batteryTxt = (TextView) this.findViewById(R.id.batteryTxt);
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyWakeLockTag");
        wakeLock.acquire();

        //Log.i("EMA_FLAG = ", Integer.toString(EMA_FLAG));

        //count = 0;
        //maxCount = 60 * 300;

        strBuilder = new StringBuilder();

        TextView date = (TextView)findViewById(R.id.dateView);
        setDate(date);

        sensecount = 0;

        sleep = (Button) findViewById(R.id.sleepButton);

/*
        Intent check = new Intent(this, ControlService.class);
        check.putExtra("stop", "" + System.currentTimeMillis());
        startService(check);
*/


        Context context = this;
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        // Are we charging / charged?
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

        final boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

//WORKING SENSOR CODE


        Intent i = new Intent(this, WatchSensorService.class);
        //String tag =   mac + "-" + WadaUtils.getTag(this.getApplicationContext()) +"-" +DateTimeUtil.getDateTimeString(System.currentTimeMillis(), 3).replace(' ','-');
        String tag = WadaUtils.getTag(this.getApplicationContext()) +"-" +DateTimeUtil.getDateTimeString(System.currentTimeMillis(), 3).replace(' ','-');

        i.putExtra("start", tag);
        startService(i);


        //Toast.makeText(this,"Accel", Toast.LENGTH_SHORT).show();
/*
        Intent j = new Intent(this, StepSensorService.class);
        //String tag =   mac + "-" + WadaUtils.getTag(this.getApplicationContext()) +"-" +DateTimeUtil.getDateTimeString(System.currentTimeMillis(), 3).replace(' ','-');
        String jtag = WadaUtils.getTag(this.getApplicationContext()) +"-" +DateTimeUtil.getDateTimeString(System.currentTimeMillis(), 3).replace(' ','-');

        j.putExtra("start", jtag);
        startService(j);
*/

        //startHR();
        startStep(); //THIS ON!!!
        //Toast.makeText(this,"Steps", Toast.LENGTH_SHORT).show();
        //startEstimote();
        //startAccel();


        ///////////////////////////////////////////////////////////////////////////////////////////////////

// Create the Handler object (on the main thread by default)
        final Handler handler = new Handler();
// Define the code block to be executed
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                // Do something here on the main thread
                if(sensecount==0){
                    //startHR();
                    if(SLEEP_FLAG == 0){
                        startHR();}
                    startEstimote();



                    long yourmilliseconds = System.currentTimeMillis();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    Date resultdate = new Date(yourmilliseconds);

                    String batteryText = "BATTERY, "+ batteryTxt.getText() + ", " + isCharging + ", " + sdf.format(resultdate) + "\n";
                    String fileName = "battery";
                    FileOutputStream fos = null;

                    saveStringToFile(fileName, batteryText);


                    sensecount++;
                }
                else if(sensecount==1){
                    stopHR();
                    stopEstimote();

                    int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    if (currentHour == EMA_HOUR && dEMAEnable ==0) {
                        startActivity(new Intent(ClockfaceActivity.this, WaitingRoomActivity.class));
                    }

                    sensecount++;
                }
                else if(sensecount==2){

                    int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                    if (currentHour < EMA_HOUR - 1){
                        dEMAEnable = 0;
                    }


                    sensecount++;
                }
                else if(sensecount==3){

                    sensecount++;
                }
                else if(sensecount==4){
                    startEstimote();

                    sensecount++;
                }
                else if(sensecount==5){
                    stopEstimote();

                    sensecount++;
                }
                else if(sensecount==6){

                    sensecount++;
                }
                else if(sensecount==7){

                    sensecount++;
                }
                else if(sensecount==8){

                    sensecount++;
                }
                else if(sensecount==9){
                    startEstimote();

                    sensecount++;
                }
                else {
                    stopEstimote();

                    sensecount = 0;
                }







                Log.d("Handlers", "Called on main thread");
                // Repeat this the same runnable code block again another 2 seconds
                // 'this' is referencing the Runnable object

                //handler.postDelayed(this, 45000); //OG code
                handler.postDelayed(this, 30000);

            }

        };
// Start the initial runnable task by posting through the handler
        handler.post(runnableCode);



        // Create the Handler object (on the main thread by default)
        final Handler pull = new Handler();
// Define the code block to be executed
        Runnable puller = new Runnable() {
            @Override
            public void run() {
                // Do something here on the main thread

                if (STATE ==0){
                    Intent dialogIntent = new Intent(ClockfaceActivity.this, ClockfaceActivity.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
                else if (STATE ==1){
                    Intent dialogIntent = new Intent(ClockfaceActivity.this, PainActivity.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
                else if (STATE ==2){
                    Intent dialogIntent = new Intent(ClockfaceActivity.this, EMAActivity.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
                else if (STATE ==3){
                    Intent dialogIntent = new Intent(ClockfaceActivity.this, EMA2Activity.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
                else if (STATE ==4){
                    Intent dialogIntent = new Intent(ClockfaceActivity.this, WaitingRoomActivity.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
                else if (STATE ==5){
                    Intent dialogIntent = new Intent(ClockfaceActivity.this, WaitingRoomActivity2.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
                else if (STATE ==6){
                    Intent dialogIntent = new Intent(ClockfaceActivity.this, WaitingRoomActivity3.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
                else if (STATE ==7){
                    Intent dialogIntent = new Intent(ClockfaceActivity.this, WaitingRoomActivity3.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
                else if (STATE ==8){
                    Intent dialogIntent = new Intent(ClockfaceActivity.this, DataActivity.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
                else if (STATE ==9) {
                    Intent dialogIntent = new Intent(ClockfaceActivity.this, dEMAActivity.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
                else {
                    Intent dialogIntent = new Intent(ClockfaceActivity.this, ClockfaceActivity.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }


                Log.d("Handlers", "Called on main thread");
                // Repeat this the same runnable code block again another 2 seconds
                // 'this' is referencing the Runnable object

                //pull.postDelayed(this, 30000);
                pull.postDelayed(this, 1000);


            }

        };
// Start the initial runnable task by posting through the handler
        pull.post(puller);




/*
        //Begin nested handler
        final Handler nestedhandler = new Handler();
        Runnable nestedrunnable = new Runnable(){
            @Override
            public void run(){

                stopHR();
                stopEstimote();


                //nestedhandler.postDelayed(this, 60000); //OG code
                nestedhandler.postDelayed(this, 45000);


            }


        };
        nestedhandler.post(nestedrunnable);
*/

        //End nested handler
        /////////////////////////////////////////////////////////////////////////////////////////

 ///////////////////////////////////////////////////////////////////////////////////////////////////
/*
// Create the Handler object (on the main thread by default)
        final Handler handler = new Handler();
// Define the code block to be executed
        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                // Do something here on the main thread


                startHR();
                startEstimote();

                Log.d("Handlers", "Called on main thread");
                // Repeat this the same runnable code block again another 2 seconds
                // 'this' is referencing the Runnable object

                handler.postDelayed(this, 45000);

            }

        };
// Start the initial runnable task by posting through the handler
        handler.post(runnableCode);


        //Begin nested handler
        final Handler nestedhandler = new Handler();
        Runnable nestedrunnable = new Runnable(){
            @Override
            public void run(){

                stopHR();
                stopEstimote();



                nestedhandler.postDelayed(this, 60000);


            }


        };
        nestedhandler.post(nestedrunnable);
        //End nested handler
         /////////////////////////////////////////////////////////////////////////////////////////
*/
/*
        Intent Intent1 = new Intent(this, proximityService.class); //ENABLE THIS!!!
        Intent1.setAction("ACTION_START_SERVICE"); //ENABLE THIS!!!
        //startForegroundService(Intent1);
        //Toast.makeText(this,"Test",Toast.LENGTH_SHORT).show();
        startService(Intent1); //ENABLE THIS!!!
        //Toast.makeText(this,"Test",Toast.LENGTH_SHORT).show();
*/

/*
// Schedule Daily EMA below

        int hour = EMA_HOUR;
        int minute = EMA_MINUTE;

        //Calendar precalendar= Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        Calendar calendarA = Calendar.getInstance();
        Calendar calendarB = Calendar.getInstance();

        //precalendar.set(Calendar.HOUR_OF_DAY,hour);
        //precalendar.set(Calendar.MINUTE,minute-1);
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,01);
        calendarA.set(Calendar.HOUR_OF_DAY,hour);
        calendarA.set(Calendar.MINUTE,minute);
        calendarA.set(Calendar.SECOND,22);
        calendarB.set(Calendar.HOUR_OF_DAY,hour);
        calendarB.set(Calendar.MINUTE,minute);
        calendarB.set(Calendar.SECOND,43);

        //NEXT CREATE THE ALARM TO TRIGGER THE DISABLE SERVICE


        //Intent intent = new Intent(getApplicationContext(),Notification_receiver.class);
        Intent intent = new Intent(this, WaitingRoomActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);

        ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, calendarA.getTimeInMillis(),pendingIntent);

        ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, calendarB.getTimeInMillis(),pendingIntent);
*/

    }


    public void sleepClick (View v){

        long yourms = System.currentTimeMillis();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date resultdate1 = new Date(yourms);

        String buttonMetricText = "ClockfaceAcivity, SLEEP, " + sdf1.format(resultdate1) + "\n";
        String fileName1 = "buttons";
        //FileOutputStream fos = null;

        saveStringToFile(fileName1, buttonMetricText);



        if(SLEEP_FLAG == 0){

            long yourmilliseconds = System.currentTimeMillis();
            //SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss.SSS");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date resultdate = new Date(yourmilliseconds);
            //System.out.println(sdf.format(resultdate));

            //String time = System.currentTimeMillis();
            //String painText = "PAIN, " + resultdate + "\n";
            String sleepText = "SLEEP, , " + sdf.format(resultdate) + "\n";
            String fileName = "pain";
            FileOutputStream fos = null;

            saveStringToFile(fileName, sleepText);
            //Toast.makeText(this, "Pain event marked!", Toast.LENGTH_SHORT).show();

            sleep.setBackgroundColor(Color.DKGRAY);
            SLEEP_FLAG = 1; }
        else if(SLEEP_FLAG == 1){

            long yourmilliseconds = System.currentTimeMillis();
            //SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss.SSS");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date resultdate = new Date(yourmilliseconds);
            //System.out.println(sdf.format(resultdate));

            //String time = System.currentTimeMillis();
            //String painText = "PAIN, " + resultdate + "\n";
            String sleepText = "AWAKE, , " + sdf.format(resultdate) + "\n";
            String fileName = "pain";
            FileOutputStream fos = null;

            saveStringToFile(fileName, sleepText);
            //Toast.makeText(this, "Pain event marked!", Toast.LENGTH_SHORT).show();

            sleep.setBackgroundColor(Color.BLUE);
            SLEEP_FLAG = 0;}

    }

    public void stopClick(View v) {
        startActivity(new Intent(this, WatchMainActivity.class));


        //Stop Sensor Service
/*
        Intent i = new Intent(this, WatchSensorService.class);
        i.putExtra("stop", "" + System.currentTimeMillis());
        startService(i);
*/

        /*
        Intent j = new Intent(this, StepSensorService.class);
        j.putExtra("stop", "" + System.currentTimeMillis());
        startService(j);
*/
        /*
        Intent k = new Intent(this, HRSensorService.class);
        k.putExtra("stop", "" + System.currentTimeMillis());
        startService(k);
*/
        stopHR();
        stopStep();
        stopEstimote();
        //stopAccel();
/*
        Intent Intent1 = new Intent(this, proximityService.class);
        Intent1.setAction("ACTION_STOP_SERVICE");
        startService(Intent1);
*/
    }

    public void goClick(View v) {

        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date resultdate = new Date(yourmilliseconds);

        String buttonMetricText = "ClockfaceAcivity, START, " + sdf.format(resultdate) + "\n";
        String fileName = "buttons";
        FileOutputStream fos = null;

        saveStringToFile(fileName, buttonMetricText);



        DISABLE = 1;
        //SUSPEND_CHECK = 1;
        //CHECKER = 0;

        startActivity(new Intent(this, PainActivity.class));
        //startActivity(new Intent(this, DataActivity.class));

        //Log.i("EMA_FLAG = ", Integer.toString(EMA_FLAG));


    }

    public void setDate (TextView view){
/*
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");//formating according to my need
        String date = formatter.format(today);
        view.setText(date);
        */
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat date = new SimpleDateFormat("MMM dd, yyyy");
        Date resultdate = new Date(yourmilliseconds);

        TextView textView = (TextView)findViewById(R.id.dateView);
        //SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd,yyyy");
        String ds = date.format(resultdate);

        textView.setText(ds);

        //dailyEMA();

    }

    public void startHR (){
        final Intent k = new Intent(this, HRSensorService.class);
        //String tag =   mac + "-" + WadaUtils.getTag(this.getApplicationContext()) +"-" +DateTimeUtil.getDateTimeString(System.currentTimeMillis(), 3).replace(' ','-');
        final String ktag = WadaUtils.getTag(this.getApplicationContext()) +"-" +DateTimeUtil.getDateTimeString(System.currentTimeMillis(), 3).replace(' ','-');

        k.putExtra("start", ktag);
        startService(k);
    }

    public void stopHR (){
        Intent k = new Intent(this, HRSensorService.class);
        k.putExtra("stop", "" + System.currentTimeMillis());
        startService(k);
    }

    public void startStep (){
        Intent jj = new Intent(this, StepSensorService.class);
        //String tag =   mac + "-" + WadaUtils.getTag(this.getApplicationContext()) +"-" +DateTimeUtil.getDateTimeString(System.currentTimeMillis(), 3).replace(' ','-');
        String jtag = WadaUtils.getTag(this.getApplicationContext()) +"-" +DateTimeUtil.getDateTimeString(System.currentTimeMillis(), 3).replace(' ','-');

        jj.putExtra("start", jtag);
        startService(jj);
    }

    public void stopStep (){
        Intent j = new Intent(this, StepSensorService.class);
        j.putExtra("stop", "" + System.currentTimeMillis());
        startService(j);
    }
    public void startEstimote (){
        Intent Intent1 = new Intent(this, proximityService.class); //ENABLE THIS!!!
        Intent1.setAction("ACTION_START_SERVICE"); //ENABLE THIS!!!
        //startForegroundService(Intent1);
        //Toast.makeText(this,"Test",Toast.LENGTH_SHORT).show();
        startService(Intent1); //ENABLE THIS!!!
        //Toast.makeText(this,"Test",Toast.LENGTH_SHORT).show();
    }

    public void stopEstimote (){
        Intent Intent1 = new Intent(this, proximityService.class);
        Intent1.setAction("ACTION_STOP_SERVICE");
        startService(Intent1);
    }


    public void startAccel () {
        Intent Intent2 = new Intent(this, WatchSensorService.class); //ENABLE THIS!!!
        Intent2.setAction("ACTION_START_SERVICE"); //ENABLE THIS!!!
        //startForegroundService(Intent1);
        //Toast.makeText(this,"Test",Toast.LENGTH_SHORT).show();
        startService(Intent2); //ENABLE THIS!!!
        //Toast.makeText(this,"Test",Toast.LENGTH_SHORT).show();
    }

        public void stopAccel (){
            Intent Intent2 = new Intent(this, proximityService.class);
            Intent2.setAction("ACTION_STOP_SERVICE");
            startService(Intent2);
        }

    @Override
    protected void onPause() {
        super.onPause();

        CHECKER=1;
        //UNCOMMENT THIS!
        /*
        Intent check = new Intent(this, ControlService.class);
        check.putExtra("stop", "" + System.currentTimeMillis());
        startService(check);
*/
    }


    @Override
    protected void onResume() {
        super.onResume();
/*
        // Schedule Daily EMA below

        int hour = EMA_HOUR;
        int minute = EMA_MINUTE;

        //Calendar precalendar= Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        Calendar calendarA = Calendar.getInstance();
        Calendar calendarB = Calendar.getInstance();

        //precalendar.set(Calendar.HOUR_OF_DAY,hour);
        //precalendar.set(Calendar.MINUTE,minute-1);
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,00);
        calendarA.set(Calendar.HOUR_OF_DAY,hour);
        calendarA.set(Calendar.MINUTE,minute);
        calendarA.set(Calendar.SECOND,33);
        calendarB.set(Calendar.HOUR_OF_DAY,hour);
        calendarB.set(Calendar.MINUTE,minute+1);
        calendarB.set(Calendar.SECOND,06);

        //NEXT CREATE THE ALARM TO TRIGGER THE DISABLE SERVICE


        //Intent intent = new Intent(getApplicationContext(),Notification_receiver.class);
        Intent intent = new Intent(this, WaitingRoomActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);

        ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, calendarA.getTimeInMillis(),pendingIntent);

        ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, calendarB.getTimeInMillis(),pendingIntent);
*/

        long yourmillis = System.currentTimeMillis();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date resultdate2 = new Date(yourmillis);

        String pagesMetricText = "ClockfaceActivity" + sdf2.format(resultdate2) + "\n";
        String fileName2 = "pages";
        //FileOutputStream fos = null;

        saveStringToFile(fileName2, pagesMetricText);
/*
        Intent check = new Intent(this, ControlService.class);
        check.putExtra("stop", "" + System.currentTimeMillis());
        startService(check);
        */

        EMAbuzzer = 0;


        CHECKER = 0;
        //SUSPEND_CHECK = 0;
        DISABLE = 0;
        STATE = 0;



/*
        Intent check = new Intent(this, ControlService.class);
        check.putExtra("stop", "" + System.currentTimeMillis());
        startService(check);
*/


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


/*
 //Uncomment this
        Intent check = new Intent(this, ControlService.class);
        check.putExtra("stop", "" + System.currentTimeMillis());
        startService(check);
*/

    }

}
