package com.techosoft.idea.playsome.utilities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.techosoft.idea.playsome.R;

public class ItemDetail extends AppCompatActivity {

    TextView tvTitle, tvDetail, tvExpDate;
    Button btnGive, btnBack;
    MyHelper myHelper;

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
        Log.d(myHelper.mConst.LOG_TAG, "intent extracted: " + title + detail + expDate);

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
    }

    void killActivity(){
        finish();
    }
}
