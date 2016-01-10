import com.iurii.game2048.Coordinate;
import com.iurii.game2048.RandomTileFactory;
import com.iurii.game2048.TilesFactory;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomTilesFactoryTest {
    @Test
    public void givenFactory_whenGenerateTile_thenIs2or4() throws Exception {
        TilesFactory tilesFactory = new RandomTileFactory();

        int tile = tilesFactory.create().getValue();

        boolean tileIs2or4 = tile == 2 || tile == 4;
        assertTrue(tileIs2or4);
    }

    @Test
    public void givenRandomFactory_whenCreateRandomCoordinate_thenIsWithinBoard() throws Exception {
        //arrange
        TilesFactory tilesFactory = new RandomTileFactory();

        //act
        Coordinate coordinate = tilesFactory.create().getCoordinate();
        // assert
        boolean coordinateIsWithinBoard = coordinate.getRow() >= 0 && coordinate.getRow() <= 3 && coordinate.getCol() >= 0 && coordinate.getCol() <= 3;
        assertTrue(coordinateIsWithinBoard);
    }
}
