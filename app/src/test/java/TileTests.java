import com.iurii.game2048.Coordinate;
import com.iurii.game2048.Tile;

import org.junit.Test;

import static org.junit.Assert.*;

public class TileTests {
    @Test
    public void givenType_whenInstantiating_thenNoProblems() throws Exception {
        new Tile(2, new Coordinate(0, 0));
    }

    @Test
    public void givenType_whenInstantiating_thenCoordinateIsInitializedProperly() throws Exception {
        Coordinate coordinate = new Coordinate(0, 0);
        Tile tile = new Tile(1, coordinate);

        Coordinate actual = tile.getCoordinate();

        assertEquals(coordinate, actual);
    }

    @Test
    public void givenType_whenInstantiating_thenValueIsInitializedProperly() throws Exception {

        Tile tile = new Tile(1024, null);

        int actual = tile.getValue();

        assertEquals(1024, actual);
    }
}
