package com.techosoft.idea.playsome;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.techosoft.idea.playsome.models.WantItem;
import com.techosoft.idea.playsome.utilities.CloudAgent;
import com.techosoft.idea.playsome.utilities.DatePickerFragment;
import com.techosoft.idea.playsome.utilities.MyHelper;

import java.text.DateFormat;
import java.util.Date;

/**
 * activity to submit want item information and save to cloud
 */
public class FormWant extends AppCompatActivity {

    //class varibles
    private DatePicker datePicker;
    private MyHelper myHelper;
    private Date expireDate;
    private boolean dateSetFlag;

    // UI reference
    Button buttonDate, buttonSubmit;
    EditText textTitle, textDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_want);

        // setup helper and varibles
        myHelper = new MyHelper(this);
        expireDate = new Date();

        // setup UI components
        buttonDate = (Button)findViewById(R.id.buttnDate);
        buttonSubmit = (Button)findViewById(R.id.btnSubmitWanted);
        textTitle = (EditText)findViewById(R.id.wantItemTitle);
        textDetail = (EditText)findViewById(R.id.wantItemDetail);

        // initialize buttons
        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        buttonDate.setText(currentDateTimeString);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(myHelper.mConst.LOG_TAG, "user has submitted data");
                if(isValidData()){
                    sendWantInfoToServer();
                    Log.d(myHelper.mConst.LOG_TAG, "user data saved on CLOUD and closing want form");
                    killActivity();
                }else{
                    askForReEntry();
                }
            }
        });

    }


    //================================= HELPER FUNC =======================================
    private void askForReEntry() {
        myHelper.displayToast("Invalid data entry, please re-enter. Thanks.");
    }

    /**
     * data validator
     * @return data check result
     */
    private boolean isValidData() {
        return true; //TODO
    }

    /**
     * call back func from the data picker fragment, will reset the button text after date selected
     * @param date
     */
    public void onUserSelectDate(Date date){
        String dateTimeString = DateFormat.getDateInstance().format(date);
        buttonDate.setText(dateTimeString);
        Log.d(myHelper.mConst.LOG_TAG, "got date from the picker: " + date.toString());
        this.expireDate = date;
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void sendWantInfoToServer(){
        CloudAgent cloudConn = new CloudAgent(this);
        WantItem wantItem = new WantItem();
        wantItem.detail = textDetail.getText().toString();
        wantItem.title = textTitle.getText().toString();
        wantItem.expireDate = this.expireDate;
        wantItem.ownerId = myHelper.getSettingsInt(myHelper.mConst.KEY_USER_ID);
        cloudConn.saveWantItem(wantItem);
    }

    void killActivity(){
        finish();
    }
    //================================= HELPER FUNC END ====================================
}
