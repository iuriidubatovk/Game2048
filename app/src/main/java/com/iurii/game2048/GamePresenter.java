package com.iurii.game2048;

public class GamePresenter implements Presenter {
    private View view;
    private Game game;
    private Board board;

    public GamePresenter(Game game, Board board) {
        this.game = game;
        this.board = board;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void start() {
     /*  if (FragmentReload.isReloadGame()) {
           game.startNewGame();

       } else game.continueGame();*/
        game.startNewGame();
        displayGame();
    }

    @Override
    public void processPlayerMove(Move move) {
        game.processPlayerMove(move);

        displayGame();
        isVibration();

        if (game.isOver()) {
            view.startClosing();
        }
    }

    @Override
    public int currentScore() {
        return game.getCurrentScore();
    }

    @Override
    public int getCount() {
        return game.getCount();
    }

    @Override
    public int getRecord() {
        return game.getRecord();
    }

    private void displayGame() {
        Tile[] tiles = board.getTiles();

        view.displayBoard(tiles);
        view.displayCount();
        view.displayRecord();
    }

    private void isVibration() {
        if (game.isMoveIsAuthentic() && MainActivity.PrefsFragment.isVibrationIncluded()) {
            view.getVibrator();
        }
    }
}