package com.iurii.game2048;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;


import static com.iurii.game2048.R.drawable.foto_game_2048;


public class GameFragment extends Fragment implements com.iurii.game2048.View {
    private Presenter presenter;
    private Vibrator vibrator;

    private NonClickableGridView gv1;
    private TextView score, record;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Board board = new Board();
        TilesFactory tilesFactory = new RandomTileFactory();
        Game game = new Game(board, tilesFactory);
        Presenter presenter = new GamePresenter(game, board);
        DataBase dataBase = new DataBase(presenter, getActivity());

        setPresenter(presenter);
        game.setDataBase(dataBase);
        ((GamePresenter) presenter).setView(this);

        initializeVibrator();

        setUpActionBar();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, null);

        view.findViewById(R.id.root_layout).setOnTouchListener(
                new OnSwipeTouchListener(getActivity()) {
                    @Override
                    public void onSwipeRight() {
                        presenter.processPlayerMove(Move.right);
                    }

                    @Override
                    public void onSwipeLeft() {
                        presenter.processPlayerMove(Move.left);
                    }

                    @Override
                    public void onSwipeTop() {
                        presenter.processPlayerMove(Move.up);
                    }

                    @Override
                    public void onSwipeBottom() {
                        presenter.processPlayerMove(Move.down);
                    }
                }
        );

        score = (TextView) view.findViewById(R.id.score);
        record = (TextView) view.findViewById(R.id.record);
        gv1 = (NonClickableGridView) view.findViewById(R.id.gridView);

        showGame();

        return view;
    }

    @Override
    public void showGame() {
        presenter.start();
    }

    @Override
    public void startClosing() {
        Toast.makeText(getActivity(), " startClosing", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayBoard(Tile[] tiles) {
        tuneGridView(tiles);
    }

    @Override
    public void displayRecord() {
        record.setText(String.valueOf(presenter.getRecord()));
    }

    @Override
    public void displayCount() {
        score.setText(String.valueOf(presenter.getCount()));
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getVibrator() {
        vibrator.vibrate(20);
    }

    private void tuneGridView(Tile[] tiles) {
        gv1.setVerticalSpacing(25);
        gv1.setHorizontalSpacing(25);
        gv1.setPadding(10, 25, 10, 0);
        gv1.setAdapter(new TileAdapter(getActivity(), tiles));
    }

    private void setUpActionBar() {
        ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        actionBar.setLogo(foto_game_2048);
        actionBar.setTitle("2048");
        setHasOptionsMenu(true);
    }

    private void initializeVibrator() {
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    }
}
