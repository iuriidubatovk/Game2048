package com.iurii.game2048;

public class Game {

    Board board;
    TilesFactory tilesFactory;
    private DataBase dataBase;
    private boolean moveIsAuthentic;

    public int getCount() {
        return dataBase.showCount();
    }

    public int getRecord() {
        return dataBase.getRecord();
    }

    public Game(Board board, TilesFactory tilesFactory) {
        this.board = board;
        this.tilesFactory = tilesFactory;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void startNewGame() {
        throwNewTile();
        throwNewTile();

      //  saveTiles();
    }

    public void continueGame() {
        setScore();
        setSaveTiles();

    }

    public void throwNewTile() {
        Tile tile;

        do {
            tile = tilesFactory.create();
        }
        while (!board.canSetTile(tile));

        board.set(tile);
    }

    public void processPlayerMove(Move move) {
        Board boardBeforeShift = board.createClone();

        switch (move) {
            case left:
                board.shiftLeft();
                break;
            case right:
                board.shiftRight();
                break;
            case down:
                board.shiftDown();
                break;
            case up:
                board.shiftUp();
                break;
        }

        Board boardAfterShift = board;

        moveIsAuthentic = !boardBeforeShift.equals(boardAfterShift);

        if (moveIsAuthentic)
            throwNewTile();

        //saveTiles();
    }

    public boolean isOver() {
        if (!board.isFull()) {
            return false;
        }

        Board cloneBoard = board.createClone();
        cloneBoard.shiftLeft();
        cloneBoard.shiftDown();

        return !cloneBoard.canThrowNewTile();
    }

    public int getScore() {
        return board.getTheAmountOfNumbersCollapsingBoards();
    }

    public boolean isMoveIsAuthentic() {
        return moveIsAuthentic;
    }

    public void setScore() {
        int score = dataBase.loadCount();
        board.setTheAmountOfNumbersCollapsingBoards(score);
    }

    public void setSaveTiles() {
        Tile[] tiles = dataBase.loadTiles();
        for (int i = 0; i < tiles.length; i++) {
            board.set(tiles[i]);
        }
    }

    private void saveTiles() {
        dataBase.saveTiles(board.getTiles());
    }
}