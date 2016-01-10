package com.iurii.game2048;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class FragmentSetting extends Fragment {
    MainActivity mainActivity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_setting, null);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.check_box_turn_vibration);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        //todo если выбрать меню настроек а потом опять его выбрарь то оно снова выбираеться
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
             //   isVibrationIncluded = !buttonView.isChecked();
            }
        });

        return view;
    }
}
