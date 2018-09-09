package com.example.ryan.ryonewsfeed;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Log.v("SettingsActivity", " SETTINGS ACTIVITY STARTED");
        // Toolbar toolbar = (Toolbar) findViewById(R.id.customToolbar);
        //setSupportActionBar(toolbar);


    }

    public static class SettingsPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.v("SettingsActivity1212", " SETTINGS Preference FRAGMENT STARTED");

            addPreferencesFromResource(R.xml.settings_preference);
            Preference orderByKey = findPreference(getString(R.string.settings_order_by_key));
            bindPreferenceSummaryToValue(orderByKey);


        }

        private void bindPreferenceSummaryToValue(Preference orderByKey) {
            orderByKey.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(orderByKey.getContext());
            String preferenceString = preferences.getString(orderByKey.getKey(), "");
            Log.v("SettingsActivity1212", preferenceString + " This is the order by key");
            onPreferenceChange(orderByKey, preferenceString);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();
            preference.setSummary(stringValue);
            preference.setDefaultValue(newValue);
            return false;
        }
    }
}


