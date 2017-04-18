package com.example.chris.mystats_univeristy;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wang.avi.AVLoadingIndicatorView;

import Data.CourseTypes;
import Data.DatabaseInformationQuerier;

public class MenuViewActivity extends AppCompatActivity {
    private MenuViewActivity currentActivity = this;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference(); //The database reference
    private final DatabaseInformationQuerier databaseInfomationQuerier = new DatabaseInformationQuerier(database);
    int i = 0;
    Dialog dialog;
    Button searchBtn;
    private TextView dialogBox;
    private EditText searchedCourseEditTextField; //The text field where the user enters the course name they wish to search
    private AVLoadingIndicatorView loadingIcon;


    /**
     * on create options menu sets up the a home indicator with the 'hamburger' icon.
     * navigation.xml is the menu used for the home indicator.
     * overflow_menu.xml is the menu used for the overflow.
     * the overflow icon (three dots) has been replaced by a customer overflow icon which uses a search icon found in styles folder.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);
        getMenuInflater().inflate(R.menu.overflow_menu, menu);

        return true;
    }


    /**
     * this overriding method handles the on click events of the drawerlayout (hamburger icon)
     * shows and closes the menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        switch (item.getItemId()) {
            case android.R.id.home:
                if(i == 0) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    i++;
                }
                else if(i == 1){
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    i--;
                }
                break;
        }
        return true;
    }

    /**
     * on selected item from menu, start activity.
     * @param item
     */
    public void aboutClick(MenuItem item) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    /**
     * on selected item from menu, start activity.
     * @param item
     */
    public void homeClick(MenuItem item) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    /**
     * on selected item from menu, start activity.
     * @param item
     */
    public void searchClick(MenuItem item) {
        Intent intent = new Intent(this, SearchPage.class);
        startActivity(intent);
    }

    /**
     * on click of the quick search icon (overflow), open the input dialog fragment.
     * @param item
     */
    public void searchQuick(MenuItem item) {
        openDialog();
    }

    /**
     * opens and sets up the dialog fragment.
     */
    public void openDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_quick_search);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        searchBtn = (Button) dialog.findViewById(R.id.searchBtn);
        handleSearchButtonPressed(dialog.getCurrentFocus());
//        View search = dialog.findViewById(R.id.editTextDialogUserInput);
//        search.onTouchEvent()
    }


    /**
     * the dialog fragment has a on click event to close the fragment and continue with the previous activity.
     * @param v
     */
    public void dialogClose(View v){

        dialog.dismiss();
    }



    /**
     * Updates the database querier with the intent and current activity so that it can create
     * the search result page
     * @param intent
     */
    private void updateInfoQuerierWithIntentIntentions(Intent intent){
        databaseInfomationQuerier.setIntent(intent);
        databaseInfomationQuerier.setCurrent(currentActivity);

    }

    /**
     * Gets the course name that the user wishes to search for
     * @return - The course name
     */
    private String getTheCourseToBeSearched(View v){
        searchedCourseEditTextField = (EditText) v.findViewById(R.id.editTextDialogUserInput);
        return searchedCourseEditTextField.getText().toString();
    }

    /**
     * This method deals with the event that the search button is pressed. It determines whether the
     * user wishes to search around their current location, an entered location or neither and makes a request
     * to the database either through radius checker or directly to the database querier
     *
     */
    private void handleSearchButtonPressed(final View v){

        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SearchResults.class);
                String course = getTheCourseToBeSearched(v);
                if(course.isEmpty()) return;
                updateInfoQuerierWithIntentIntentions(intent);
                    databaseInfomationQuerier.getAllCoursesByCourseName(course,
                            CourseTypes.FULL_TIME);
                }
            });
    }



}

