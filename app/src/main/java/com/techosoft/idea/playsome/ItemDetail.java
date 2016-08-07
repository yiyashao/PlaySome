package com.techosoft.idea.playsome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.techosoft.idea.playsome.R;
import com.techosoft.idea.playsome.utilities.CloudAgent;
import com.techosoft.idea.playsome.utilities.MyHelper;

import java.util.List;

public class ItemDetail extends AppCompatActivity {

    TextView tvTitle, tvDetail, tvExpDate;
    Button btnGive, btnBack;
    MyHelper myHelper;

    //variables
    int giveUserId = 0;

    final static String ACTIVITY_NAME = "activity/ItemDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        //init helpers
        myHelper = new MyHelper(this);

        //setup UI
        tvTitle = (TextView)findViewById(R.id.tvItemTitle);
        tvDetail = (TextView)findViewById(R.id.tvItemDetail);
        tvExpDate = (TextView)findViewById(R.id.tvItemExpDate);
        btnGive = (Button)findViewById(R.id.btnItemDetailGive);
        btnBack = (Button)findViewById(R.id.btnItemDetailBack);

        //receive the intent
        String title = this.getIntent().getExtras().getString("title");
        String detail = this.getIntent().getExtras().getString("detail");
        String expDate = this.getIntent().getExtras().getString("expDate");
        final String itemCloudId = this.getIntent().getExtras().getString(myHelper.mConst.WANT_ITEM_OBJID);
        Log.d(myHelper.mConst.LOG_TAG, ACTIVITY_NAME + " intent extracted: " + title + detail + expDate);

        tvTitle.setText(title);
        tvDetail.setText(detail);
        tvExpDate.setText(expDate);

        //setup listners
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                killActivity();
            }
        });
        btnGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //put this item in the user's give waiting box
                giveUserId = myHelper.getSettingsInt(myHelper.mConst.KEY_USER_ID);
                //check if item is already in giver box
                isItemInTable(myHelper.mConst.TABLE_GIVE_BOX,
                        myHelper.mConst.GIVE_BOX_WANT_ITEM_OBJID, itemCloudId);
            }
        });
    }

    //save the want item and giver link into cloud database
    void putItemInUserGiveList(String itemCloudId, int userId){
        CloudAgent cloudConn = new CloudAgent(this);
        cloudConn.saveWantItemInGiveList(itemCloudId, userId);
        killActivity();
    }

    //check if the item is already in the giver box
    public void isItemInTable(String tableName, String fieldName, String itemCloudId){
        final String itemId = itemCloudId; //for listener, a final value for inner class
        AVQuery<AVObject> query = new AVQuery<>(tableName);
        query.whereEqualTo(fieldName, itemCloudId);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if(list != null) { //avoid null pointer
                    if (list.size() > 0) {//item already in give list
                        myHelper.displayToast("item already exist in your give box.");
                    } else {//item not in give list, can go to give list
                        putItemInUserGiveList(itemId, giveUserId);
                    }
                }else{
                    myHelper.displayToast("does ever here");
                    putItemInUserGiveList(itemId, giveUserId);
                }
            }
        });
    }

    void killActivity(){
        finish();
    }
}
