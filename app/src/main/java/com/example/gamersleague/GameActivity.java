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



public class GameActivity extends AppCompatActivity implements View.OnClickListener{


    @BindView(R.id.gameDetailsTextView)
    TextView mgameDetailsTextView;
    @BindView(R.id.reviewslistView)
    ListView mReviewsListView;
    @BindView(R.id.addCommentButton)
    Button maddCommentButton;


    private String[] comments = new String[]{"Sweet Hereafter", "Cricket", "Hawthorne Fish House", "Viking Soul Food"};
    private String[] ratings = new String[]{"Vegan Food", "Breakfast", "Fishs Dishs", "Scandinavian"};

    ArrayList<String> allComments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);
        allComments.addAll(Arrays.asList(comments));
    }

    @Override
    public void onClick(View v) {

    }
}