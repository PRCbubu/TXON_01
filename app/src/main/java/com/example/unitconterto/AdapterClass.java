package com.example.unitconterto;

import android.content.Context;
import android.view.*;
import android.widget.*;
import android.annotation.*;
import android.util.*;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AdapterClass extends ArrayAdapter<MenuItems>
{
    public AdapterClass(@NonNull Context context, ArrayList<MenuItems> menuItemsArrayList)
    {
        super(context, 0, menuItemsArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent)
    {
        View itemListView = convertView;

        if(itemListView==null)
            itemListView = LayoutInflater.from(getContext()).inflate(R.layout.card_layout, parent, false);

        MenuItems courseModel = getItem(position);
        TextView itemTV = itemListView.findViewById(R.id.icon_text);
        ImageView itemIV = itemListView.findViewById(R.id.icons);

        itemTV.setText(courseModel.getOptionName());
        itemIV.setImageResource(courseModel.getImgId());
        return itemListView;
    }
}
