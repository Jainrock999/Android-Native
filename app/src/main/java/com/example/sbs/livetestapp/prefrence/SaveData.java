package com.example.sbs.livetestapp.prefrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 */
public class SaveData {

    public static String getUserName(Context ctx) {
        String parentname = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        parentname = preferences.getString("UserName", "");
        return parentname;
    }

    public static void setUserName(Context cx, String parentname) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(cx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("UserName", parentname);
        editor.commit();
    }

    public static void logout(Context cx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(cx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("UserName", "");
        editor.commit();
        editor.clear();
    }


}
