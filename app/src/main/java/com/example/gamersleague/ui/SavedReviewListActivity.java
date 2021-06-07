package com.example.gamersleague.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamersleague.Constants;
import com.example.gamersleague.R;
import com.example.gamersleague.models.Result;
import com.example.gamersleague.models.Reviews;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedReviewListActivity  extends AppCompatActivity {
    private DatabaseReference mReviewReference;
    private FirebaseRecyclerAdapter<Reviews, FirebaseReviewViewHolder> mFirebaseAdapter;
    private FirebaseAuth mAuth;
    private Result mResult;

    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_game_detail);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        mReviewReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_REVIEWS).child(mResult.getId().toString());


        setUpFirebaseAdapter();
        hideProgressBar();
        showGames();
    }

    private void setUpFirebaseAdapter(){


        FirebaseRecyclerOptions<Reviews> options =
                new FirebaseRecyclerOptions.Builder<Reviews>()
                        .setQuery(mReviewReference, Reviews.class)
                        .build();


        mFirebaseAdapter = new FirebaseRecyclerAdapter<Reviews, FirebaseReviewViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull FirebaseReviewViewHolder firebaseGameViewHolder, int position,@NonNull Reviews review) {
                firebaseGameViewHolder.bindReview(review);
            }

            @NonNull
            @Override
            public FirebaseReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_list_item, parent, false);
                return new FirebaseReviewViewHolder(view);
            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }

    private void showGames() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}

