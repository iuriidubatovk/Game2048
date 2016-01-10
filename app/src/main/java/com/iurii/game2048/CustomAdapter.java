package com.iurii.game2048;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.iurii.game2048.R.drawable.*;

public class CustomAdapter extends BaseAdapter {

    Tile[] tiles;
    Context context;


    private static LayoutInflater inflater = null;
    private Presenter presenter;

    public CustomAdapter(Context context, Tile[] tiles, Presenter presenter) {
        // TODO Auto-generated constructor stub
        this.tiles = tiles;
        this.context = context;
        this.presenter = presenter;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public class Holder {
        LinearLayout layout;
        TextView textView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.item, null);
        rowView.setLayoutParams(new GridView.LayoutParams(250, 250));
        holder.textView = (TextView) rowView.findViewById(R.id.label);
        holder.layout = (LinearLayout) rowView.findViewById(R.id.linear);

        String text = String.valueOf(tiles[position].getValue());
        holder.textView.setText(text);
        int value = tiles[position].getValue();
        boolean loadAnimation = tiles[position].getIsLoadAnimation();
        changeColorTile(holder, value, loadAnimation);

        return rowView;
    }

    private void changeColorTile(Holder holder, int value, boolean loadAnimation) {
        switch (value) {
            case 0:
                holder.textView.setText("");
                holder.layout.setBackgroundResource(rest2);
                break;
            case 2:
                if (loadAnimation) {
                    Animation changeTransparency = AnimationUtils.loadAnimation(context, R.anim.my_alpha);
                    holder.layout.startAnimation(changeTransparency);
                }
                holder.layout.setBackgroundResource(val_2);
                break;
            case 4:
                if (loadAnimation) {
                    Animation changeTransparency = AnimationUtils.loadAnimation(context, R.anim.my_alpha);
                    holder.layout.startAnimation(changeTransparency);
                }
                holder.layout.setBackgroundResource(val_4);
                break;
            case 8:
                holder.layout.setBackgroundResource(val_8);
                break;
            case 16:
                holder.layout.setBackgroundResource(val_16);
                break;
            case 32:
                holder.layout.setBackgroundResource(val_32);
                break;
            case 64:
                holder.layout.setBackgroundResource(val_64);
                break;
            case 128:
                holder.layout.setBackgroundResource(val_128);
                break;
            case 256:
                holder.layout.setBackgroundResource(val_256);
                break;
            case 512:
                holder.layout.setBackgroundResource(val_512);
                break;
            case 1024:
                holder.layout.setBackgroundResource(val_1024);
                break;
            case 2048:
                holder.layout.setBackgroundResource(val_2048);
                break;
        }
    }

    @Override
    public int getCount() {
        return tiles.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
}