package edu.virginia.ece.inertia.besic.besi_c;/*

package edu.virginia.cs.mooncake.wada;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.RegionUtils;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.example.davealex.estimoo.R;
import com.google.android.gms.common.internal.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import android.os.Handler;
import android.widget.Toast;

import static com.example.davealex.estimoo.Commn.Sharedd.writeToFile;

import edu.virginia.cs.mooncake.wada.Commn.Sharedd;


public class MainActivity extends WearableActivity {
    //private ContentTestBinding binding;
   // private ServiceConnection serviceConnection;
   // private proximityService service;
   // private Disposable disposable;
    //private ListView mListView;
   // private BeacAdapter adapter;
   // Handler handler;
    private TextView mTextView;
    private Button startButton,stopButton;
   // private String hii ="hii";
  //  boolean mBound = false;
   // private ArrayList<Beac> eas11;
  //  private PowerManager powerManager;
   // private PowerManager.WakeLock wakeLock;
    // private Disposable disposable;
  // public static String MAIN_ACTION = "com.nkdroid.alertdialog.action.main";









    @Override
    protected void onDestroy() {
      //  unregisterReceiver(receiver);
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.listview_Bea);
        // Log.i("Thread Called0", "Hi" + "_" + eas1.size());
       // Intent noiseIntent = new Intent(this, proximityService.class);
       // noiseIntent.putExtra("check",1);
        //noiseIntent.putExtra("hi",hii);
        //registerReceiver(receiver, new IntentFilter(
        //     proximityService.NOTIFICATION));

                //intent1.setAction(proximityService.ACTION_STOP_SERVICE);
              //  startForegroundService(noiseIntent);
        startButton= (Button)findViewById(R.id.button1);
       stopButton = (Button)findViewById(R.id.button2);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this, proximityService.class);
                startIntent.setAction("ACTION_START_SERVICE");
                startForegroundService(startIntent);
            }
        });

       stopButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              Intent Intent1 = new Intent(MainActivity.this, proximityService.class);
               Intent1.setAction("ACTION_STOP_SERVICE");
               startForegroundService(Intent1);
          }
       });











    }



    @Override
    protected void onPause() {
        super.onPause();
     //   unregisterReceiver(receiver);
    }
    @Override
    protected void onResume() {
        super.onResume();
      //  registerReceiver(receiver, new IntentFilter(
       //         proximityService.NOTIFICATION));
    }

}


*/



