import com.iurii.game2048.Tile;

public interface TileFactoryBuilder {
    PresenterBuilder andRandomTileFactory();

    PresenterBuilder andTileFactoryReturning(Tile[] tiles);

    PresenterBuilder withoutTileFactory();
}
