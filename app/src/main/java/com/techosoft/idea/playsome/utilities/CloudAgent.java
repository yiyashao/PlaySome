package com.techosoft.idea.playsome.utilities;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.techosoft.idea.playsome.WantList;
import com.techosoft.idea.playsome.models.WantItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by davidsss on 16-08-06.
 * class used to interact with cloud service, so no need to develop own api
 */
public class CloudAgent extends ContextWrapper {

    private Constants mConst;
    private MyHelper myHelper;

    public CloudAgent(Context base){ //use contextWrapper to handle the pass of context
        super(base);
        //initialize objects
        mConst = new Constants();
        AVOSCloud.initialize(this, mConst.CLOUD_KEY_01, mConst.CLOUD_KEY_02); //initilize the cloud service
        myHelper = new MyHelper(base);
    }

    public void saveWantItem(final String title, String detail, Date date, int userId){
        final AVObject wantItem = new AVObject(mConst.TABLE_WANT_ITEM);
        wantItem.put(mConst.WANT_ITEM_TITLE, title);
        wantItem.put(mConst.WANT_ITEM_DETAIL, detail);
        wantItem.put(mConst.WANT_ITEM_DATE, date);
        wantItem.put(mConst.WANT_ITEM_USERID, userId);
        wantItem.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    // 存储成功
                    Log.d(mConst.LOG_TAG, "want item saved with objId: " + wantItem.getObjectId());
                    // 保存成功之后，objectId 会自动从服务端加载到本地
                } else {
                    Log.d(mConst.LOG_TAG, "failed save want_item, title: " + title);
                    // 失败的话，请检查网络环境以及 SDK 配置是否正确
                }
            }
        });
    }

    public void saveWantItem(WantItem myWantItem){
        final AVObject wantItem = new AVObject(mConst.TABLE_WANT_ITEM);
        wantItem.put(mConst.WANT_ITEM_TITLE, myWantItem.title);
        wantItem.put(mConst.WANT_ITEM_DETAIL, myWantItem.detail);
        wantItem.put(mConst.WANT_ITEM_DATE, myWantItem.expireDate);
        wantItem.put(mConst.WANT_ITEM_USERID, myWantItem.ownerId);
        final String title = myWantItem.title; //this is used for error message, but may never used
        wantItem.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    // 存储成功
                    Log.d(mConst.LOG_TAG, "want item saved with objId: " + wantItem.getObjectId());
                    // 保存成功之后，objectId 会自动从服务端加载到本地
                } else {
                    Log.d(mConst.LOG_TAG, "failed save want_item, title: " + title);
                    // 失败的话，请检查网络环境以及 SDK 配置是否正确
                }
            }
        });
    }

   /* public void getAvObject(){
        AVQuery<AVObject> query = new AVQuery<>("Todo");
        query.whereEqualTo("number", 2);
        // 如果这样写，第二个条件将覆盖第一个条件，查询只会返回 priority = 1 的结果
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                List<AVObject> priorityEqualsZeroTodos = list;// 符合 priority = 0 的 Todo 数组
                Log.d("LeanCloud", "ok, found some records " + list.size());
                AVObject oneItem = list.get(0);
                setMessageValue(oneItem.get("content").toString());
            }
        });
    }*/


}
