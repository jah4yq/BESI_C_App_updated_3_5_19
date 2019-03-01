package edu.virginia.ece.inertia.besic.besi_c;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ControlService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();


        Intent dialogIntent = new Intent(this, ClockfaceActivity.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(dialogIntent);




    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {




        Intent dialogIntent = new Intent(this, ClockfaceActivity.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(dialogIntent);


/*
            if (ClockfaceActivity.CHECKER == 1) {

                Intent dialogIntent = new Intent(this, ClockfaceActivity.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(dialogIntent);
            } else if (ClockfaceActivity.CHECKER == 2) {

                Intent dialogIntent = new Intent(this, PainActivity.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(dialogIntent);
            }
*/
/*
        if (ClockfaceActivity.STATE == 0) { //Pull Clockface
            if (ClockfaceActivity.DISABLE == 0 && ClockfaceActivity.CHECKER == 1) {
                Intent dialogIntent = new Intent(this, ClockfaceActivity.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(dialogIntent);

            }
        } else if (ClockfaceActivity.STATE == 1) { //Pull PainActivity
            if (ClockfaceActivity.DISABLE == 0 && ClockfaceActivity.CHECKER == 1) {
                Intent dialogIntent = new Intent(this, PainActivity.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(dialogIntent);
            }

        } else if (ClockfaceActivity.STATE == 2) { //Pull Pain EMA
            if (ClockfaceActivity.DISABLE == 0 && ClockfaceActivity.CHECKER == 1) {
                Intent dialogIntent = new Intent(this, EMAActivity.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(dialogIntent);
            }

        } else if (ClockfaceActivity.STATE == 3) { //Pull Waiting Room 1
            if (ClockfaceActivity.DISABLE == 0 && ClockfaceActivity.CHECKER == 1) {
                Intent dialogIntent = new Intent(this, EMAActivity.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(dialogIntent);
            }

        }
*/
            return Service.START_REDELIVER_INTENT;
        }



        @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
