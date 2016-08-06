package com.techosoft.idea.playsome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.techosoft.idea.playsome.models.WantItem;
import com.techosoft.idea.playsome.utilities.AdapterWantList;
import com.techosoft.idea.playsome.utilities.CloudAgent;
import com.techosoft.idea.playsome.utilities.MyHelper;

import java.util.ArrayList;
import java.util.List;

public class WantList extends AppCompatActivity {

    //helpers
    //CloudAgent cloudConn;
    MyHelper myHelper;

    // UI reference
    private ListView lvWantList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //this could be deleted

        //init helpers
        //cloudConn = new CloudAgent(this);
        myHelper = new MyHelper(this);
        AVOSCloud.initialize(this, myHelper.mConst.CLOUD_KEY_01, myHelper.mConst.CLOUD_KEY_02); //initilize the cloud service
        lvWantList = (ListView) findViewById(R.id.listViewWantItems);

        //TODO make this button a add WANT item
        FloatingActionButton addNewWantButton = (FloatingActionButton) findViewById(R.id.newWantButton);
        addNewWantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //form a query and get the result
        final ArrayList<WantItem> itemList = new ArrayList<WantItem>();
        AVQuery<AVObject> query = new AVQuery<>("want_item");
        //query.whereEqualTo("user_id", 1); add this line to get records only for this user
        //find records with userId as current ID

        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> resultList, AVException e) {
                //loop through the result if returned value
                if(resultList.size() > 0){
                    for(int i = 0; i < resultList.size(); i ++){
                        AVObject oneItem = resultList.get(i);
                        WantItem oneWantItem = new WantItem();
                        oneWantItem.title = oneItem.getString(myHelper.mConst.WANT_ITEM_TITLE);
                        oneWantItem.detail = oneItem.getString(myHelper.mConst.WANT_ITEM_DETAIL);
                        oneWantItem.expireDate = oneItem.getDate(myHelper.mConst.WANT_ITEM_DATE);
                        itemList.add(oneWantItem);
                    }
                    Log.d(myHelper.mConst.LOG_TAG, "record found: " + resultList.size());
                }else{
                    Log.d(myHelper.mConst.LOG_TAG, "no record found for userId: " + 1); //current user have no record
                }
                whenResultReturned(itemList);
            }
        });
    }

    //attach the item detail then go to the activity
    private void goToDetailActivity(WantItem selectedItem){
        Intent detailIntent = new Intent(this, ItemDetail.class);
        detailIntent.putExtra("title", selectedItem.title);
        detailIntent.putExtra("detail", selectedItem.detail);
        detailIntent.putExtra("expDate", selectedItem.expireDate.toString());
        startActivity(detailIntent);
    }

    public void whenResultReturned(final ArrayList<WantItem> itemList){
        AdapterWantList adapter = new AdapterWantList(this, itemList);
        lvWantList.setAdapter(adapter);

        lvWantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                WantItem selectedItem = itemList.get(position); //find selected item
                goToDetailActivity(selectedItem);
            }
        });

    }

}
