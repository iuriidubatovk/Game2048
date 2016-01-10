import com.iurii.game2048.Tile;
import com.iurii.game2048.TilesFactory;

public class CertainTilesFactory implements TilesFactory {
    Tile[] tiles;
    int nextIndex = 0;

    public CertainTilesFactory(Tile[] tiles) {
        this.tiles = tiles;
    }

    @Override
    public Tile create() {
        return tiles[nextIndex++];
    }
}
