package com.f101469.mypastaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.f101469.mypastaapp.ui.fragment.DatePickerFragment;
import com.f101469.mypastaapp.ui.fragment.OrderNowDialogFragment;
import com.f101469.mypastaapp.ui.fragment.TimePickerFragment;

import java.util.Locale;

public class OrderActivity extends AppCompatActivity {

  private int currentQuantity = 1;

  private TextView pastaNameTextView;
  private TextView priceTextView;
  private TextView orderDateTextView;
  private TextView orderTimeTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_order);

    ActionBar supportActionBar = getSupportActionBar();
    if (supportActionBar != null) supportActionBar.setDisplayHomeAsUpEnabled(true);

    Spinner quantitySpinner = findViewById(R.id.quantity_spinner);
    Button datePickerButton = findViewById(R.id.date_picker_button);
    Button timePickerButton = findViewById(R.id.time_picker_button);
    Button orderNowButton = findViewById(R.id.order_now_button);

    pastaNameTextView = findViewById(R.id.pasta_name_view);
    priceTextView = findViewById(R.id.price_text_view);
    orderDateTextView = findViewById(R.id.order_date_text_view);
    orderTimeTextView = findViewById(R.id.order_time_text_view);

    getAndSetIntentData();

    quantitySpinner.setOnItemSelectedListener(
        new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            double piecePriceBeforeChange = parsePrice() / currentQuantity;
            currentQuantity = position + 1;
            double price = piecePriceBeforeChange * currentQuantity;
            setPriceInRightFormat(price);
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {
            double piecePriceBeforeChange = parsePrice() / currentQuantity;
            currentQuantity = 1;
            double price = piecePriceBeforeChange * currentQuantity;
            setPriceInRightFormat(price);
          }
        });

    datePickerButton.setOnClickListener(this::showDatePickerDialog);
    timePickerButton.setOnClickListener(this::showTimePickerDialog);

    orderNowButton.setOnClickListener(
        view -> {
          Bundle bundle = new Bundle();
          bundle.putString("pastaName", pastaNameTextView.getText().toString());
          bundle.putString("price", priceTextView.getText().toString());
          bundle.putString("quantity", String.valueOf(currentQuantity));
          OrderNowDialogFragment orderNowDialogFragment = new OrderNowDialogFragment();
          orderNowDialogFragment.setArguments(bundle);
          orderNowDialogFragment.show(getSupportFragmentManager(), "orderNow");
        });
  }

  void getAndSetIntentData() {
    if (getIntent().hasExtra("pastaName") && getIntent().hasExtra("price")) {
      // Getting Data from Intent
      double parsedPrice = Double.parseDouble(getIntent().getStringExtra("price").split("\\s+")[0]);
      pastaNameTextView.setText(getIntent().getStringExtra("pastaName"));
      priceTextView.setText(String.format(Locale.getDefault(), "%.2f", parsedPrice));
    } else {
      Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
    }
  }

  @SuppressLint("NonConstantResourceId")
  public void onCheckboxClicked(View view) {
    boolean checked = ((CheckBox) view).isChecked();
    double price = parsePrice();

    switch (view.getId()) {
      case R.id.extra_cheese_checkBox:
        if (checked) price += 2 * currentQuantity;
        else price -= 2 * currentQuantity;
        break;
      case R.id.extra_meat_checkbox:
        if (checked) price += 2.5 * currentQuantity;
        else price -= 2.5 * currentQuantity;
        break;
      case R.id.extra_veggies_checkbox:
        if (checked) price += currentQuantity;
        else price -= currentQuantity;
        break;
    }
    setPriceInRightFormat(price);
  }

  private void setPriceInRightFormat(double price) {
    priceTextView.setText(String.format(Locale.getDefault(), "%.2f BGN", price));
  }

  private double parsePrice() {
    return Double.parseDouble(priceTextView.getText().toString().split("\\s+")[0]);
  }

  public void showTimePickerDialog(View v) {
    DialogFragment newFragment = new TimePickerFragment();
    newFragment.show(getSupportFragmentManager(), "timePicker");
  }

  public void showDatePickerDialog(View v) {
    DialogFragment newFragment = new DatePickerFragment();
    newFragment.show(getSupportFragmentManager(), "datePicker");
  }

  public void processTimePickerResult(int hourOfDay, int minute) {
    orderTimeTextView.setText(String.format(Locale.getDefault(), " AT %d:%02d", hourOfDay, minute));
    orderTimeTextView.setVisibility(View.VISIBLE);
  }

  public void processDatePickerResult(int day, int month, int year) {
    orderDateTextView.setText(
        String.format(Locale.getDefault(), "ON %02d.%02d.%d", day, month + 1, year));
    orderDateTextView.setVisibility(View.VISIBLE);
  }
}
