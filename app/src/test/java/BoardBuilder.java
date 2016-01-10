public interface BoardBuilder {
    TileFactoryBuilder withEmptyBoard();

    TileFactoryBuilder withBoard(int[][] snapshot);
}
