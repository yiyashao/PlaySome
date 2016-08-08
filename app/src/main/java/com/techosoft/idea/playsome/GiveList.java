package com.techosoft.idea.playsome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;

import com.techosoft.idea.playsome.utilities.AdapterSwipeList;

/**
 * https://github.com/nemanja-kovacevic/recycler-view-swipe-to-delete/blob/master/app/src/main/java/net/nemanjakovacevic/recyclerviewswipetodelete/MainActivity.java
 *
 */
public class GiveList extends AppCompatActivity {

    RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        mRecycleView = (RecyclerView) findViewById(R.id.recycler_view);
        setUpRecyclerView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }





    /**
     *setup the recycler view
     */
    private void setUpRecyclerView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(this)); //choose linear layout manager, no use, so just create on the run
        mRecycleView.setAdapter(new GiveListAdapter()); //customized adapter only for this list
        mRecycleView.setHasFixedSize(true);
        setUpItemTouchHelper(); //for the swipe delete code
        setUpAnimationDecoratorHelper(); //for the special animation
     }

    private void setUpAnimationDecoratorHelper() {
    }


    /**
     * may method to setup the item Touch, for swipe then delete OR actions
     * This is the standard support library way of implementing "swipe to delete" feature. You can do custom drawing in onChildDraw method
     * but whatever you draw will disappear once the swipe is over, and while the items are animating to their new position the recycler view
     * background will be visible. That is rarely an desired effect.
     */
    private void setUpItemTouchHelper(){
        //to finish
    }


    /**
     * recyclerView adapeter
     */
    class GiveListAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

}
