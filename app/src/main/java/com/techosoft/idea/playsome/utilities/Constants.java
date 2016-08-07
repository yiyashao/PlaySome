package com.techosoft.idea.playsome.utilities;

/**
 * Created by davidsss on 16-08-06.
 * used to store global varibles
 */
public class Constants {

    //list of setting constants
    public static final String PERF_SETTING = "settings";
    public static final String KEY_LOGIN = "key_login";
    public static final String KEY_USER_ID = "key_user_id";
    public static final String KEY_WANT_CLOUD_ID = "key_want_cloud_id";

    public static final String LOG_TAG = "TechoSoft LOG";


    //for LeanCloud, table name and column name
    public final static String CLOUD_KEY_01 = "V8rs92rEWMh65J46K9Np6jvd-gzGzoHsz"; //app keys
    public final static String CLOUD_KEY_02 = "nom630shHF4kLUVaQm5L0f4j";
    public final static String TABLE_WANT_ITEM = "want_item";
    public final static String WANT_ITEM_TITLE = "title";
    public final static String WANT_ITEM_DETAIL = "detail";
    public final static String WANT_ITEM_DATE = "expire_date";
    public final static String WANT_ITEM_USERID = "user_id";
    public final static String WANT_ITEM_OBJID = "objectId";
    public static final String TABLE_GIVE_BOX = "give_box";
    public static final String GIVE_BOX_WANT_ITEM_OBJID = "want_item_objId";
    public static final String GIVE_BOX_GIVER_ID = "giver_id";


    public static final int USER_ID_DEFAULT = 0;


    Constants(){} //initializer, not used
}
