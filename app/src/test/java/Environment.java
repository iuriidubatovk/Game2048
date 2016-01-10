import com.iurii.game2048.Board;
import com.iurii.game2048.Game;
import com.iurii.game2048.GamePresenter;
import com.iurii.game2048.Presenter;
import com.iurii.game2048.RandomTileFactory;
import com.iurii.game2048.Tile;
import com.iurii.game2048.TilesFactory;

public class Environment implements BoardBuilder, TileFactoryBuilder, PresenterBuilder, GameBuilder, Result {

    private Board board;
    private TilesFactory tileFactory;
    private Presenter presenter;
    private Game game;

    @Override
    public TileFactoryBuilder withEmptyBoard() {
        board = new Board();
        return this;
    }

    @Override
    public TileFactoryBuilder withBoard(int[][] snapshot) {
        board = BoardFactory.createBoard(snapshot);
        return this;
    }

    @Override
    public PresenterBuilder andRandomTileFactory() {
        tileFactory = new RandomTileFactory();
        createGame();

        return this;
    }

    @Override
    public PresenterBuilder andTileFactoryReturning(Tile[] tiles) {
        tileFactory = new CertainTilesFactory(tiles);
        createGame();

        return this;
    }

    @Override
    public PresenterBuilder withoutTileFactory() {
        createGame();

        return this;
    }

    @Override
    public Result withoutPresenter() {
        presenter = new NullPresenter();
        return this;
    }

    @Override
    public Result  withPresenter() {
        presenter = new GamePresenter(game, board);
        return this;
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    private void createGame() {
        game = new Game(board, tileFactory);
    }
}
