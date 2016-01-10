import com.iurii.game2048.Board;
import com.iurii.game2048.Coordinate;
import com.iurii.game2048.Tile;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTests {

    @Test
    public void canCreateBoard() throws Exception {
        new Board();
    }


    @Test
    public void brandNewBoardIsClear() throws Exception {

        Board board = new Board();

        int[][] actual = board.getSnapshot();
        int[][] expected = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        assertArrayEquals(expected, actual);
    }

    @Test
    public void canSetTileToBoard() throws Exception {

        //Arrange
        Board board = new Board();

        //Act
        board.set(new Tile(2, new Coordinate(0, 0)));

        //Assert
        int[][] shadow = board.getSnapshot();
        int[][] expected = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        assertArrayEquals(expected, shadow);
    }

    @Test
    public void boardShadowReflectsItsState() {

        //Arrange
        Board board = new Board();
        board.set(new Tile(2, new Coordinate(0, 0)));
        board.set(new Tile(4, new Coordinate(2, 1)));
        board.set(new Tile(8, new Coordinate(3, 3)));

        //Act
        int[][] shadow = board.getSnapshot();

        //Assert
        int[][] expectedState = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
                {0, 0, 0, 8}
        };

        assertArrayEquals(expectedState, shadow);
    }

    @Test
    public void givenTileThatPointsToOccupiedPlaceOnBoard_whenCheckCanSetTile_thenCanNot() throws Exception {
        Tile tile = new Tile(2, new Coordinate(0, 0));
        int[][] boardTemplate = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        Board board = BoardFactory.createBoard(boardTemplate);

        boolean can = board.canSetTile(tile);

        assertTrue(!can);
    }

    @Test
    public void givenBoardWithoutEmptyCells_whenCheckingIfItsFull_thenItIs() throws Exception {
        int[][] boardTemplate = {
                {4, 4, 32, 4},
                {8, 4, 2, 2},
                {2, 2, 2, 2},
                {2, 2, 2, 2}
        };
        Board board = BoardFactory.createBoard(boardTemplate);

        boolean isFull = board.isFull();

        assertTrue(isFull);
    }

    @Test
    public void givenBoardWithEmptyCells_whenCheckingIfItsFull_thenItIsNot() throws Exception {
        int[][] boardTemplate = {
                {4, 4, 32, 4},
                {8, 4, 2, 2},
                {2, 2, 2, 2},
                {0, 2, 2, 2}
        };
        Board board = BoardFactory.createBoard(boardTemplate);

        boolean isFull = board.isFull();

        assertFalse(isFull);
    }

    @Test
    public void givenBoard_whenCloning_thenBoardsAreEqual() throws Exception {
        int[][] boardTemplate = {
                {4, 4, 32, 4},
                {8, 4, 2, 2},
                {2, 2, 2, 2},
                {0, 2, 2, 2}
        };
        Board originalBoard = BoardFactory.createBoard(boardTemplate);

        Board clonedBoard = originalBoard.createClone();

        assertEquals(originalBoard, clonedBoard);
    }

    @Test
    public void givenBoardWithItsClone_whenModifyingCloneBoard_thenOriginalBoardRemainsUnchanged() throws Exception {

        int[][] boardTemplate = {
                {4, 4, 32, 4},
                {8, 4, 2, 2},
                {2, 2, 2, 2},
                {0, 2, 2, 2}
        };
        Board originalBoard = BoardFactory.createBoard(boardTemplate);

        Board clonedBoard = originalBoard.createClone();
        clonedBoard.set(new Tile(2, new Coordinate(0, 0)));

        assertNotSame(originalBoard, clonedBoard);

    }

    @Test
    public void givenTwoIdenticalBoards_whenComparing_thenTheyAreEqual() throws Exception {
        int[][] boardTemplate = {
                {4, 4, 32, 4},
                {8, 4, 2, 2},
                {2, 2, 2, 2},
                {0, 2, 2, 2}
        };

        Board firstBoard = BoardFactory.createBoard(boardTemplate);
        Board secondBoard = BoardFactory.createBoard(boardTemplate);

        assertEquals(firstBoard, secondBoard);
    }

    @Test
    public void givenTwoDifferentBoards_whenComparing_thenTheyAreDifferent() throws Exception {
        int[][] boardTemplate = {
                {4, 4, 32, 4},
                {8, 4, 2, 2},
                {2, 2, 2, 2},
                {0, 2, 2, 2}
        };

        Board firstBoard = BoardFactory.createBoard(boardTemplate);
        Board secondBoard = BoardFactory.createBoard(boardTemplate);
        secondBoard.set(new Tile(1024, new Coordinate(0, 0)));

        assertNotEquals(firstBoard, secondBoard);
    }

    @Test
    public void givenFullBoard_whenCheckingCanThrowNewTile_thenCanNot() throws Exception {
        int[][] boardTemplate = {
                {4, 4, 32, 4},
                {8, 4, 2, 2},
                {2, 2, 2, 2},
                {2, 2, 2, 2}
        };
        Board board = BoardFactory.createBoard(boardTemplate);

        assertFalse(board.canThrowNewTile());
    }

  /*  @Test
    public void given() throws Exception {
        int[][] initial = {
                {8, 16, 32, 64},
                {8, 16, 32, 64},
                {8, 16, 32, 64},
                {8, 16, 32, 64}
        };
        Board board = BoardFactory.createBoard(initial);

        int[] actual = board.convertForAndroidView(initial);

        int[] expected = {8, 16, 32, 64, 8, 16, 32, 64, 8, 16, 32, 64, 8, 16, 32, 64};
        assertArrayEquals(expected, actual);
    }

  /*  @Test
    public void testName() throws Exception {
        int[][] initial = {
                {8, 16, 32, 64},
                {8, 16, 32, 64},
                {8, 16, 32, 64},
                {8, 16, 32, 64}
        };
        Board board = BoardFactory.createBoard(initial);

        int[] actual = board.ExtractVector(new Coordinate(0, 0), new Coordinate(0, 1));

        int[] expected = {8,16,32,64};
        assertArrayEquals(expected, actual);
    }*/
}