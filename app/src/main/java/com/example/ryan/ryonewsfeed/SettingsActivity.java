package com.example.ryan.ryonewsfeed;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Log.v("SettingsActivity", " SETTINGS ACTIVITY STARTED");

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //Return to the parent activity
        NavUtils.navigateUpFromSameTask(this);
    }

    public static class SettingsPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.v("SettingsActivity1212", " SETTINGS Preference FRAGMENT STARTED");

            addPreferencesFromResource(R.xml.settings_preference);
            Preference orderByKey = findPreference(getString(R.string.settings_order_by_key));
            Preference pageSizeKey = findPreference(getString(R.string.settings_min_page_key));
            bindPreferenceSummaryToValue(orderByKey);
            bindPreferenceSummaryToValue(pageSizeKey);

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
            //When preference is changed, set the summary correctly.
            String stringValue = newValue.toString();
            if (preference instanceof ListPreference){
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0){
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);

                }
            }else {
                preference.setSummary(stringValue);
            }
            return true;
        }
    }
}


