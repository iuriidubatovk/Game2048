package com.iurii.game2048;
public class Board {
    private final int size = 4;
    private int[][] board;

    public void setTheAmountOfNumbersCollapsingBoards(int theAmountOfNumbersCollapsingBoards) {
        TheAmountOfNumbersCollapsingBoards = theAmountOfNumbersCollapsingBoards;
    }

    private int TheAmountOfNumbersCollapsingBoards;
    private boolean[][] animation;

    public Board() {
        board = new int[size][size];
        animation = new boolean[size][size];
    }

    public void set(Tile tile) {

        board[tile.getCoordinate().getRow()][tile.getCoordinate().getCol()] = tile.getValue();
        animation[tile.getCoordinate().getRow()][tile.getCoordinate().getCol()] = tile.getIsLoadAnimation();
    }
//todo написать на этот метд тест
    public Tile[] getTiles() {
        Tile[] tiles = new Tile[16];
        int nextElement = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {

                tiles[nextElement] = new Tile((board[row][col]), new Coordinate(row, col),animation[row][col]);

                nextElement++;
            }
        }

        return tiles;
    }

    public int[][] getSnapshot() {
        int[][] snapshot = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                snapshot[row][col] = board[row][col];
            }
        }

        return snapshot;
    }

    public void shiftDown() {
        shift(new Coordinate(3, 0), new Coordinate(-1, 0), new Coordinate(0, 1));
    }


    public void shiftLeft() {
        shift(new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(1, 0));
    }

    public void shiftRight() {
        shift(new Coordinate(0, 3), new Coordinate(0, -1), new Coordinate(1, 0));
    }

    public int getTheAmountOfNumbersCollapsingBoards() {
        return TheAmountOfNumbersCollapsingBoards;
    }

    public void shiftUp() {
        shift(new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(0, 1));
    }

    private Vector extractVector(Coordinate startCoordinate, Coordinate moveForwardDelta) {
        int[] elements = new int[size];
        int currentIndex = 0;

        Coordinate currentCoordinate = startCoordinate;

        while (isWithinBoard(currentCoordinate)) {
            elements[currentIndex] = getTile(currentCoordinate);
            currentIndex++;
            currentCoordinate = getNextCoordinate(currentCoordinate, moveForwardDelta);
        }

        return new Vector(elements);
    }

    private void inlineVector(Vector vector, Coordinate startCoordinate, Coordinate moveForwardDelta) {

        int[] elements = vector.getElements();
        int currentIndex = 0;

        Coordinate currentCoordinate = startCoordinate;

        while (isWithinBoard(currentCoordinate)) {
            set(new Tile(elements[currentIndex], currentCoordinate));
            currentIndex++;
            currentCoordinate = getNextCoordinate(currentCoordinate, moveForwardDelta);
        }
    }

    private void shift(Coordinate startCoordinate, Coordinate moveForwardDelta, Coordinate nextVectorDelta) {
        getTheAmountOfNumbersCollapsingBoards(startCoordinate, moveForwardDelta, nextVectorDelta);

        while (isWithinBoard(startCoordinate)) {
            Vector vector = extractVector(startCoordinate, moveForwardDelta);
            vector.collapse();
            inlineVector(vector, startCoordinate, moveForwardDelta);

            startCoordinate = getNextCoordinate(startCoordinate, nextVectorDelta);
        }
    }

    private int getTheAmountOfNumbersCollapsingBoards(Coordinate startCoordinate, Coordinate moveForwardDelta, Coordinate nextVectorDelta) {
        while (isWithinBoard(startCoordinate)) {
            Vector vector = extractVector(startCoordinate, moveForwardDelta);
            vector.getTheNumberOfTheVectorSumOfCollapsing();
            TheAmountOfNumbersCollapsingBoards = TheAmountOfNumbersCollapsingBoards + vector.getScore();

            startCoordinate = getNextCoordinate(startCoordinate, nextVectorDelta);
        }
        return TheAmountOfNumbersCollapsingBoards;
    }

    private Coordinate getNextCoordinate(Coordinate coordinate, Coordinate delta) {
        int nextRowCoordinate = coordinate.getRow() + delta.getRow();
        int nextColCoordinate = coordinate.getCol() + delta.getCol();

        return new Coordinate(nextRowCoordinate, nextColCoordinate);
    }

    private int getTile(Coordinate coordinate) {
        return board[coordinate.getRow()][coordinate.getCol()];
    }

    private boolean isWithinBoard(Coordinate coordinate) {
        boolean rowIsWithinRange = coordinate.getRow() >= 0 && coordinate.getRow() < size;
        boolean colIsWithinRange = coordinate.getCol() >= 0 && coordinate.getCol() < size;

        return rowIsWithinRange && colIsWithinRange;
    }

    public boolean canSetTile(Tile tile) {
        return getTile(tile.getCoordinate()) == 0;
    }

    public boolean isFull() {
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                if (board[row][col] == 0)
                    return false;

        return true;
    }

    public Board createClone() {
        Board clone = new Board();

        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                clone.board[row][col] = board[row][col];

        return clone;
    }

    @Override
    public boolean equals(Object obj) {
        int[][] thisBoard = board;
        int[][] thoseBoard = ((Board) obj).board;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (thisBoard[row][col] != thoseBoard[row][col])
                    return false;
            }
        }

        return true;
    }

    public boolean canThrowNewTile() {
        return !isFull();
    }

    public Tile[] setCurrentBoard(Tile[] arrtiles1) {
        return arrtiles1;
    }
}