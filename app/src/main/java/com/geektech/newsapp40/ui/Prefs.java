package com.geektech.newsapp40.ui;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }


    public void saveBoardState() {
        preferences.edit().putBoolean("board_state", true).apply();
    }

    public boolean isBoardShown() {
        return preferences.getBoolean("board_state", false);
    }

    // сохраняет написанное слово
    public void saveWord(String word) {
        preferences.edit().putString("yellow", word).apply();
    }

    // получает из данных твое слово
    public String getWord() {
        return preferences.getString("yellow", "");
    }

    public void cleanPrefs(){
        preferences.edit().clear().apply();
    }

//     public void saveImage(String image) {
//         preferences.edit().putString("image", image).apply();
//     }
//
//     public int getImage(){
//        return preferences.getInt("image", 1);
//
//     }

    public void prefsCash(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

}