import com.iurii.game2048.Board;
import com.iurii.game2048.Coordinate;
import com.iurii.game2048.Tile;

public class BoardFactory {
    public static Board createBoard(int[][] boardSnapshot) {
        Board board = new Board();
        for (int row = 0; row < boardSnapshot.length; row++) {
            for (int col = 0; col < boardSnapshot.length; col++) {
                Tile tile = new Tile(boardSnapshot[row][col], new Coordinate(row, col));
                board.set(tile);
            }
        }

        return board;
    }
}
