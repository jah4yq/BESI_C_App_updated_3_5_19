package edu.virginia.ece.inertia.besic.besi_c;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static edu.virginia.ece.inertia.besic.besi_c.utils.FileUtil.saveStringToFile;


public class PainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ClockfaceActivity.CHECKER = 0;

        //ClockfaceActivity.DISABLE=0;

        setContentView(R.layout.memento_pain);

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);




        /*
        Intent k = new Intent(this, ControlService.class);
        k.putExtra("stop", "" + System.currentTimeMillis());
        startService(k);
*/



    }
/*
    @Override
    protected void onStop() {
        super.onStop();

        ClockfaceActivity.CHECKER = 0;

    }
*/
    //CHECK THIS
    @Override
    protected void onStop() {
        super.onStop();


        ClockfaceActivity.CHECKER=1;
/*
        Intent check = new Intent(this, ControlService.class);
        check.putExtra("stop", "" + System.currentTimeMillis());
        startService(check);
*/

        //ClockfaceActivity.CHECKER=2;

        /*
        Intent k = new Intent(this, ControlService.class);
        k.putExtra("stop", "" + System.currentTimeMillis());
        startService(k);
*/

    }


    @Override
    protected void onResume() {
        super.onResume();

        long yourmillis = System.currentTimeMillis();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date resultdate2 = new Date(yourmillis);

        String pagesMetricText = "PainActivity" + sdf2.format(resultdate2) + "\n";
        String fileName2 = "pages";
        //FileOutputStream fos = null;

        saveStringToFile(fileName2, pagesMetricText);

        ClockfaceActivity.STATE=1;
        ClockfaceActivity.DISABLE=0;
        ClockfaceActivity.CHECKER=0;


/*
        Intent k = new Intent(this, ControlService.class);
        k.putExtra("stop", "" + System.currentTimeMillis());
        startService(k);
*/
    }
    //END CHECK THIS
/*
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent check = new Intent(this, ControlService.class);
        check.putExtra("stop", "" + System.currentTimeMillis());
        startService(check);

    }
*/
    public void backClick(View v) {
        //ClockfaceActivity.CHECKER=0;

        long yourms = System.currentTimeMillis();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date resultdate1 = new Date(yourms);

        String buttonMetricText = "PainAcivity, CANCEL, " + sdf1.format(resultdate1) + "\n";
        String fileName1 = "buttons";
        //FileOutputStream fos = null;

        saveStringToFile(fileName1, buttonMetricText);

        ClockfaceActivity.DISABLE = 1;

        startActivity(new Intent(this, ClockfaceActivity.class));

    }

    public void painClick(View v) {
        //Log.i("EMA_FLAG = ", Integer.toString(EMA_FLAG));
        //EMA_FLAG = 1;

        long yourms = System.currentTimeMillis();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date resultdate1 = new Date(yourms);

        String buttonMetricText = "PainAcivity, PAIN, " + sdf1.format(resultdate1) + "\n";
        String fileName1 = "buttons";
        //FileOutputStream fos = null;

        saveStringToFile(fileName1, buttonMetricText);

        long yourmilliseconds = System.currentTimeMillis();
        //SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss.SSS");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date resultdate = new Date(yourmilliseconds);
        //System.out.println(sdf.format(resultdate));

        //String time = System.currentTimeMillis();
        //String painText = "PAIN, " + resultdate + "\n";
        String painText = "PAIN, , " + sdf.format(resultdate) + "\n";
        String fileName = "pain";
        FileOutputStream fos = null;

        saveStringToFile(fileName, painText);
        //Toast.makeText(this, "Pain event marked!", Toast.LENGTH_SHORT).show();

        ClockfaceActivity.DISABLE = 1;

        startActivity(new Intent(this, EMAActivity.class));

    }



}