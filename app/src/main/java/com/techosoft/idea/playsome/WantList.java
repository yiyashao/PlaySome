package com.techosoft.idea.playsome;

import android.app.ProgressDialog;
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
import android.widget.ProgressBar;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.techosoft.idea.playsome.models.WantItem;
import com.techosoft.idea.playsome.utilities.AdapterWantList;
import com.techosoft.idea.playsome.utilities.MyHelper;

import java.util.ArrayList;
import java.util.List;

public class WantList extends AppCompatActivity {

    //helpers
    MyHelper myHelper;

    // UI reference
    private ListView lvWantList;
    private ProgressDialog progress;

    // variables
    ArrayList<WantItem> itemList; // to store the want items

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //this could be deleted

        //init helpers
        myHelper = new MyHelper(this);
        AVOSCloud.initialize(this, myHelper.mConst.CLOUD_KEY_01, myHelper.mConst.CLOUD_KEY_02); //initilize the cloud service
        lvWantList = (ListView) findViewById(R.id.listViewWantItems);
        itemList = new ArrayList<WantItem>();

        //UI setup
        //TODO make this button a add WANT item
        FloatingActionButton addNewWantButton = (FloatingActionButton) findViewById(R.id.newWantButton);
        addNewWantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add New Want Item", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                addWantItem();
            }
        });

        //start doing business
        loadDataFromCloud();
    }

    private void loadDataFromCloud(){

        progress=new ProgressDialog(this);
        progress.setMessage("loading data");
        progress.show();

        AVQuery<AVObject> query = new AVQuery<>("want_item");//query.whereEqualTo("user_id", 1); add this line to get records only for this user
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> resultList, AVException e) {
                //loop through the result if returned value
                if(resultList.size() > 0){
                    for(int i = 0; i < resultList.size(); i ++){
                        AVObject cloudItem = resultList.get(i);
                        WantItem tempWantItem = new WantItem();
                        tempWantItem.title = cloudItem.getString(myHelper.mConst.WANT_ITEM_TITLE);
                        tempWantItem.detail = cloudItem.getString(myHelper.mConst.WANT_ITEM_DETAIL);
                        tempWantItem.expireDate = cloudItem.getDate(myHelper.mConst.WANT_ITEM_DATE);
                        tempWantItem.itemCloudId = cloudItem.getObjectId();
                        itemList.add(tempWantItem);
                    }
                    Log.d(myHelper.mConst.LOG_TAG, "record found: " + resultList.size());
                }else{
                    Log.d(myHelper.mConst.LOG_TAG, "no record found for userId: " + 1); //current user have no record
                }

                //mySleep(500); used to make sure the loading diagram shows
                whenResultReturned(itemList);

                progress.hide();
            }
        });
    }

    // after a click on a CELL, intent to start new activity
    // attach the item detail then go to the activity
    private void goToDetailActivity(WantItem selectedItem){
        Intent detailIntent = new Intent(this, ItemDetail.class);
        detailIntent.putExtra(myHelper.mConst.WANT_ITEM_OBJID, selectedItem.itemCloudId);
        detailIntent.putExtra("title", selectedItem.title);
        detailIntent.putExtra("detail", selectedItem.detail);
        detailIntent.putExtra("expDate", selectedItem.expireDate.toString());
        //myHelper.displayToast(selectedItem.itemCloudId);
        startActivity(detailIntent);
    }
    // go to the WANT FORM activity
    private void addWantItem(){
        Intent wantForm = new Intent(this, FormWant.class);
        startActivity(wantForm);
    }

    // called when cloud data loaded, then build the cell list
    public void whenResultReturned(final ArrayList<WantItem> itemList){
        AdapterWantList adapter = new AdapterWantList(this, itemList);
        lvWantList.setAdapter(adapter);
        //use customized adapter to show the cell in a better way
        lvWantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                WantItem selectedItem = itemList.get(position); //find selected item
                goToDetailActivity(selectedItem);
            }
        });
    }

    public void mySleep(long mSec){
        try {
            Thread.sleep(mSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
