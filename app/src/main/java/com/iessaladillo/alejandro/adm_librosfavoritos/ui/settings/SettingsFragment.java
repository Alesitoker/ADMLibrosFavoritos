package com.iessaladillo.alejandro.adm_librosfavoritos.ui.settings;

import android.os.Bundle;

import com.iessaladillo.alejandro.adm_librosfavoritos.R;

import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }
}
