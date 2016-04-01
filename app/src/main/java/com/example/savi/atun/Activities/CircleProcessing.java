package com.example.savi.atun.Activities;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.savi.atun.R;

public class CircleProcessing extends Activity {
    ProgressBar progressBar ;
    Button button_progressbar ;
    Handler handler ;
    TextView textview_progress ;
    int progress_count= 0, calculation_made=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_processing);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        button_progressbar = (Button)findViewById(R.id.btn_processBar);
        textview_progress = (TextView)findViewById(R.id.textview_progress);
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 100); // see this max value coming back here, we animale towards that value
      //  animation.setDuration(5000); //in milliseconds
        animation.setInterpolator(new CycleInterpolator(0));
        animation.start();
        handler = new Handler();
        button_progressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_count= 0;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                       /* handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(progress_count);
                            }
                        });*/
                        final long MAX_VALUE = 1000000000L;
                        long ONETH_PART = MAX_VALUE / 100;
                        long NEW_PART = 0;
                        NEW_PART = ONETH_PART;
                        int value = 0;
                        while (progress_count < 100) {
                            for (calculation_made = 1; calculation_made <= MAX_VALUE; calculation_made++) {

                                if (NEW_PART == calculation_made) {
                                    progress_count = progress_count + 1;
                                    NEW_PART = NEW_PART + ONETH_PART;
                                     progressBar.setProgress(progress_count);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            textview_progress.setText(progress_count +" %");
                                        }
                                    });
                                }


                            }
                        }

                    }
                }).start();
            }
        });
    }

   void setTextview(String process){
       textview_progress.setText(process);
   }
}
