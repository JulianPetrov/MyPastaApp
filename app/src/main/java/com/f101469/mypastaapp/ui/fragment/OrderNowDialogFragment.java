package com.f101469.mypastaapp.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.f101469.mypastaapp.R;

public class OrderNowDialogFragment extends DialogFragment {
  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Bundle arguments = getArguments();
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder
        .setMessage(R.string.order_now_message)
        .setPositiveButton(
            R.string.confirm,
            (dialog, id) -> {
              Intent intent = new Intent(Intent.ACTION_SEND);
              intent.setType("plain/text");
              intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"test@email.address"});
              intent.putExtra(Intent.EXTRA_SUBJECT, "Pasta Order");
              if (arguments != null) {
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    String.format(
                        "You ordered %s pastas '%s'. The total price of your order is: %s",
                        arguments.getString("quantity"),
                        arguments.getString("pastaName"),
                        arguments.getString("price")));
              } else {
                intent.putExtra(Intent.EXTRA_TEXT, "Invalid order!");
              }
              startActivity(Intent.createChooser(intent, ""));
            })
        .setNegativeButton(R.string.cancel, (dialog, id) -> dismiss());
    return builder.create();
  }
}
