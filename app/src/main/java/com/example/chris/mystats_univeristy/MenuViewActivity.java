package com.example.chris.mystats_univeristy;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

// this activity is to inflate the menu and will then be extended by other activities
public class MenuViewActivity extends AppCompatActivity {
    int i = 0;

    // the menu is set up and the default icon is replaced by the 'hamburger' icon
    // the menu is given the data from navigation.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);
        getMenuInflater().inflate(R.menu.navigation, menu);
        return false;
    }

    // on click/tap of menu icon the drawerlayout is called to bring menu into/out of view
    // the switch statement only validates and acts on menu icon click, for further development
    // other buttons/menu's could be added to the toolbar with their respective drawers.
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
        }
        return true;
    }

    // the methods below are activated below when on item within the navigation.xml is clicked
    // which is found within: Res > menu > navigation.xml
    public void aboutClick(MenuItem item) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void homeClick(MenuItem item) {
        Intent intent = new Intent(this, SearchPage.class);
        startActivity(intent);
    }

    public void accClick(MenuItem item) {
        Intent intent = new Intent(this, Account.class);
        startActivity(intent);
    }

    public void advClick(MenuItem item) {
        Intent intent = new Intent(this, AdvanceSearch.class);
        startActivity(intent);
    }


}

