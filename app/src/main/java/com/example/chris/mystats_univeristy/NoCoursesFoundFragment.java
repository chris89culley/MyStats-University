package com.example.chris.mystats_univeristy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by chris on 06/04/17.
 */

public class NoCoursesFoundFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("No results found").setNeutralButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("i have been clicked" , "bli");
            }
        });

        return builder.create();

    }


}
