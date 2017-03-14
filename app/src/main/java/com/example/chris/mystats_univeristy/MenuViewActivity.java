package com.example.chris.mystats_univeristy;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MenuViewActivity extends ActionBarActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.about_page) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void aboutClick(MenuItem item) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void homeClick(MenuItem item) {
        Intent intent = new Intent(this, HomePage.class);
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