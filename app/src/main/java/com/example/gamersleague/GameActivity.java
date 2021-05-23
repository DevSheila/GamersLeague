package com.example.gamersleague;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;



public class GameActivity extends AppCompatActivity implements View.OnClickListener,ReviewsActivity.ExampleDialogListener {


    @BindView(R.id.gameDetailsTextView) TextView mgameDetailsTextView;
    @BindView(R.id.reviewslistView) ListView mReviewsListView;
    @BindView(R.id.addCommentButton) Button maddCommentButton;





    private String[] comments= new String[] { "A very enjoyable progressive wave shooter with plenty of upgrades, abilities, cool locations and zombie types." ,"A lot better than I was expecting and if you like games like Crashlands, you'll love this for its cheap price.", "Only played for a few hours, and already it's worth the money. I suggest getting the premium edition as the DLC vehicles help you progress faster, and flatten the steep learning curve. It can run on my ryzen 5 3600 and rx 580 smoothly on on the 'high' graphics setting. Although it has a slow start, it is very addicting." ,"One annoying thing is that the camera is a bit wonky in places.However,would totally reccomend!","Graphics are beautiful and the gameplay is fun for what it is. If you were a fan of Mudrunners, then you will most likely be a fan of this"};
    private String[] ratings = new String[] {"4", "3", "4", "3","4" };

    ArrayList<String> allComments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);
        allComments.addAll(Arrays.asList(comments));

        ReviewArrayAdapter reviewAdapter = new ReviewArrayAdapter(GameActivity.this, android.R.layout.simple_list_item_1,comments,ratings);
        mReviewsListView.setAdapter(reviewAdapter);

        Intent intent = getIntent();
        String gameDetails= intent.getStringExtra("gameDetails");
        String game =intent.getStringExtra("game");


        mgameDetailsTextView.setText("Name : "+ game + " \n "+ gameDetails);

        maddCommentButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v == maddCommentButton) {
            openDialog();
        }
    }

    public void openDialog(){
        ReviewsActivity exampleDialog = new ReviewsActivity();
        exampleDialog.show(getSupportFragmentManager(),"example dialog");

    }

    @Override
    public void applyText(String comment, String rating) {

        int n = comments.length;

        String newCommentsArr[] = new String[n+ 1];
        String newRatingsArr[] = new String[n+ 1];


        for (int i=0;i< n; i++){
            newCommentsArr[i] = comments[i];
            newCommentsArr[n] = comment;

            newRatingsArr[i] = ratings[i];
            newRatingsArr[n] = rating;

        }
        comments = newCommentsArr;
        ratings = newRatingsArr;

        Log.i("newcomment",newCommentsArr[n]);
        Log.i("newrating",newRatingsArr[n]);

        ReviewArrayAdapter reviewAdapter = new ReviewArrayAdapter(GameActivity.this, android.R.layout.simple_list_item_1,comments,ratings);
        mReviewsListView.setAdapter(reviewAdapter);

        Toast.makeText(GameActivity.this, "comment added", Toast.LENGTH_LONG).show();

    }



}
