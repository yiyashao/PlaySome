package com.techosoft.idea.playsome;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.techosoft.idea.playsome.utilities.DatePickerFragment;
import com.techosoft.idea.playsome.utilities.MyHelper;

import java.text.DateFormat;
import java.util.Date;


public class FormWant extends AppCompatActivity {

    private DatePicker datePicker;
    MyHelper myHelper;
    Button buttonDate, buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_want);

        // setup helper
        myHelper = new MyHelper(this);

        // setup UI
        buttonDate = (Button)findViewById(R.id.buttnDate);
        buttonSubmit = (Button)findViewById(R.id.btnSubmitWanted);

        // initialize buttons
        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        buttonDate.setText(currentDateTimeString);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendWantInfoToServer();
            }
        });

    }

    public void onUserSelectValue(Date date){
        String dateTimeString = DateFormat.getDateInstance().format(date);
        buttonDate.setText(dateTimeString);
        Log.d(myHelper.mConst.LOG_TAG, "got date from the picker: " + date.toString());
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void sendWantInfoToServer(){
        myHelper.displayToast("ok, sending the info to server");
    }
}
