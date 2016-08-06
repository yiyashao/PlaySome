package com.techosoft.idea.playsome.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.techosoft.idea.playsome.R;
import com.techosoft.idea.playsome.models.WantItem;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by davidsss on 16-08-06.
 */
public class AdapterWantList extends BaseAdapter {
    //necessary variables
    private Context myContext;
    private LayoutInflater myInflater;
    private ArrayList<WantItem> dataSource;

    //init(), just following the rules
    public AdapterWantList(Context context, ArrayList<WantItem> items) {
        myContext = context;
        dataSource = items;
        myInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // Get view for row item
        View rowView = myInflater.inflate(R.layout.cell_item_want, parent, false);
        // Get title element
        TextView titleTextView = (TextView) rowView.findViewById(R.id.tvItemTitle);
        TextView detailTextView = (TextView) rowView.findViewById(R.id.tvItemDetail);
        TextView expDateTextView = (TextView) rowView.findViewById(R.id.tvItemExpDate);
        // for each item, get the data into each cell
        WantItem item = (WantItem) getItem(position);
        titleTextView.setText(item.title);
        detailTextView.setText(item.detail);
        String expDate = DateFormat.getDateInstance().format(item.expireDate);
        expDateTextView.setText(expDate);
        //following used to add photo into cell, keep for future use
        //Picasso.with(mContext).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);

        return rowView;
    }
}
