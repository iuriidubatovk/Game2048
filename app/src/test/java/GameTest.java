import com.iurii.game2048.Board;
import com.iurii.game2048.Coordinate;
import com.iurii.game2048.Game;
import com.iurii.game2048.Move;
import com.iurii.game2048.RandomTileFactory;
import com.iurii.game2048.Tile;
import com.iurii.game2048.TilesFactory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void canCreateGame() throws Exception {
        new Game(new Board(), new RandomTileFactory());
    }

    @Test
    public void when_game_starts_board_gets_2_or_4() throws Exception {
        //arrange
        Board board = new Board();
        Tile[] tiles = {new Tile(2, new Coordinate(0, 0)), new Tile(2, new Coordinate(0, 1))};
        TilesFactory tilesFactory = new CertainTilesFactory(tiles);
        Game game = new Game(board, tilesFactory);
        //act
        int[][] originalBoardShadow = board.getSnapshot();
        game.startNewGame();
        int[][] updatedBoardShadow = board.getSnapshot();
        //assert
        List<Integer> changedTiles = findChangedTiles(originalBoardShadow, updatedBoardShadow);

        boolean onlyTwoTileChanged = changedTiles.size() == 2;
        assertTrue(onlyTwoTileChanged);

        Integer tile1 = changedTiles.get(0);
        assertTrue(tile1 == 2 || tile1 == 4);

        Integer tile2 = changedTiles.get(1);
        assertTrue(tile2 == 2 || tile2 == 4);
    }

    public List<Integer> findChangedTiles(int[][] original, int[][] updated) {
        List<Integer> changedTiles = new ArrayList<>();

        for (int row = 0; row < original.length; row++) {
            for (int col = 0; col < original.length; col++) {
                if (original[row][col] != updated[row][col])
                    changedTiles.add(updated[row][col]);
            }
        }

        return changedTiles;
    }

    @Test
    public void UserCanSwipeBoardDownSoThatAlongsideTilesAreSummed() throws Exception {

        //arrange

        int[][] initial = {
                {2, 0, 0, 2},
                {2, 4, 0, 2},
                {2, 2, 4, 0},
                {2, 2, 2, 8}
        };

        Board board = BoardFactory.createBoard(initial);

        TilesFactory tilesFactory = new CertainTilesFactory(new Tile[]{new Tile(2, new Coordinate(0, 0))});
        Game game = new Game(board, tilesFactory);

        //act
        game.processPlayerMove(Move.down);
        int[][] actual = board.getSnapshot();

        //assert
        int[][] expected = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 4, 4, 4},
                {4, 4, 2, 8}
        };
        assertArrayEquals(expected, actual);

    }

    @Test
    public void UserCanSwipeBoardLeftSoThatAlongsideTilesAreSummed() throws Exception {

        //arrange

        int[][] initial = {
                {0, 2, 0, 2},
                {2, 2, 4, 0},
                {2, 4, 16, 16},
                {8, 0, 2, 2}
        };

        Board board = BoardFactory.createBoard(initial);
        TilesFactory tilesFactory = new CertainTilesFactory(new Tile[]{new Tile(2, new Coordinate(1, 3))});

        Game game = new Game(board, tilesFactory);

        game.processPlayerMove(Move.left);

        int[][] actual = board.getSnapshot();
        int[][] expected = {
                {4, 0, 0, 0},
                {4, 4, 0, 2},
                {2, 4, 32, 0},
                {8, 4, 0, 0}
        };
        assertArrayEquals(expected, actual);

    }

    @Test
    public void UserCanSwipeBoardRightSoThatAlongsideTilesAreSummed() throws Exception {

        //arrange

        int[][] initial = {
                {0, 2, 0, 2},
                {2, 2, 4, 0},
                {2, 4, 16, 16},
                {8, 0, 2, 2}
        };

        Board board = BoardFactory.createBoard(initial);
        TilesFactory tilesFactory = new CertainTilesFactory(new Tile[]{new Tile(2, new Coordinate(0, 0))});
        Game game = new Game(board, tilesFactory);

        //act
        game.processPlayerMove(Move.right);
        int[][] actual = board.getSnapshot();

        //assert
        int[][] expected = {
                {2, 0, 0, 4},
                {0, 0, 4, 4},
                {0, 2, 4, 32},
                {0, 0, 8, 4}
        };
        assertArrayEquals(expected, actual);

    }

    @Test
    public void UserCanSwipeBoardUpSoThatAlongsideTilesAreSummed() throws Exception {

        //arrange

        int[][] initial = {
                {0, 2, 0, 2},
                {2, 2, 16, 0},
                {2, 4, 16, 0},
                {8, 0, 2, 2}
        };

        Board board = BoardFactory.createBoard(initial);
        TilesFactory tilesFactory = new CertainTilesFactory(new Tile[]{new Tile(2, new Coordinate(3, 0))});
        Game game = new Game(board, tilesFactory);

        //act
        game.processPlayerMove(Move.up);
        int[][] actual = board.getSnapshot();

        //assert
        int[][] expected = {
                {4, 4, 32, 4},
                {8, 4, 2, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0}
        };

        assertArrayEquals(expected, actual);
    }

    @Test //setOneTile
    public void givenBoard_whenThrowingNewTile_thenBoardContainsIt() throws Exception {
        //arrange
        Board board = new Board();
        TilesFactory tilesFactory = new RandomTileFactory();
        Game game = new Game(board, tilesFactory);
        //act
        game.throwNewTile();
        //assert
        int[][] boardShadow = board.getSnapshot();
        int foundTilesOne = 0;

        for (int row = 0; row < boardShadow.length; row++) {
            for (int col = 0; col < boardShadow.length; col++) {
                if (boardShadow[row][col] > 0)
                    foundTilesOne++;
            }
        }

        assertEquals(1, foundTilesOne);
    }

    @Test
    public void givenBoardWithOneEmptyCell_whenCheckingIfGameIsOver_thenItIsNot() throws Exception {
        int[][] boardSnapshot = {
                {4, 4, 32, 4},
                {8, 4, 2, 2},
                {2, 2, 2, 2},
                {0, 2, 2, 2}
        };

        Board board = BoardFactory.createBoard(boardSnapshot);
        Game game = new Game(board, null);
        boolean gameIsOver = game.isOver();
        assertFalse(gameIsOver);
    }

    @Test
    public void givenFullBoard_whenCheckIsGameOver_thenItIs() throws Exception {
        int[][] boardSnapshot = {
                {1024, 2048, 1024, 16},
                {8, 128, 16, 32},
                {32, 8, 128, 8},
                {2048, 256, 16, 256}
        };
        Board board = BoardFactory.createBoard(boardSnapshot);
        Game game = new Game(board, null);

        boolean gameIsOver = game.isOver();

        assertTrue(gameIsOver);
    }

    @Test
    public void givenFullBoardWithTwoVerticallyNeighboringIdenticalTiles_whenCheckIsGameOver_thenItIsNot() throws Exception {
        int[][] boardSnapshot = {
                {1024, 2048, 1024, 16},
                {1024, 128, 16, 32},
                {32, 8, 128, 8},
                {2048, 256, 16, 256}
        };
        Board board = BoardFactory.createBoard(boardSnapshot);
        Game game = new Game(board, null);
        boolean gameIsOver = game.isOver();

        assertFalse(gameIsOver);
    }

    @Test
    public void givenFullBoardWithTwoHorizontallyNeighboringIdenticalTiles_whenCheckIsGameOver_thenItIsNot() throws Exception {
        int[][] boardSnapshot = {
                {1024, 1024, 1024, 16},
                {8, 128, 16, 32},
                {32, 8, 128, 8},
                {2048, 256, 16, 256}
        };
        Board board = BoardFactory.createBoard(boardSnapshot);
        Game game = new Game(board, null);
        boolean gameIsOver = game.isOver();

        assertFalse(gameIsOver);
    }

    @Test
    public void givenGame_whenAMoveToTheLeftDoNotExistTiles_thenNewTileDoesNotAppear() throws Exception {
        int[][] boardSnapshot = {
                {2, 512, 0, 0},
                {1024, 2, 16, 0},
                {32, 8, 0, 0},
                {2048, 0, 0, 0}
        };
        Board board = BoardFactory.createBoard(boardSnapshot);
        TilesFactory tilesFactory = new RandomTileFactory();
        Game game = new Game(board, tilesFactory);

        game.processPlayerMove(Move.left);

        int[][] actual = board.getSnapshot();
        int[][] expected = boardSnapshot;
        assertArrayEquals(expected, actual);
    }

    @Test
    public void givenGame_whenPlayerPerformsAuthenticMoveLeft_thenBoardIsShiftedLeftAndNewTileAppeared() throws Exception {
        int[][] initialBoard = {
                {0, 0, 0, 8},
                {0, 0, 0, 8},
                {0, 0, 0, 8},
                {0, 0, 0, 8}
        };
        int[][] expected = {
                {8, 0, 0, 0},
                {8, 2, 0, 0},
                {8, 0, 0, 0},
                {8, 0, 0, 0}
        };

        assertBoardIsExpectedAfterMove(Move.left, initialBoard, expected, new Tile(2, new Coordinate(1, 1)));
    }

    @Test
    public void givenGame_whenPlayerPerformsAuthenticMoveRight_thenBoardIsShiftedRightAndNewTileAppeared() throws Exception {
        int[][] initialBoard = {
                {8, 0, 0, 0},
                {8, 0, 0, 0},
                {8, 0, 0, 0},
                {8, 0, 0, 0}
        };
        int[][] expected = {
                {0, 0, 0, 8},
                {0, 2, 0, 8},
                {0, 0, 0, 8},
                {0, 0, 0, 8}
        };

        assertBoardIsExpectedAfterMove(Move.right, initialBoard, expected, new Tile(2, new Coordinate(1, 1)));
    }

    @Test
    public void givenGame_whenPlayerPerformsAuthenticMoveUp_thenBoardIsShiftedUpAndNewTileAppeared() throws Exception {
        int[][] initialBoard = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {8, 8, 8, 8}
        };
        int[][] expected = {
                {8, 8, 8, 8},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        assertBoardIsExpectedAfterMove(Move.up, initialBoard, expected, new Tile(2, new Coordinate(1, 1)));
    }

    @Test
    public void givenGame_whenPlayerPerformsAuthenticMoveDown_thenBoardIsShiftedDownAndNewTileAppeared() throws Exception {
        int[][] initialBoard = {
                {8, 8, 8, 8},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        int[][] expected = {
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {8, 8, 8, 8}
        };

        assertBoardIsExpectedAfterMove(Move.down, initialBoard, expected, new Tile(2, new Coordinate(1, 1)));
    }

    @Test
    public void givenGame_whenPlayerPerformsDummyMoveLeft_thenBoardIsUnchangedAndNewTileNotAppeared() throws Exception {
        int[][] initialBoard = {
                {8, 0, 0, 0},
                {8, 0, 0, 0},
                {8, 0, 0, 0},
                {8, 0, 0, 0}
        };
        int[][] expected = {
                {8, 0, 0, 0},
                {8, 0, 0, 0},
                {8, 0, 0, 0},
                {8, 0, 0, 0}
        };

        assertBoardIsExpectedAfterMove(Move.left, initialBoard, expected, new Tile(2, new Coordinate(1, 1)));
    }


    @Test
    public void givenGame_whenPlayerPerformsDummyMoveRight_thenBoardIsUnchangedAndNewTileNotAppeared() throws Exception {
        int[][] initialBoard = {
                {0, 0, 0, 8},
                {0, 0, 0, 8},
                {0, 0, 0, 8},
                {0, 0, 0, 8}
        };
        int[][] expected = {
                {0, 0, 0, 8},
                {0, 0, 0, 8},
                {0, 0, 0, 8},
                {0, 0, 0, 8}
        };

        assertBoardIsExpectedAfterMove(Move.right, initialBoard, expected, new Tile(2, new Coordinate(1, 1)));
    }

    @Test
    public void givenGame_whenPlayerPerformsDummyMoveDown_thenBoardIsUnchangedAndNewTileNotAppeared() throws Exception {
        int[][] initialBoard = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {8, 8, 8, 8}
        };
        int[][] expected = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {8, 8, 8, 8}
        };

        assertBoardIsExpectedAfterMove(Move.down, initialBoard, expected, new Tile(2, new Coordinate(1, 1)));
    }

    @Test
    public void givenGame_whenPlayerPerformsDummyMoveUp_thenBoardIsUnchangedAndNewTileNotAppeared() throws Exception {
        int[][] initialBoard = {
                {8, 8, 8, 8},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        int[][] expected = {
                {8, 8, 8, 8},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        assertBoardIsExpectedAfterMove(Move.up, initialBoard, expected, new Tile(2, new Coordinate(1, 1)));
    }


    private void assertBoardIsExpectedAfterMove(Move move, int[][] initialBoard, int[][] expected, Tile futureTile) {


        Board board = BoardFactory.createBoard(initialBoard);
        Tile[] tiles = {futureTile};
        TilesFactory tilesFactory = new CertainTilesFactory(tiles);
        Game game = new Game(board, tilesFactory);

        switch (move) {
            case left:
                game.processPlayerMove(Move.left);
                break;
            case right:
                game.processPlayerMove(Move.right);
                break;
            case down:
                game.processPlayerMove(Move.down);
                break;
            case up:
                game.processPlayerMove(Move.up);
                break;
        }

        int[][] actual = board.getSnapshot();

        assertArrayEquals(expected, actual);
    }
}


