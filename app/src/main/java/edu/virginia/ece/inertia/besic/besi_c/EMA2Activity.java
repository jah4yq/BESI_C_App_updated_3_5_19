package edu.virginia.ece.inertia.besic.besi_c;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static edu.virginia.ece.inertia.besic.besi_c.utils.FileUtil.saveStringToFile;


public class EMA2Activity extends Activity {

    int EMAcount, i,j,k,m,n;
    String question;
    private TextView questionView, answerView;
    private TextView q,a;
    String[] answer;
    int EMA_FLAG;
    long EMA_time;
    String painText0,painText1,painText2,painText3,painText4;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memento_ema_2nd);

        Log.i("EMA_FLAG = ", Integer.toString(EMA_FLAG));

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(1000);

        EMAcount = 0;
        i = 0;
        j = 0;
        k = 0;
        m = 0;
        n = 0;




        if (EMAcount == 0) {

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "Are you still having cancer pain now?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "Is the patient still having cancer pain now?";
            }

            String[] answer = {"YES", "NO"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[i%2]);


        }
        if (EMAcount == 1) {

            //i=5;

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "What is your pain level?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "What is the patient's pain level?";
            }

            String[] answer = {"1","2","3","4","5","6","7","8","9","10"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[j%10]);

        }
        if (EMAcount == 2) {

            //i=2;

            question = "How distressed are you?";

            String[] answer = {"Not at all", "A little", "Moderately", "Very"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[k%4]);

        }
        if (EMAcount == 3) {

            //i=2;

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "How distressed is your caregiver?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "How distressed is the patient?";
            }

            String[] answer = {"Not at all", "A little", "Moderately", "Very", "Unsure"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[m%5]);

        }
        if (EMAcount == 4){

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "Did you take an additional opioid for the pain?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "Did the patient take an additional opioid for the pain?";
            }

            String[] answer = {"YES", "NO"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[n%2]);

        }





    }

    @Override
    protected void onResume() {
        super.onResume();

        ClockfaceActivity.STATE=3;
        ClockfaceActivity.DISABLE=0;
        ClockfaceActivity.CHECKER=0;


/*
        Intent k = new Intent(this, ControlService.class);
        k.putExtra("stop", "" + System.currentTimeMillis());
        startService(k);
*/
    }

    public void incClick(View v) {


        if (EMAcount == 0) {

            i++;

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "Are you still having cancer pain now?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "Is the patient still having cancer pain now?";
            }

            String[] answer = {"YES", "NO"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[i%2]);


        }
        if (EMAcount == 1) {

            j++;
            //i=5;

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "What is your pain level?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "What is the patient's pain level?";
            }

            String[] answer = {"1","2","3","4","5","6","7","8","9","10"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[j%10]);

        }
        if (EMAcount == 2) {

            k++;

            //i=2;

            question = "How distressed are you?";

            String[] answer = {"Not at all", "A little", "Moderately", "Very"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[k%4]);

        }
        if (EMAcount == 3) {

            m++;

            //i=2;

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "How distressed is your caregiver?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "How distressed is the patient?";
            }

            String[] answer = {"Not at all", "A little", "Moderately", "Very", "Unsure"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[m%5]);

        }
        if (EMAcount == 4){

            n++;

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "Did you take an additional opioid for the pain?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "Did the patient take an additional opioid for the pain?";
            }

            String[] answer = {"YES", "NO"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[n%2]);

        }


        //setEMA(answer,question,answer[i]);

    }


    public void nextClick(View v) {

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        //Save answer

        if (EMAcount == 0) {

            String response = a.getText().toString();

            painText0 = "PAIN EMA 1, " + response +", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "pain";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);

            if (i%2 == 1){

                //String fileName = "pain";

                saveStringToFile(fileName, painText0);

                Intent EMA_intent = new Intent(this, EMAActivity.class);

                PendingIntent pIntent = PendingIntent.getActivity(this, 10, EMA_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //AlarmManager.cancel(pIntent);

                ((AlarmManager) getSystemService(ALARM_SERVICE)).cancel(pIntent);

                Toast.makeText(this,"Thank you!", Toast.LENGTH_SHORT).show();

                EMAcount = 0;
                i = 0;

                startActivity(new Intent(this, ClockfaceActivity.class));


            }




        }
        if (EMAcount == 1) {
            String response = a.getText().toString();
            painText1 = "PAIN EMA 2, " + response +", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "pain";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);
        }
        if (EMAcount == 2) {
            String response = a.getText().toString();
            painText2 = "PAIN EMA 3, " + response +", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "pain";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);

        }
        if (EMAcount == 3) {
            String response = a.getText().toString();
            painText3 = "PAIN EMA 4, " + response +", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "pain";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);

        }
        if (EMAcount == 4){
            String response = a.getText().toString();
            painText4 = "PAIN EMA 5, " + response +", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "pain";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);

        }

        EMAcount++;

        if (EMAcount == 0) {

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "Are you still having cancer pain now?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "Is the patient still having cancer pain now?";
            }

            String[] answer = {"YES", "NO"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[i%2]);


        }

        if (EMAcount == 1) {

            //i = 5*100000000;

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "What is your pain level?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "What is the patient's pain level?";
            }

            String[] answer = {"1","2","3","4","5","6","7","8","9","10"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[j%10]);

        }
        if (EMAcount == 2) {

            question = "How distressed are you?";

            String[] answer = {"Not at all", "A little", "Moderately", "Very"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[k%4]);

        }
        if (EMAcount == 3) {

            //i=2;

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "How distressed is your caregiver?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "How distressed is the patient?";
            }

            String[] answer = {"Not at all", "A little", "Moderately", "Very", "Unsure"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[m%5]);

        }
        if (EMAcount == 4){

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "Did you take an additional opioid for the pain?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "Did the patient take an additional opioid for the pain?";
            }

            String[] answer = {"YES", "NO"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (Button)findViewById(R.id.answerView);
            a.setText(answer[n%2]);



/*
            if (EMA_FLAG == Boolean.TRUE){
                EMA_FLAG = Boolean.FALSE;
            }
            else if (EMA_FLAG == Boolean.FALSE){
                EMA_FLAG = Boolean.TRUE;
                EMA_time = System.currentTimeMillis();
            }
*/


        }
        if (EMAcount >= 5){
            //special conditions

            //Toast.makeText(this,"Thank you!",Toast.LENGTH_SHORT).show();

            String fileName = "pain";

            saveStringToFile(fileName, painText0);
            saveStringToFile(fileName, painText1);
            saveStringToFile(fileName, painText2);
            saveStringToFile(fileName, painText3);
            saveStringToFile(fileName, painText4);

            Intent EMA_intent = new Intent(this, EMAActivity.class);

            PendingIntent pIntent = PendingIntent.getActivity(this, 10, EMA_intent, PendingIntent.FLAG_UPDATE_CURRENT);

            //AlarmManager.cancel(pIntent);

            ((AlarmManager) getSystemService(ALARM_SERVICE)).cancel(pIntent);

            Toast.makeText(this,"Thank you!", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, ClockfaceActivity.class));

            /*
            Log.i("EMA_FLAG = ", Integer.toString(EMA_FLAG));
            Toast.makeText(this,"pre-if",Toast.LENGTH_SHORT).show();
            if (EMA_FLAG >= 1) {
                Log.i("EMA_FLAG = ", Integer.toString(EMA_FLAG));
                Toast.makeText(this, "EMA_FLAG = FALSE", Toast.LENGTH_SHORT).show();

                Intent EMA_intent = new Intent(this, EMAActivity.class);

                PendingIntent pIntent = PendingIntent.getActivity(this, 10, EMA_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //AlarmManager.cancel(pIntent);

                ((AlarmManager) getSystemService(ALARM_SERVICE)).cancel(pIntent);
                Log.i("EMA_FLAG = ", Integer.toString(EMA_FLAG));

            }
            else if (EMA_FLAG == 0){
                Log.i("EMA_FLAG = ", Integer.toString(EMA_FLAG));
                //EMA_FLAG = Boolean.TRUE;
                EMA_time = System.currentTimeMillis();
                Toast.makeText(this, "EMA_FLAG = TRUE", Toast.LENGTH_SHORT).show();

                Calendar EMA_cal = Calendar.getInstance();

                EMA_cal.setTimeInMillis(EMA_time + 30000L);

                Intent EMA_intent = new Intent(this, EMAActivity.class);

                PendingIntent pIntent = PendingIntent.getActivity(this, 10, EMA_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                ((AlarmManager) getSystemService(ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, EMA_cal.getTimeInMillis(), pIntent);

                Log.i("EMA_FLAG = ", Integer.toString(EMA_FLAG));
                EMA_FLAG++;
            }
            Log.i("EMA_FLAG = ", Integer.toString(EMA_FLAG));
            EMA_FLAG++;
            Log.i("EMA_FLAG = ", Integer.toString(EMA_FLAG));
            startActivity(new Intent(this, ClockfaceActivity.class));
            */

        }


    }

    public void backClick(View v) {



        if (EMAcount == 0) {
            EMAcount = 0;
        }
        else {

            EMAcount--;

            if (EMAcount == 0) {

                //minusButton.setEnabled(false);

                if (ClockfaceActivity.PTorCG == "PT"){
                    question = "Are you in pain now?";
                }
                if (ClockfaceActivity.PTorCG == "CG"){
                    question = "Is the patient having cancer pain now?";
                }

                String[] answer = {"YES", "NO"};

                q = (TextView) findViewById(R.id.questionView);
                q.setText(question);

                a = (Button) findViewById(R.id.answerView);
                a.setText(answer[i % 2]);


            }

            if (EMAcount == 1) {

                //i = 5 * 100000000;

                if (ClockfaceActivity.PTorCG == "PT"){
                    question = "What is your pain level?";
                }
                if (ClockfaceActivity.PTorCG == "CG"){
                    question = "What is the patient's pain level?";
                }

                String[] answer = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

                q = (TextView) findViewById(R.id.questionView);
                q.setText(question);

                a = (Button) findViewById(R.id.answerView);
                a.setText(answer[j % 10]);

            }
            if (EMAcount == 2) {

                question = "How distressed are you?";

                String[] answer = {"Not at all", "A little", "Moderately", "Very"};

                q = (TextView) findViewById(R.id.questionView);
                q.setText(question);

                a = (Button) findViewById(R.id.answerView);
                a.setText(answer[k % 4]);

            }
            if (EMAcount == 3) {

                //i=2;

                if (ClockfaceActivity.PTorCG == "PT"){
                    question = "How distressed is your caregiver?";
                }
                if (ClockfaceActivity.PTorCG == "CG"){
                    question = "How distressed is the patient?";
                }

                String[] answer = {"Not at all", "A little", "Moderately", "Very", "Unsure"};

                q = (TextView) findViewById(R.id.questionView);
                q.setText(question);

                a = (Button) findViewById(R.id.answerView);
                a.setText(answer[m % 5]);

            }
            if (EMAcount == 4) {

                if (ClockfaceActivity.PTorCG == "PT"){
                    question = "Did you take an opioid for the pain?";
                }
                if (ClockfaceActivity.PTorCG == "CG"){
                    question = "Did the patient take an additional opioid for the pain?";
                }




                String[] answer = {"YES", "NO"};

                q = (TextView) findViewById(R.id.questionView);
                q.setText(question);

                a = (Button) findViewById(R.id.answerView);
                a.setText(answer[n % 2]);

            }

        }
    }





}
