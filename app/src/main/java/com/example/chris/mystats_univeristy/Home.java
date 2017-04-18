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




    /**
     * Thhis method runs through all the items of the arc layout and animates them moving from the bottom
     * of the screen to their place
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Typeface retroFont = Typeface.createFromAsset(this.getAssets(), "fonts/Market_Deco.ttf");

        //Sets up the arc layout and an initial animation
        ArcLayout layout = (ArcLayout) findViewById(R.id.arc) ;
        animateTheArclayout(layout);

        //Sets up all the buttons
        Button quickSearchButton  = (Button) findViewById(R.id.quicksearchbutton);
        Button accountButton = (Button) findViewById(R.id.accountbutton);
        Button recentSearchButton = (Button) findViewById(R.id.recentsearchbutton);
        Button ucasTipsButton = (Button) findViewById(R.id.ucastipbutton);
        Button aboutButton = (Button) findViewById(R.id.aboutbutton);

        //Sets the fonts of all the buttons
        quickSearchButton.setTypeface(retroFont);
        accountButton.setTypeface(retroFont);
        recentSearchButton.setTypeface(retroFont);
        ucasTipsButton.setTypeface(retroFont);
        aboutButton.setTypeface(retroFont);

        //Sets the listeners to navigate to the correct pages
        quickSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchPage.class);
                startActivity(intent);
            }
        });

        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Account.class);
                startActivity(intent);
            }
        });

        recentSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchResults.class);
                RSDBhandler dataGrabber =  new RSDBhandler(v.getContext());
                intent.putParcelableArrayListExtra("searchResults" , dataGrabber.readAll());
                startActivity(intent);
            }
        });


        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AboutFragments.About.class);
                startActivity(intent);
            }
        });

        ucasTipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UcasTipsFragments.UcasTips.class);
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
        float dx =  0 - item.getX();
        float dy = 1000 - item.getY();

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
