package com.bagelbytes.libertyhacksmerge;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

/**
 * Created by tkaram on 8/17/2017.
 */

public class AddPaymentDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        String[] s = { "Test","Other"};

        final ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, s);

        final Spinner sp = new Spinner(getContext());
        sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        sp.setAdapter(adp);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(sp)
                .setTitle("Select bill type: ")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String text = sp.getSelectedItem().toString();

                        if (text == "Other"){
                            Intent myIntent = new Intent(getContext(),AddPaymentsActivity.class);
                            getContext().startActivity(myIntent);
                        }else{
                            Intent myIntent = new Intent(getContext(),Utility_Login.class);
                            getContext().startActivity(myIntent);
                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }
}