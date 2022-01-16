package com.f101469.mypastaapp.ui.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.f101469.mypastaapp.OrderActivity;

import java.util.Calendar;
import java.util.Locale;

public class TimePickerFragment extends DialogFragment
    implements TimePickerDialog.OnTimeSetListener {

  private Integer selectedHour;
  private Integer selectedMinute;

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    // Use the current time as the default values for the picker
    final Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);

    // Create a new instance of TimePickerDialog and return it
    return new TimePickerDialog(
        getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
  }

  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    OrderActivity orderActivity = (OrderActivity) getActivity();
    if (orderActivity != null) {
      orderActivity.processTimePickerResult(hourOfDay, minute);
    }
  }
}
