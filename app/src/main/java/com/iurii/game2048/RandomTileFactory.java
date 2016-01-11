package com.iurii.game2048;


import java.util.Random;

public class RandomTileFactory implements TilesFactory {
    Random random = new Random();

    @Override
    public Tile create() {
        int tileValue = createValue();
        Coordinate coordinate = createCoordinate();

        return new Tile(tileValue, coordinate, true);
    }

    private int createValue() {
        int tileValue;
        int tileIs2or4 = random.nextInt(2);

        if (tileIs2or4 == 0)
            tileValue = 2;
        else
            tileValue = 4;
        return tileValue;
    }

    private Coordinate createCoordinate() {
        int row = random.nextInt(4);
        int col = random.nextInt(4);
        return new Coordinate(row, col);
    }
}
