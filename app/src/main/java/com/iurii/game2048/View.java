package com.iurii.game2048;

public interface View {
    void setPresenter(Presenter presenter);

    void displayBoard(Tile[] board);

    void showGame();

    void startClosing();

    void getVibrator();

    void displayRecord();

    void displayCount();

}
