package com.techosoft.idea.playsome.utilities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techosoft.idea.playsome.R;

import org.w3c.dom.Text;

/**
 * Created by david on 2016/8/8.
 * coppied from website, https://developer.android.com/training/material/lists-cards.html
 * this is for learning purpose
 */
public class AdapterSwipeList extends RecyclerView.Adapter<AdapterSwipeList.ViewHolder>{

    String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    //so this holds the space as a VIEW for the data item. (my word)
    public static class ViewHolder extends RecyclerView.ViewHolder { //inner class, define the view holder.
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v; //a text view
        }
    }

    //constructor, depends on the data used, for my case, should use GiveLink as data type, should also have WantItem linked together
    public AdapterSwipeList(String[] myDataset){
        mDataset = myDataset;
    }


    @Override
    public AdapterSwipeList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //create a new VIEW
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_give_link, parent, false);
        //set the view's size, margins, paddings and layout parameters, this is a TODO
        ViewHolder vh = new ViewHolder((TextView) v);
        return vh;
    }

    //replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AdapterSwipeList.ViewHolder holder, int position) {
        // get element from data set
        // replace the conents of the view with that element
        holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
