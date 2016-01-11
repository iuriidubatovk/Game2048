package com.iurii.game2048;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class DataBase {

    private Presenter presenter;
    private Context context;
    private final String PREFS_NAME = "AOP_PREFS";
    private final String SAVED_TILES = "saved_tiles";
    private final String SAVED_COUNT = "saved_count";
    private final String SAVED_RECORD = "saved_record";

    private String firsDownApplication = "[{\"coordinate\":{\"col\":0,\"row\":0},\"isLoadAnimation\":true,\"value\":4}," +
                                  "{\"coordinate\":{\"col\":1,\"row\":0},\"isLoadAnimation\":false,\"value\":0}," +
                                  "{\"coordinate\":{\"col\":2,\"row\":0},\"isLoadAnimation\":false,\"value\":0}," +
                                  "{\"coordinate\":{\"col\":3,\"row\":0},\"isLoadAnimation\":false,\"value\":0}," +
                                  "{\"coordinate\":{\"col\":0,\"row\":1},\"isLoadAnimation\":false,\"value\":0}," +
                                  "{\"coordinate\":{\"col\":1,\"row\":1},\"isLoadAnimation\":false,\"value\":0}," +
                                  "{\"coordinate\":{\"col\":2,\"row\":1},\"isLoadAnimation\":false,\"value\":0}," +
                                  "{\"coordinate\":{\"col\":3,\"row\":1},\"isLoadAnimation\":false,\"value\":0}," +
                                  "{\"coordinate\":{\"col\":0,\"row\":2},\"isLoadAnimation\":false,\"value\":0}," +
                                  "{\"coordinate\":{\"col\":1,\"row\":2},\"isLoadAnimation\":false,\"value\":0}," +
                                  "{\"coordinate\":{\"col\":2,\"row\":2},\"isLoadAnimation\":false,\"value\":0}," +
                                  "{\"coordinate\":{\"col\":3,\"row\":2},\"isLoadAnimation\":false,\"value\":0}," +
                                  "{\"coordinate\":{\"col\":0,\"row\":3},\"isLoadAnimation\":false,\"value\":0}," +
                                  "{\"coordinate\":{\"col\":1,\"row\":3},\"isLoadAnimation\":true,\"value\":2}," +
                                  "{\"coordinate\":{\"col\":2,\"row\":3},\"isLoadAnimation\":false,\"value\":0}," +
                                  "{\"coordinate\":{\"col\":3,\"row\":3},\"isLoadAnimation\":false,\"value\":0}]";


    public DataBase(Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    //// TODO: 06.01.2016 подумать можно ли уменьшить базу данных например убрать повторяющийся код
    ///// TODO: 11.01.2016 написать тесты
    public void saveTiles(Tile[] tiles) {
        String jsonStr = new Gson().toJson(tiles);
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = settings.edit();
        ed.putString(SAVED_TILES, jsonStr);

        ed.apply();
    }

    public Tile[] loadTiles() {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String saveTiles = settings.getString(SAVED_TILES, firsDownApplication);
        Tile[] tiles = new Gson().fromJson(saveTiles, Tile[].class);

        return tiles;
    }

    private void saveCount() {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = settings.edit();
        ed.putInt(SAVED_COUNT, presenter.currentScore());
        ed.apply();
    }

    public int loadCount() {
        SharedPreferences count = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int score = count.getInt(SAVED_COUNT, 0);

        return score;
    }

    public int getSaveCount() {
        saveCount();

        return loadCount();
    }

    private void saveRecord() {
        SharedPreferences setting = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = setting.edit();
        ed.putInt(SAVED_RECORD, presenter.currentScore());
        ed.apply();
    }

    private int loadRecord() {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        return settings.getInt(SAVED_RECORD, 0);
    }

    public int getRecord() {
        int currentScore = presenter.currentScore();
        int recordUser = loadRecord();
        if (currentScore > recordUser) {
            saveRecord();
            loadRecord();

        } else
            loadRecord();

        return loadRecord();
    }
}