package com.example.menaadel.salesapp.DataStorage.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.menaadel.salesapp.Utils.Constants;

/**
 * Created by MenaAdel on 6/12/2018.
 */

public class DataSharedPreference {
    private SharedPreferences mPreference;

    /**
     * constructor for creating instance of SharedPreference class for saving data
     */
    public DataSharedPreference(Context context) {
        mPreference = context.getSharedPreferences(Constants.PREF_NAME, context.MODE_PRIVATE);
    }

    /**
     * save String value in sp
     */
    public void saveStringToSharedPreference(String key, String value) {
        SharedPreferences.Editor mEditor = mPreference.edit();
        mEditor.putString(key, value);
        mEditor.apply();
    }

    public String retrieveStringFromSharedPreference(String key) {

        return mPreference.getString(key, "");
    }

    public void removeSharedPreference(String key) {
        SharedPreferences.Editor mEditor = mPreference.edit();
        mEditor.remove(key);
        mEditor.apply();
    }
}
