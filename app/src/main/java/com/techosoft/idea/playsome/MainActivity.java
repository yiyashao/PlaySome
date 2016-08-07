package com.techosoft.idea.playsome;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.techosoft.idea.playsome.utilities.MyHelper;

public class MainActivity extends AppCompatActivity {

    // Utilities
    MyHelper myHelper;// = new MyHelper(MainActivity.this);

    // UI references.
    Button btnWant;
    Button btnWantList;
    Button btnTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHelper = new MyHelper(this);
        myHelper.setSettingsStr("testKey", "this is test value");
        myHelper.setSettingsInt(myHelper.mConst.KEY_USER_ID, 1); //hardcoded

        //setup UI
        btnWant = (Button)findViewById(R.id.btnWant);
        btnWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(FormWant.class);
            }
        });
        btnWantList = (Button)findViewById(R.id.btnWantList);
        btnWantList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(WantList.class);
            }
        });

        //test button

        btnTest = (Button) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "test button for snackbar message, and show progress bar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });



        //TO Delete **********
        myHelper.setLogin(false);

        //goToActivity(LoginActivity.class);
    }


    //inner helpers
    private void goToActivity(Class destinationClass) {
        Intent intent = new Intent(this, destinationClass);
        startActivity(intent);
    }
}
