package com.example.chris.mystats_univeristy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

/**
 * Created by chris on 06/04/17.
 * This class creates a pop up dialog when the user searches for a course but has no search results
 */


public class NoCoursesFoundFragment extends DialogFragment {

    /**
     * Creates the dialog box with an ok button to say that the user has seen the message
     * @param savedInstanceState - Current state
     * @return - The dialog to be shown
     */
    public Dialog onCreateDialog(Bundle savedInstanceState){

       LayoutInflater inflater = getActivity().getLayoutInflater();
       AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       builder.setView(inflater.inflate(R.layout.no_courses_found_dialog_frag, null));
       builder.setNeutralButton( "Ok ", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("i have been clicked" , "bli");
            }
        });

        return builder.create();

    }


}
