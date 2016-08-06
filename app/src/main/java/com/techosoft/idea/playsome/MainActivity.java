package com.techosoft.idea.playsome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

import com.techosoft.idea.playsome.utilities.MyHelper;

public class MainActivity extends AppCompatActivity {

    // Utilities
    MyHelper myHelper;// = new MyHelper(MainActivity.this);

    // UI references.
    Button btnWant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHelper = new MyHelper(this);
        myHelper.setSettingsStr("testKey", "this is test value");

        //setup UI
        btnWant = (Button)findViewById(R.id.btnWant);
        btnWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //myHelper.displayToast(myHelper.getSettingsStr("testKey"));
                goToActivity(FormWant.class);
            }
        });

        myHelper.setLogin(false);

        //goToActivity(LoginActivity.class);
    }


    //inner helpers
    private void goToActivity(Class destinationClass) {
        Intent intent = new Intent(this, destinationClass);
        startActivity(intent);
    }
}
