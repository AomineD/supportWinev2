package com.example.supportwinev2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.NonSkippableVideoCallbacks;
import com.wine.supportv2.appodeal.AdsController;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdsController.InitializeAppo(this, "bacc6cca6df83e71cb5e0aa778526952df968f11d0164d1f", "https://www.appodeal.com/privacy-policy");

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       AdsController.showVideo(MainActivity.this, new NonSkippableVideoCallbacks() {

                           @Override
                           public void onNonSkippableVideoLoaded(boolean b) {

                           }

                           @Override
                           public void onNonSkippableVideoFailedToLoad() {

                           }

                           @Override
                           public void onNonSkippableVideoShown() {

                           }

                           @Override
                           public void onNonSkippableVideoFinished() {

                           }

                           @Override
                           public void onNonSkippableVideoClosed(boolean b) {
                               Toast.makeText(MainActivity.this, "Closed", Toast.LENGTH_SHORT).show();
                           }

                           @Override
                           public void onNonSkippableVideoExpired() {

                           }
                       });
                       AdsController.showBannerBottom(MainActivity.this);
                   }
               });

            }
        }, 9000);


      //  Appodeal.startTestActivity((Activity) this);
    }
}
