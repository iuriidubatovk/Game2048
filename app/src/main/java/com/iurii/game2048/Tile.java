package com.iurii.game2048;

public class Tile {

    private int value;
    private Coordinate coordinate;
    private boolean isLoadAnimation;

    public Tile(int value, Coordinate coordinate, boolean isLoadAnimation) {
        this.value = value;
        this.coordinate = coordinate;
        this.isLoadAnimation = isLoadAnimation;
    }

    public Tile(int value, Coordinate coordinate) {
      this(value,coordinate,false);
    }

    public Coordinate getCoordinate() {

        return coordinate;
    }

    public int getValue() {

        return value;
    }

    public boolean getIsLoadAnimation() {
        return isLoadAnimation;
    }

    @Override
    public String toString() {
        return
                +value + ","
                        + coordinate +
                        '}';
    }

}




