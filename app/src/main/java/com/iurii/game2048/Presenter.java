package com.iurii.game2048;

public interface Presenter {
    void start();
    void processPlayerMove(Move move);
    int getScore();
    int getCount();
    int getRecord();
}


