package ru.dimasokol.school.demopreferences;

import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

public class PreferencesFragment extends PreferenceFragmentCompat {

    private static final String ARG_ROOT = "rootScreen";

    public static PreferencesFragment newInstance(String root) {
        Bundle args = new Bundle();
        args.putString(ARG_ROOT, root);

        PreferencesFragment fragment = new PreferencesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, getArguments().getString(ARG_ROOT));

        Preference toastPreference = findPreference(getString(R.string.pref_key_toast));
        if (toastPreference != null) {
            toastPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Toast.makeText(requireContext(), "onPreferenceClickListener", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

        Preference listPreference = findPreference(getString(R.string.pref_key_list));
        if (listPreference != null) {
            listPreference.setSummaryProvider(new Preference.SummaryProvider() {
                @Override
                public CharSequence provideSummary(Preference preference) {
                    String value = ((ListPreference) preference).getValue();

                    if (value != null) {
                        return getResources().getStringArray(R.array.demo_list_help)[Integer.valueOf(value) - 1];
                    }

                    return "";
                }
            });
        }
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (getString(R.string.pref_key_toast).equals(preference.getKey())) {
            Toast.makeText(requireContext(), "onPreferenceTreeClick", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onPreferenceTreeClick(preference);
    }

    @Override
    public void onNavigateToScreen(PreferenceScreen preferenceScreen) {
        super.onNavigateToScreen(preferenceScreen);
    }
}
