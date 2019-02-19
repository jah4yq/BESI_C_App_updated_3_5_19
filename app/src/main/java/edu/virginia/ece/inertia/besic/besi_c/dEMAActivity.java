package edu.virginia.ece.inertia.besic.besi_c;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static edu.virginia.ece.inertia.besic.besi_c.utils.FileUtil.saveStringToFile;

//import static edu.virginia.cs.mooncake.wada.utils.FileUtil.saveStringToFile;


public class dEMAActivity extends Activity {

    int EMAcount, i,j,k,l,m,n,o,p,r;
    String question;
    private TextView questionView, answerView;
    private TextView q,a;
    String[] answer;
    String painText0,painText1,painText2,painText3,painText4,painText5,painText6,painText7;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memento_d_ema);




        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(1000);

        EMAcount = 0;
        i = 0;
        j = 0;
        k = 0;
        l = 0;
        m = 0;
        n = 0;
        o = 0;
        p = 0;
        r = 0;



        //date.format(new Date(System.currentTimeMillis()));


        if (EMAcount == 0) {

            question = "How active were you?";

            String[] answer = {"Not at all", "A little", "Moderately", "Very" };

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[i%4]);


        }
        if (EMAcount == 1) {

            //i=5;

            question = "How busy was your home?";

            String[] answer = {"Not at all", "A little", "Moderately", "Very"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[j%4]);

        }
        if (EMAcount == 2) {

            //i=2;

            question = "Time spent outside your home?";

            String[] answer = {"None", "A little", "Medium", "A lot"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[k%4]);

        }
        if (EMAcount == 3) {

            //i=2;

            question = "How much time did you spend with other people?";

            String[] answer = {"None", "A little", "Medium", "A lot"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[l%4]);

        }
        if (EMAcount == 4){

            question = "How distressed were you overall?";

            String[] answer = {"Not at all", "A little", "Moderately", "Very"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[m%4]);

        }
        if (EMAcount == 5){

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "How much did pain interfere with your life?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "How did the patient's pain interfere with your life?";
            }

            String[] answer = {"None", "A little", "Medium", "A lot"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[n%4]);

        }
        if (EMAcount == 6){

            question = "How was your mood overall?";

            String[] answer = {"Poor", "Fair", "Good", "Excellent"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[o%4]);

        }
        if (EMAcount == 7){

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "How distressed was your caregiver overall?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "How distressed was the patient overall?";
            }

            String[] answer = {"Not at all", "A little", "Moderately", "Very", "Unsure"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[p%5]);

        }
        if (EMAcount == 7){

            question = "How would you rate your sleep quality?";

            String[] answer = {"Poor", "Fair", "Good", "Excellent"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[r%4]);

        }




    }

    @Override
    protected void onResume() {
        super.onResume();

        ClockfaceActivity.STATE=9;
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

            question = "How active were you?";

            String[] answer = {"Not at all", "A little", "Moderately", "Very" };

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[i%4]);


        }
        if (EMAcount == 1) {

            j++;

            //i=5;

            question = "How busy was your home?";

            String[] answer = {"Not at all", "A little", "Moderately", "Very"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[j%4]);

        }
        if (EMAcount == 2) {

            k++;

            //i=2;

            question = "Time spent outside your home?";

            String[] answer = {"None", "A little", "Medium", "A lot"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[k%4]);

        }
        if (EMAcount == 3) {

            l++;

            //i=2;

            question = "How much time did you spend with other people?";

            String[] answer = {"None", "A little", "Medium", "A lot"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[l%4]);

        }
        if (EMAcount == 4){

            m++;

            question = "How distressed were you overall?";

            String[] answer = {"Not at all", "A little", "Moderately", "Very"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[m%4]);

        }
        if (EMAcount == 5){

            n++;

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "How much did pain interfere with your life?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "How did the patient's pain interfere with your life?";
            }

            String[] answer = {"None", "A little", "Medium", "A lot"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[n%4]);

        }
        if (EMAcount == 6){

            o++;

            question = "How was your mood overall?";

            String[] answer = {"Poor", "Fair", "Good", "Excellent"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[o%4]);

        }
        if (EMAcount == 7){

            p++;

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "How distressed was your caregiver overall?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "How distressed was the patient overall?";
            }

            String[] answer = {"Not at all", "A little", "Moderately", "Very", "Unsure"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[p%5]);

        }
        if (EMAcount == 8){

            r++;

            question = "How would you rate your sleep quality?";

            String[] answer = {"Poor", "Fair", "Good", "Excellent"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[r%4]);

        }


        //setEMA(answer,question,answer[i]);

    }


    public void nextClick(View v) {



        //Save answer

        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        if (EMAcount == 0) {

            String response = a.getText().toString();

            String painText0 = "DAILY EMA 1, " + response+ ", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "daily";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);

        }
        if (EMAcount == 1) {
            String response = a.getText().toString();
            String painText1 = "DAILY EMA 2, " + response+ ", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "daily";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);
        }
        if (EMAcount == 2) {
            String response = a.getText().toString();
            String painText2 = "DAILY EMA 3, " + response+ ", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "daily";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);

        }
        if (EMAcount == 3) {
            String response = a.getText().toString();
            String painText3 = "DAILY EMA 4, " + response+ ", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "daily";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);

        }
        if (EMAcount == 4){
            String response = a.getText().toString();
            String painText4 = "DAILY EMA 5, " + response+ ", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "daily";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);

        }
        if (EMAcount == 5){
            String response = a.getText().toString();
            String painText5 = "DAILY EMA 6, " + response+ ", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "daily";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);

        }
        if (EMAcount == 6){
            String response = a.getText().toString();
            String painText6 = "DAILY EMA 7, " + response+ ", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "daily";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);

        }
        if (EMAcount == 7){
            String response = a.getText().toString();
            String painText7 = "DAILY EMA 8, " + response+ ", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "daily";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);

        }
        if (EMAcount == 8){
            String response = a.getText().toString();
            String painText7 = "DAILY EMA 9, " + response+ ", " + date.format(new Date(System.currentTimeMillis()))+ "\n";
            String fileName = "daily";
            FileOutputStream fos = null;

            //saveStringToFile(fileName, painText);

        }

        EMAcount++;

        if (EMAcount == 0) {

            question = "How active were you?";

            String[] answer = {"Not at all", "A little", "Moderately", "Very" };

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[i%4]);


        }
        if (EMAcount == 1) {

            //i=5;

            question = "How busy was your home?";

            String[] answer = {"Not at all", "A little", "Moderately", "Very"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[j%4]);

        }
        if (EMAcount == 2) {

            //i=2;

            question = "Time spent outside your home?";

            String[] answer = {"None", "A little", "Medium", "A lot"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[k%4]);

        }
        if (EMAcount == 3) {

            //i=2;

            question = "How much time did you spend with other people?";

            String[] answer = {"None", "A little", "Medium", "A lot"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[l%4]);

        }
        if (EMAcount == 4){

            question = "How distressed were you overall?";

            String[] answer = {"Not at all", "A little", "Moderately", "Very"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[m%4]);

        }
        if (EMAcount == 5){

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "How much did pain interfere with your life?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "How did the patient's pain interfere with your life?";
            }

            String[] answer = {"None", "A little", "Medium", "A lot"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[n%4]);

        }
        if (EMAcount == 6){

            question = "How was your mood overall?";

            String[] answer = {"Poor", "Fair", "Good", "Excellent"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[o%4]);

        }
        if (EMAcount == 7){

            if (ClockfaceActivity.PTorCG == "PT"){
                question = "How distressed was your caregiver overall?";
            }
            if (ClockfaceActivity.PTorCG == "CG"){
                question = "How distressed was the patient overall?";
            }

            String[] answer = {"Not at all", "A little", "Moderately", "Very", "Unsure"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[p%5]);

        }
        if (EMAcount == 8){

            //r++;

            question = "How would you rate your sleep quality?";

            String[] answer = {"Poor", "Fair", "Good", "Excellent"};

            q = (TextView)findViewById(R.id.questionView);
            q.setText(question);

            a = (TextView)findViewById(R.id.answerView);
            a.setText(answer[r%4]);

        }
        if (EMAcount >= 9){
            //special conditions

            String fileName = "daily";

            saveStringToFile(fileName, painText0);
            saveStringToFile(fileName, painText1);
            saveStringToFile(fileName, painText2);
            saveStringToFile(fileName, painText3);
            saveStringToFile(fileName, painText4);
            saveStringToFile(fileName, painText5);
            saveStringToFile(fileName, painText6);
            saveStringToFile(fileName, painText7);

            Toast.makeText(this,"Thank you!", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, ClockfaceActivity.class));

        }


    }

    public void backClick(View v) {


        if (EMAcount == 0) {
            EMAcount = 0;
        }
        else {

            EMAcount--;

            if (EMAcount == 0) {

                question = "How active were you?";

                String[] answer = {"Not at all", "A little", "Moderately", "Very" };

                q = (TextView)findViewById(R.id.questionView);
                q.setText(question);

                a = (TextView)findViewById(R.id.answerView);
                a.setText(answer[i%4]);


            }
            if (EMAcount == 1) {

                //i=5;

                question = "How busy was your home?";

                String[] answer = {"Not at all", "A little", "Moderately", "Very"};

                q = (TextView)findViewById(R.id.questionView);
                q.setText(question);

                a = (TextView)findViewById(R.id.answerView);
                a.setText(answer[j%4]);

            }
            if (EMAcount == 2) {

                //i=2;

                question = "Time spent outside your home?";

                String[] answer = {"None", "A little", "Medium", "A lot"};

                q = (TextView)findViewById(R.id.questionView);
                q.setText(question);

                a = (TextView)findViewById(R.id.answerView);
                a.setText(answer[k%4]);

            }
            if (EMAcount == 3) {

                //i=2;

                question = "How much time did you spend with other people?";

                String[] answer = {"None", "A little", "Medium", "A lot"};

                q = (TextView)findViewById(R.id.questionView);
                q.setText(question);

                a = (TextView)findViewById(R.id.answerView);
                a.setText(answer[l%4]);

            }
            if (EMAcount == 4){

                question = "How distressed were you overall?";

                String[] answer = {"Not at all", "A little", "Moderately", "Very"};

                q = (TextView)findViewById(R.id.questionView);
                q.setText(question);

                a = (TextView)findViewById(R.id.answerView);
                a.setText(answer[m%4]);

            }
            if (EMAcount == 5){

                if (ClockfaceActivity.PTorCG == "PT"){
                    question = "How much did pain interfere with your life?";
                }
                if (ClockfaceActivity.PTorCG == "CG"){
                    question = "How did the patient's pain interfere with your life?";
                }

                String[] answer = {"None", "A little", "Medium", "A lot"};

                q = (TextView)findViewById(R.id.questionView);
                q.setText(question);

                a = (TextView)findViewById(R.id.answerView);
                a.setText(answer[n%4]);

            }
            if (EMAcount == 6){

                question = "How was your mood overall?";

                String[] answer = {"Poor", "Fair", "Good", "Excellent"};

                q = (TextView)findViewById(R.id.questionView);
                q.setText(question);

                a = (TextView)findViewById(R.id.answerView);
                a.setText(answer[o%4]);

            }
            if (EMAcount == 7){

                if (ClockfaceActivity.PTorCG == "PT"){
                    question = "How distressed was your caregiver overall?";
                }
                if (ClockfaceActivity.PTorCG == "CG"){
                    question = "How distressed was the patient overall?";
                }

                String[] answer = {"Not at all", "A little", "Moderately", "Very", "Unsure"};

                q = (TextView)findViewById(R.id.questionView);
                q.setText(question);

                a = (TextView)findViewById(R.id.answerView);
                a.setText(answer[p%5]);

            }
            if (EMAcount == 8){

                //r++;

                question = "How would you rate your sleep quality?";

                String[] answer = {"Poor", "Fair", "Good", "Excellent"};

                q = (TextView)findViewById(R.id.questionView);
                q.setText(question);

                a = (TextView)findViewById(R.id.answerView);
                a.setText(answer[r%4]);

            }


        }
    }




}
