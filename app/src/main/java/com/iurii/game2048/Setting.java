package com.iurii.game2048;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;


public class Setting extends PreferenceFragment {
    private static boolean isVibrationIncluded;
    SharedPreferences sp;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferenses);

        final CheckBoxPreference checkboxPref = (CheckBoxPreference) getPreferenceManager().findPreference("notif");
        checkboxPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.toString().equals("true")) {

                    isVibrationIncluded = true;
                } else {
                    isVibrationIncluded = false;
                }

                return true;
            }
        });
    }

  /*  @Override
    public void onResume() {
        isVibrationIncluded = sp.getBoolean("notif", false);

        super.onResume();
    }*/

    public static boolean isVibrationIncluded() {
        return isVibrationIncluded;
    }
}