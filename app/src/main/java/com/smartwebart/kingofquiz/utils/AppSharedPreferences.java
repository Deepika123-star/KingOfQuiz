package com.smartwebart.kingofquiz.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.gson.Gson;

public class AppSharedPreferences extends AndroidViewModel {

//    INSTANCE;

    private static final String PREF_NAME = "SMS";
    private static final String loginDetails = "loginDetails";
    private Context context;

    SharedPreferences preferences;

    public AppSharedPreferences(@NonNull Application application) {
        super(application);
        context = getApplication().getApplicationContext();
    }

    public SharedPreferences getPreferences() {
        if (preferences == null) {
            preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
        return preferences;
    }

    public String getLoginDetails() {
        return getPreferences().getString(loginDetails, "");
    }

    public void setLoginDetails(String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(loginDetails, value);
        editor.commit();
    }





    public Context getContext() {
        return context;
    }
}
