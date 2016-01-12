package com.iurii.game2048;

import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private FragmentReload fragmentReload = new FragmentReload();
    SharedPreferences sp;
    private static boolean isVibrationIncluded;

    @Override
    protected void onResume() {
        //// TODO: 06.01.2016 изменить ключь
        isVibrationIncluded = sp.getBoolean("notif", false);

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //todo почитать как нужно передавать фрагмент так или может через конструктор
        UsersFragment fragment = new UsersFragment();
        setFragment(fragment);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmCont, new PrefsFragment()).addToBackStack(null)
                                .commit();
                return true;

            case R.id.action_share:
                Toast.makeText(this, item + " selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_reload:
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().add(R.id.fragmCont, fragmentReload).addToBackStack(null).commit();
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }

    public void setFragment(UsersFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmCont, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public static class PrefsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
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
       //// TODO: 06.01.2016 static плохо лучше передать ссылку на MainActivity и у незвать нестатический класс
        //но тогда нужно будет везде где используеться этот метод передавать и MainActivity
        public static boolean isVibrationIncluded() {
            return isVibrationIncluded;
        }
    }
}

