package com.techosoft.idea.playsome;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.techosoft.idea.playsome.utilities.DatePickerFragment;
import com.techosoft.idea.playsome.utilities.MyHelper;


public class FormWant extends AppCompatActivity {

    private DatePicker datePicker;
    MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_want);

        // setup helper
        myHelper = new MyHelper(this);
    }

    public void onUserSelectValue(){
        myHelper.displayToast("ok, its after the click");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");



    }
}
