package com.iurii.game2048;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentReload extends Fragment implements View.OnClickListener {
    MainActivity mainActivity;

    public static boolean isReloadGame() {
        return isNewGame;
    }

    private static boolean isNewGame ;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //todo замеить test нормальное название
        View view = inflater.inflate(R.layout.test, null);
        Button cancel = (Button) view.findViewById(R.id.cancel);
        Button ok = (Button) view.findViewById(R.id.ok);
        cancel.setOnClickListener(this);
        ok.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                mainActivity.onBackPressed();
                break;
            case R.id.ok:
                mainActivity.onBackPressed();
                mainActivity.setFragment(new UsersFragment());
                isNewGame = true;
                break;
        }
    }
}


