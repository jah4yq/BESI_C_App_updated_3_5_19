package edu.virginia.ece.inertia.besic.besi_c;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.virginia.ece.inertia.besic.besi_c.utils.DateTimeUtil;
import edu.virginia.ece.inertia.besic.besi_c.utils.ServiceUtil;
import edu.virginia.ece.inertia.besic.besi_c.utils.WadaUtils;


public class WatchMainActivity extends Activity {

    private PowerManager powerManager;
    private PowerManager.WakeLock wakeLock;
    final static String folderName = Environment.getExternalStorageDirectory() + "/WatchData";
    private TextView tvStatus;
    Button btn;
    Handler handler;
    boolean layoutInflated = false;
    String mac;

    private static final int REQUEST_CODE = 1;
    private static String[] PERMISSIONS_LIST = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_watch_main);
        setContentView(R.layout.memento_start); //ENABLE THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        //wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"Estimote:MyWakeLock");
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"stayawake");

        wakeLock.acquire();

        startActivity(new Intent(this, ClockfaceActivity.class)); //ENABLE THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

/*
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //DONT NEED THIS
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub); //NOT SURE IF I NEED THIS

        //if (getResources().getConfiguration().isScreenRound() == false) {


            stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
                @Override
                public void onLayoutInflated(WatchViewStub stub) {
                    tvStatus = (TextView) stub.findViewById(R.id.tvStatus);
                    btn = (Button) stub.findViewById(R.id.btnStartStop);
                    refreshStatus();
                    layoutInflated = true;
                }
            });

        //}

        handler = new Handler();
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mac = Build.SERIAL; //getMac();

        //Check if storage read-write permission is enabled
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1)
            verifyPermissions(this);
*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (layoutInflated)
            refreshStatus();

    }


    public void btnClick(View v) {
        if (v.getId() == R.id.btnStartStop) {
            boolean status = ServiceUtil.isMySensorServiceRunning(this.getApplicationContext(), WatchSensorService.class.getName());
            if (btn.getText().equals("Start") && status == false) {
                Intent i = new Intent(this, WatchSensorService.class);
                String tag = mac + "-" + WadaUtils.getTag(this.getApplicationContext()) +"-" +DateTimeUtil.getDateTimeString(System.currentTimeMillis(), 3).replace(' ','-');

                i.putExtra("start", tag);
                startService(i);
                btn.setBackgroundResource(R.color.white);
                btn.setEnabled(false);
                handler.postDelayed(runnable, 2000);
            } else if (btn.getText().equals("Stop") && status == true) {
                Intent i = new Intent(this, WatchSensorService.class);
                i.putExtra("stop", "" + System.currentTimeMillis());
                startService(i);
                btn.setEnabled(false);
                btn.setText("Stopped... Press Refresh");
                btn.setBackgroundResource(R.color.grey);
            }
        } else if (v.getId() == R.id.btnRefresh) {
            refreshStatus();

        } else if (v.getId() == R.id.btnData) {
            startActivity(new Intent(this, DataActivity.class));
        } else if (v.getId() == R.id.btnTag) {
            startActivity(new Intent(this, TagActivity.class));
        }


    }


    public void startClick(View v) {


        /*
        //Start Sensor Service
        Intent i = new Intent(this, WatchSensorService.class);
        String tag = mac + "-" + WadaUtils.getTag(this.getApplicationContext()) +"-" +DateTimeUtil.getDateTimeString(System.currentTimeMillis(), 3).replace(' ','-');

        i.putExtra("start", tag);
        startService(i);
        */


/*
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY,11);
        calendar.set(Calendar.MINUTE,55);


        Intent intent = new Intent(getApplicationContext(),Notification_receiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        */

        //Switch watch face
        startActivity(new Intent(this, ClockfaceActivity.class));

    }



    public void refreshStatus() {
        boolean status = ServiceUtil.isMySensorServiceRunning(this.getApplicationContext(), WatchSensorService.class.getName());

        if (status == true) {
            btn.setText("Stop");
            btn.setBackgroundResource(R.color.red);
        } else {
            btn.setText("Start");
            btn.setBackgroundResource(R.color.green);
        }

        tvStatus.setText(WadaUtils.getTag(this.getApplicationContext()));
        btn.setEnabled(true);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            refreshStatus();
        }
    };


    /* public String getMac() {
        String mac = SharedPrefUtil.getSharedPref("mac", this.getApplicationContext());
        if (mac == null) {
            mac = "na";
            WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
            if(wifiManager !=null ){
                mac = wifiManager.getConnectionInfo().getMacAddress();
                if(mac!=null)
                    mac = mac.replace(":", "").toLowerCase();
            }
            SharedPrefUtil.putSharedPref("mac", mac, this.getApplicationContext());
        }
        return mac;
    }*/


    public static void verifyPermissions(Activity activity) {
        int permission1 = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission2 = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        int permission3 = ActivityCompat.checkSelfPermission(activity, Manifest.permission.BODY_SENSORS);
        if (permission1 != PackageManager.PERMISSION_GRANTED || permission2 != PackageManager.PERMISSION_GRANTED || permission3 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_LIST,
                    REQUEST_CODE
            );
        }

    }

}