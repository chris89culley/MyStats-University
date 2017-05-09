package com.example.chris.mystats_univeristy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import com.ogaclejapan.arclayout.ArcLayout;
import java.util.ArrayList;
import java.util.List;
import Animations.AnimatorUtils;
import Data.RSDBhandler;

public class Home extends AppCompatActivity {



    private ArcLayout menuOptions;


    /**
     * Thhis method runs through all the items of the arc layout and animates them moving from the bottom
     * of the screen to their place with a bounce
     * @param layout
     */
    private void animateTheArclayout(ArcLayout layout){
        List<Animator> animatorList = new ArrayList();


        for (int i = 0; i < layout.getChildCount(); i ++){
            animatorList.add(createShowItemAnimator(layout.getChildAt(i)));
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.setInterpolator(new OvershootInterpolator());
        animatorSet.playSequentially(animatorList);
        animatorSet.start();
    }


    /**
     * When the page is resumed from going off onto either a different page or off the app
     * the animation is restarted
     */
    @Override
    protected void onResume(){
        animateTheArclayout(menuOptions);
        super.onResume();
    }

    /**
     *
     * Sets up the fonts on the home page
     *
     * @param quickSearchButton  - The button which navigates to the search page
     * @param recentSearchButton - The button that navigates to the recent searches
     * @param ucasTipsButton - The button which navigates to the ucas tips page
     * @param aboutButton - The button which navigates to the about page
     */
    private void setUpHomeFonts(Button quickSearchButton, Button recentSearchButton, Button ucasTipsButton, Button aboutButton){
        Typeface retroFont = Typeface.createFromAsset(this.getAssets(), "fonts/Josefin_Sans/JosefinSans-SemiBold.ttf");
        quickSearchButton.setTypeface(retroFont);
        recentSearchButton.setTypeface(retroFont);
        ucasTipsButton.setTypeface(retroFont);
        aboutButton.setTypeface(retroFont);
    }
    /**
     * When the app is first created this method is run, this method sets up all the buttons and their
     * actions once pressed
     * @param savedInstanceState - current saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        menuOptions = (ArcLayout) findViewById(R.id.arc) ;
        animateTheArclayout(menuOptions);      //Sets up all the buttons
        Button quickSearchButton  = (Button) findViewById(R.id.quicksearchbutton);
        Button recentSearchButton = (Button) findViewById(R.id.recentsearchbutton);
        Button ucasTipsButton = (Button) findViewById(R.id.ucastipbutton);
        Button aboutButton = (Button) findViewById(R.id.aboutbutton);
        setUpHomeFonts(quickSearchButton,recentSearchButton,ucasTipsButton,aboutButton);
        setUpButtonListnersOnHomePage(quickSearchButton,recentSearchButton,ucasTipsButton,aboutButton);
    }

    /**
     * Sets up the listeners for the buttons on the home page so that they navigate to the correct pages
     *
     * @param quickSearchButton  - The button which navigates to the search page
     * @param recentSearchButton - The button that navigates to the recent searches
     * @param ucasTipsButton - The button which navigates to the ucas tips page
     * @param aboutButton - The button which navigates to the about page
     */
    private void setUpButtonListnersOnHomePage(Button quickSearchButton, Button recentSearchButton, Button ucasTipsButton, Button aboutButton) {

        quickSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchPage.class);
                startActivity(intent);
            }
        });

        //Recent search page gets the recent searches from the db and sends them to the search results page
        recentSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchResults.class);
                RSDBhandler dataGrabber =  new RSDBhandler(v.getContext());
                intent.putExtra("searchedName" , "Recent Searches");
                intent.putParcelableArrayListExtra("searchResults" , dataGrabber.readAll());
                startActivity(intent);
            }
        });


        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), About.class);
                startActivity(intent);
            }
        });

        ucasTipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UcasTips.class);
                startActivity(intent);
            }
        });


    }
    /**
     * This method takes an item and applies an animation
     * @param item - The item being animated
     * @return - An animator object
     */
    private Animator createShowItemAnimator(final View item)
    {
        //Where the animation begins
        float dx =  0;
        float dy = 1000;

        item.setRotation(300f);
        item.setTranslationX(dx);
        item.setTranslationY(dy);

        //The set of animations to be applied
        Animator anim = ObjectAnimator.ofPropertyValuesHolder(item,
                AnimatorUtils.rotation(720f, 0f),
                AnimatorUtils.translationX(dx , 0f),
                AnimatorUtils.translationY(dy , 0f));

        anim.addListener(new AnimatorListenerAdapter() {

            public  void onAnimationEnd(Animator animator){
                super.onAnimationEnd(animator);
                item.setTranslationX(0f);
                item.setTranslationY(0f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });

        return anim;
    }
}
