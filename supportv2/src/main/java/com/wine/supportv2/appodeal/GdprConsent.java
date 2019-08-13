package com.wine.supportv2.appodeal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

import com.wine.supportv2.R;

import java.util.Locale;

public class GdprConsent extends AppCompatActivity {

    public static final String key_save_consent = "ASJDASDASDASDASHNHNRDRGGGR";
    public static String policyUrl;


    private View agree;
    private View disagre;
    private static SharedPreferences preference;

    private static Activity acs;
    public static boolean needConsent(Activity ac){

if(acs == null) {
    acs = ac;
}
        preference = acs.getPreferences(MODE_PRIVATE);
        return preference.getInt(key_save_consent, 0) == 0;
    }

    public static boolean consentAccepted(Activity ac){
        preference = ac.getPreferences(MODE_PRIVATE);

        if(preference.getInt(key_save_consent, 0) == 1){
            return true;
        }else if(preference.getInt(key_save_consent, 0) == 2){
            return false;
        }else{
            return false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gdpr_wine);

        agree = findViewById(R.id.accept_gdpr);
        disagre = findViewById(R.id.disagre_gdpr);
        TextView tvText = findViewById(R.id.tv_text);

        String learnMore = "Learn more.";
        if(getLocale(this).getLanguage().equals("es")){
            learnMore = "Obtenga más información.";
        }else{
            learnMore = "Learn more.";
        }
        String mainText = getString(R.string.app_gdpr, getString(R.string.app_name));
        int startPosition = mainText.indexOf(learnMore);
        int endPosition = startPosition + learnMore.length();
        SpannableString spannableMain = new SpannableString(mainText);
        spannableMain.setSpan(new URLSpan(policyUrl), startPosition, endPosition, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        tvText.setMovementMethod(LinkMovementMethod.getInstance());
        tvText.setText(spannableMain);

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference.edit().putInt(key_save_consent, 1).commit();
                AdsController.InitializeAppo(GdprConsent.this, AdsController.key_app, policyUrl);
                finish();
            }
        });

        disagre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference.edit().putInt(key_save_consent, 2).commit();
                AdsController.InitializeAppo(GdprConsent.this, AdsController.key_app, policyUrl);
                finish();
            }
        });

    }


    private Locale getLocale(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return context.getResources().getConfiguration().getLocales().get(0);
        } else{
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }
}
