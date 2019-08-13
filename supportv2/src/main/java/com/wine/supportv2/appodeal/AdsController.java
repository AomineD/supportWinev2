package com.wine.supportv2.appodeal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.amazon.device.ads.GDPRInfo;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.ads.NonSkippableVideoCallbacks;

public class AdsController {

    public static String key_app = "nothing";

    public static void InitializeAppo(Activity c, String appkey, String policyUrl) {

        key_app = appkey;
        Log.e("MAIN", "InitializeAppo: "+GdprConsent.consentAccepted(c) );

        if (!GdprConsent.needConsent(c)) {
            Appodeal.muteVideosIfCallsMuted(true);
            Appodeal.initialize(c, appkey, Appodeal.INTERSTITIAL, GdprConsent.consentAccepted(c));
            Appodeal.initialize(c, appkey,  Appodeal.BANNER , GdprConsent.consentAccepted(c));
            Appodeal.initialize(c, appkey,  Appodeal.NON_SKIPPABLE_VIDEO , GdprConsent.consentAccepted(c));

            Appodeal.setLogLevel(com.appodeal.ads.utils.Log.LogLevel.verbose);
        }else{
            GdprConsent.policyUrl = policyUrl;
            c.startActivity(new Intent(c, GdprConsent.class));
        }
    }


    public static void showInterstitial(Activity c, InterstitialCallbacks callbacks){
        if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)) {
            Appodeal.cache(c, Appodeal.INTERSTITIAL);
            Appodeal.setInterstitialCallbacks(callbacks);
            Appodeal.show(c, Appodeal.INTERSTITIAL);
        }
        else
            Log.e("MAIN", "No interstitial ad");

    }



    public static void showVideo(Activity c, NonSkippableVideoCallbacks callbacks){
        if(Appodeal.isLoaded(Appodeal.NON_SKIPPABLE_VIDEO)) {
            Appodeal.cache(c, Appodeal.NON_SKIPPABLE_VIDEO);
            Appodeal.setNonSkippableVideoCallbacks(callbacks);
            Appodeal.show(c, Appodeal.NON_SKIPPABLE_VIDEO);
        }
        else
            Log.e("MAIN", "No NONSKIPPABLE VIDEO ad");

    }


    public static void showBannerBottom(Activity c){
        Appodeal.show(c, Appodeal.BANNER_BOTTOM);
    }
}
