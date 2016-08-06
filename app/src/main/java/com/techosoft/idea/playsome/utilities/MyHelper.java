package com.techosoft.idea.playsome.utilities;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by davidsss on 16-08-06.
 */
public class MyHelper extends ContextWrapper {

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    public Constants mConst;
    Context context;

    public MyHelper(Context base){
        super(base);
        //initialize the editor
        context = this;
        settings = context.getSharedPreferences(mConst.PERF_SETTING, 0);
        editor = settings.edit();
    }

    public void displayToast(String text){
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.show();
    }

    public void setLogin(boolean loginFlag){
        editor.putBoolean(mConst.KEY_LOGIN, loginFlag);
        editor.commit();
    }

    /**
     * universal method to set a string value to shared preference
     * @param key
     * @param value
     */
    public void setSettingsStr(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public void setSettingsInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public String getSettingsStr(String key){
        return settings.getString(key, "");
    }
    public int getSettingsInt(String key){
        return settings.getInt(key, 0);
    }

    public boolean isLogin(){
        return settings.getBoolean(mConst.KEY_LOGIN, false);
    }


}
