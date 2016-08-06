package com.techosoft.idea.playsome.utilities;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.techosoft.idea.playsome.FormWant;

import java.util.Calendar;

/**
 * Created by davidsss on 16-08-06.
 * date picker fragment for button click
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public int year, monthOfYear, dayOfMonth;

    public DatePickerFragment(){
        super();
        year = 0;
        monthOfYear = 0;
        dayOfMonth = 0;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.monthOfYear = monthOfYear;
        this.dayOfMonth = dayOfMonth;
        FormWant callingActivity = (FormWant) getActivity();
        callingActivity.onUserSelectValue();
        //dialog.dismiss();
    }


}
