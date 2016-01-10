package com.iurii.game2048;


public class ConsoleView implements View {
    private Presenter presenter;
    private Player player;
    private boolean doPolling = true;

    public ConsoleView(Player player) {
        this.player = player;
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showGame() {
        presenter.start();

        while (doPolling) {
            Move move = player.doMove();
            presenter.processPlayerMove(move);
        }
    }

    @Override
    public void startClosing() {
        doPolling = false;
    }

    @Override
    public void getVibrator() {
    }

    @Override
    public void displayRecord() {

    }

    @Override
    public void displayCount() {

    }

    @Override
    public void displayBoard(Tile[] board) {
     /*   for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++)
                System.out.print(board[row][col] + "\t");

            System.out.println();
        }*/
    }
}