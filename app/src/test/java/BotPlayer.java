import com.iurii.game2048.Move;
import com.iurii.game2048.Player;
import com.iurii.game2048.View;

import org.junit.Test;

public class BotPlayer implements Player {
    private View view;

    Move[] hits;
    int nextIndex = 0;
    private boolean closeViewOnLastMove;

    public BotPlayer(Move[] hits) {
        this(hits, true);
    }

    public BotPlayer(Move[] hits, boolean closeViewOnLastMove) {
        this.closeViewOnLastMove = closeViewOnLastMove;
        this.hits = hits;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public Move doMove() {
        boolean noMovesLeft = nextIndex >= hits.length;
        if (noMovesLeft)
            throw new BotMoveImpossibleError(String.format("BotPlayer was configured with %d moves, but someone forces him to do extra move", hits.length));

        if (closeViewOnLastMove) {
            boolean moveIsLast = nextIndex == hits.length - 1;
            if (moveIsLast)
                view.startClosing();
        }

        return hits[nextIndex++];
    }
    
}