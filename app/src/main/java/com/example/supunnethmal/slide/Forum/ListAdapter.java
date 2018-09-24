package com.example.supunnethmal.slide.Forum;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.supunnethmal.slide.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by supun nethmal on 25-Jun-18.
 */

public class ListAdapter extends ArrayAdapter<dataModel> {

    private Context mContext;
    private List<dataModel> dataList = new ArrayList<>();

    public ListAdapter(@NonNull Context context, ArrayList<dataModel> list) {
        super(context, 0 , list);
        mContext = context;
        dataList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.listitem,parent,false);

        dataModel currentMovie = dataList.get(position);

        TextView name = listItem.findViewById(R.id.textView2);
        name.setText(currentMovie.getPostType());

        TextView release = listItem.findViewById(R.id.textView1);
        release.setText(currentMovie.getPostTittle());

        return listItem;
    }
}
