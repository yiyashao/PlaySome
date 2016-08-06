package com.techosoft.idea.playsome;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.techosoft.idea.playsome.models.WantItem;
import com.techosoft.idea.playsome.utilities.AdapterWantList;

import java.util.ArrayList;

public class WantList extends AppCompatActivity {

    // UI reference
    private ListView lvWantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton addNewWantButton = (FloatingActionButton) findViewById(R.id.newWantButton);
        addNewWantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        lvWantList = (ListView) findViewById(R.id.listViewWantItems);

        final ArrayList<WantItem> itemList = new ArrayList<WantItem>();
        WantItem a = new WantItem();
        a.title = "this is the A one";
        a.detail = "OK, now more for a detail";
        WantItem b = new WantItem();
        b.title = "this is the B title";
        b.detail = "OK, now more for a detail";
        itemList.add(a);
        itemList.add(b);

        AdapterWantList adapter = new AdapterWantList(this, itemList);
        lvWantList.setAdapter(adapter);
    }

}
