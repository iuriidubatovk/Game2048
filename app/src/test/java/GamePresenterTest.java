import com.iurii.game2048.Board;
import com.iurii.game2048.ConsoleView;
import com.iurii.game2048.Coordinate;
import com.iurii.game2048.Game;
import com.iurii.game2048.GamePresenter;
import com.iurii.game2048.Move;
import com.iurii.game2048.Tile;
import com.iurii.game2048.TilesFactory;
import com.iurii.game2048.View;

import org.junit.Test;

import static org.junit.Assert.*;

public class GamePresenterTest {


    @Test
    public void testName() throws Exception {
        //// TODO: 07.09.2015 здесь я испытываю DCL

        Tile[] tiles = {new Tile(0, new Coordinate(1, 1)), new Tile(0, new Coordinate(1, 1)), new Tile(2, new Coordinate(0, 1))};
        BoardBuilder boardBuilder = new Environment();
        boardBuilder.withEmptyBoard().andTileFactoryReturning(tiles).withoutPresenter().getGame();


    }

    @Test
    public void givenNewGame_whenStarting_thenTwoTilesAppeared() throws Exception {
        //arrange
        Board board = new Board();
        Tile[] tiles = {new Tile(2, new Coordinate(0, 0)), new Tile(2, new Coordinate(0, 1))};
        TilesFactory tilesFactory = new CertainTilesFactory(tiles);
        Game game = new Game(board, tilesFactory);
        GamePresenter presenter = new GamePresenter(game, board);
        presenter.setView(new NullView());
        //act
        presenter.start();
        //assert
        int newTilesAmount = 0;

        int[][] shadow = board.getSnapshot();
        for (int row = 0; row < shadow.length; row++) {
            for (int col = 0; col < shadow.length; col++) {
                if (shadow[row][col] > 0)
                    newTilesAmount++;
            }
        }

        assertEquals(2, newTilesAmount);
    }

    @Test
    public void givenNewGame_whenUserSwipesFirstTimeLeft_thenTilesStickToLeftSideOfTheBoard() throws Exception {
        Board board = new Board();
        Tile[] tiles = {new Tile(2, new Coordinate(1, 1)), new Tile(4, new Coordinate(2, 2)), new Tile(2, new Coordinate(2, 2))};
        TilesFactory tilesFactory = new CertainTilesFactory(tiles);
        Game game = new Game(board, tilesFactory);
        GamePresenter presenter = new GamePresenter(game, board);
        presenter.setView(new NullView());
        //act
        presenter.start();
        presenter.processPlayerMove(Move.left);
        //assert
        int[][] shadow = board.getSnapshot();
        int foundTilesAmount = 0;
        for (int row = 0; row < shadow.length; row++) {
            if (shadow[row][0] > 0)
                foundTilesAmount++;
        }

        boolean bothTilesStuckToLeft = foundTilesAmount == 2;
        assertTrue(bothTilesStuckToLeft);

    }

    @Test
    public void givenNewGame_whenUserSwipesFirstTimeRight_thenTilesStickToLeftSideOfTheBoard() throws Exception {
        Board board = new Board();
        Tile[] tiles = {new Tile(2, new Coordinate(1, 1)), new Tile(4, new Coordinate(2, 2)), new Tile(2, new Coordinate(2, 2))};
        TilesFactory tilesFactory = new CertainTilesFactory(tiles);
        Game game = new Game(board, tilesFactory);
        GamePresenter presenter = new GamePresenter(game, board);
        presenter.setView(new NullView());
        //act
        presenter.start();
        presenter.processPlayerMove(Move.right);
        //assert
        int[][] shadow = board.getSnapshot();
        int foundTilesAmount = 0;
        for (int row = 0; row < shadow.length; row++) {
            if (shadow[row][3] > 0)
                foundTilesAmount++;
        }

        boolean bothTilesStuckToLeft = foundTilesAmount == 2;
        assertTrue(bothTilesStuckToLeft);

    }

    @Test
    public void givenNewGame_whenUserSwipesFirstTimeDown_thenTilesStickToLeftSideOfTheBoard() throws Exception {
        Board board = new Board();
        Tile[] tiles = {new Tile(2, new Coordinate(1, 1)), new Tile(4, new Coordinate(2, 2)), new Tile(2, new Coordinate(2, 2))};
        TilesFactory tilesFactory = new CertainTilesFactory(tiles);
        Game game = new Game(board, tilesFactory);
        GamePresenter presenter = new GamePresenter(game, board);
        presenter.setView(new NullView());
        //act
        presenter.start();
        presenter.processPlayerMove(Move.down);
        //assert
        int[][] shadow = board.getSnapshot();
        int foundTilesAmount = 0;
        for (int col = 0; col < shadow.length; col++) {
            if (shadow[3][col] > 0)
                foundTilesAmount++;
        }

        boolean bothTilesStuckToLeft = foundTilesAmount == 2;
        assertTrue(bothTilesStuckToLeft);

    }

    @Test
    public void givenNewGame_whenUserSwipesFirstTimeUp_thenTilesStickToLeftSideOfTheBoard() throws Exception {
        Board board = new Board();
        Tile[] tiles = {new Tile(2, new Coordinate(1, 1)), new Tile(4, new Coordinate(2, 2)), new Tile(2, new Coordinate(2, 2))};
        TilesFactory tilesFactory = new CertainTilesFactory(tiles);
        Game game = new Game(board, tilesFactory);
        GamePresenter presenter = new GamePresenter(game, board);
        presenter.setView(new NullView());
        //act
        presenter.start();
        presenter.processPlayerMove(Move.up);
        //assert
        int[][] shadow = board.getSnapshot();
        int foundTilesAmount = 0;
        for (int col = 0; col < shadow.length; col++) {
            if (shadow[0][col] > 0)
                foundTilesAmount++;
        }

        boolean bothTilesStuckToLeft = foundTilesAmount == 2;
        assertTrue(bothTilesStuckToLeft);

    }

    @Test
    public void givenNewGame_whenUserSwipesFirstTimeLeft_thenNewTileAppeared() throws Exception {
        Board board = new Board();
        Tile[] tiles = {new Tile(2, new Coordinate(1, 1)), new Tile(2, new Coordinate(2, 2)), new Tile(4, new Coordinate(0, 3))};
        TilesFactory tilesFactory = new CertainTilesFactory(tiles);
        Game game = new Game(board, tilesFactory);
        GamePresenter presenter = new GamePresenter(game, board);
        presenter.setView(new NullView());
        //act
        presenter.start();
        presenter.processPlayerMove(Move.left);
        //assert
        int[][] shadow = board.getSnapshot();

        int foundTilesAmount = 0;
        for (int row = 0; row < shadow.length; row++) {
            for (int col = 0; col < shadow.length; col++) {
                if (shadow[row][col] > 0)
                    foundTilesAmount++;
            }
        }

        assertEquals(3, foundTilesAmount);
    }

    @Test
    public void givenGame_whenPlayerDoesLastSwipeLeft_thenGameIsOver() throws Exception {
        int[][] boardSnapshot = {
                {512, 1024, 0, 256},
                {8, 128, 16, 32},
                {32, 8, 128, 8},
                {2048, 256, 16, 256}
        };
        Board board = BoardFactory.createBoard(boardSnapshot);
        Tile[] tiles = {new Tile(0, new Coordinate(0, 2)), new Tile(0, new Coordinate(0, 2)), new Tile(2, new Coordinate(0, 3))};
        TilesFactory tilesFactory = new CertainTilesFactory(tiles);
        Game game = new Game(board, tilesFactory);
        Move[] hits = {Move.left};
        BotPlayer player = new BotPlayer(hits, false);
        View view = new ConsoleView(player);
        GamePresenter presenter = new GamePresenter(game, board);
        view.setPresenter(presenter);
        presenter.setView(view);
        player.setView(view);

        view.showGame();

        assertTrue(game.isOver());
    }

    @Test
    public void givenGame_whenPlayerDoesLastSwipeRight_thenGameIsOver() throws Exception {
        int[][] boardSnapshot = {
                {512, 0, 1024, 256},
                {8, 128, 16, 32},
                {32, 8, 128, 8},
                {2048, 256, 16, 256}
        };
        Board board = BoardFactory.createBoard(boardSnapshot);
        Tile[] tiles = {new Tile(0, new Coordinate(0, 1)), new Tile(0, new Coordinate(0, 1)), new Tile(2, new Coordinate(0, 0))};
        TilesFactory tilesFactory = new CertainTilesFactory(tiles);
        Game game = new Game(board, tilesFactory);
        Move[] hits = {Move.right};
        BotPlayer player = new BotPlayer(hits, false);
        View view = new ConsoleView(player);
        GamePresenter presenter = new GamePresenter(game, board);
        view.setPresenter(presenter);
        presenter.setView(view);
        player.setView(view);

        view.showGame();

        assertTrue(game.isOver());
    }

    @Test
    public void givenGame_whenPlayerDoesLastSwipeUp_thenGameIsOver() throws Exception {
        int[][] boardSnapshot = {
                {512, 0, 1024, 256},
                {8, 128, 16, 32},
                {32, 1024, 128, 8},
                {2048, 256, 16, 256}
        };
        Board board = BoardFactory.createBoard(boardSnapshot);
        Tile[] tiles = {new Tile(0, new Coordinate(0, 1)), new Tile(0, new Coordinate(0, 1)), new Tile(2, new Coordinate(3, 1))};
        TilesFactory tilesFactory = new CertainTilesFactory(tiles);
        Game game = new Game(board, tilesFactory);
        Move[] hits = {Move.up};
        BotPlayer player = new BotPlayer(hits, false);
        View view = new ConsoleView(player);
        GamePresenter presenter = new GamePresenter(game, board);
        view.setPresenter(presenter);
        presenter.setView(view);
        player.setView(view);

        view.showGame();

        assertTrue(game.isOver());
    }

    @Test
    public void givenGame_whenPlayerDoesLastSwipeDown_thenGameIsOver() throws Exception {
        int[][] boardSnapshot = {
                {512, 128, 1024, 256},
                {8, 0, 16, 32},
                {32, 8, 128, 8},
                {2048, 256, 16, 256}
        };
        Board board = BoardFactory.createBoard(boardSnapshot);
        Tile[] tiles = {new Tile(0, new Coordinate(1, 1)), new Tile(0, new Coordinate(1, 1)), new Tile(2, new Coordinate(0, 1))};
        TilesFactory tilesFactory = new CertainTilesFactory(tiles);
        Game game = new Game(board, tilesFactory);
        Move[] hits = {Move.down};
        BotPlayer player = new BotPlayer(hits, false);
        View view = new ConsoleView(player);
        GamePresenter presenter = new GamePresenter(game, board);
        view.setPresenter(presenter);
        presenter.setView(view);
        player.setView(view);

        view.showGame();

        assertTrue(game.isOver());
    }
}

