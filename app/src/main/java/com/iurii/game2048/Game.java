package com.iurii.game2048;

public class Game {

    private Board board;
    private TilesFactory tilesFactory;
    private DataBase dataBase;
    private boolean moveIsAuthentic;

    public int getCount() {
        return dataBase.loadCount();
    }

    //// TODO: 11.01.2016 написать тест или тесты
    public int getCurrentScore() {
        return board.getTheAmountOfNumbersCollapsingBoards();
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

        saveTiles();
        saveCount();
    }

    //// TODO: 11.01.2016 написать тест
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

        saveTiles();
        saveCount();
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

    public boolean isMoveIsAuthentic() {
        return moveIsAuthentic;
    }

    private void setScore() {
        int score = dataBase.loadCount();
        board.setTheAmountOfNumbersCollapsingBoards(score);
    }

    private void setSaveTiles() {
        Tile[] tiles = dataBase.loadTiles();
        for (Tile tile : tiles) {
            board.set(tile);
        }
    }

    private void saveTiles() {
        dataBase.saveTiles(board.getTiles());
    }

    private void saveCount() {
        dataBase.saveCount();
    }
}