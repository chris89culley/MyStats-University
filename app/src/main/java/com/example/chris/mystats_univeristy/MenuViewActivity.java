package com.example.chris.mystats_univeristy;

import android.app.Dialog;
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

import aboutFragments.About;

public class MenuViewActivity extends AppCompatActivity {
    int i = 0;
    Dialog dialog;

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
    }

    /**
     * the dialog fragment has a on click event to close the fragment and continue with the previous activity.
     * @param v
     */
    public void dialogClose(View v){
        dialog.dismiss();
    }


}

