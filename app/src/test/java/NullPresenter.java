import com.iurii.game2048.Move;
import com.iurii.game2048.Presenter;
import com.iurii.game2048.Tile;

public class NullPresenter implements Presenter {
    @Override
    public void start() {

    }

    @Override
    public void processPlayerMove(Move move) {

    }

    @Override
    public int currentScore() {
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public int getRecord() {
        return 0;
    }
}
