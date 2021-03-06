package com.example.gamersleague.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamersleague.Constants;
import com.example.gamersleague.R;
import com.example.gamersleague.adapters.ReviewsListAdapter;
import com.example.gamersleague.models.Result;
import com.example.gamersleague.models.Reviews;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameDetailFragment extends Fragment implements View.OnClickListener ,ReviewsActivity.ExampleDialogListener{
    @BindView(R.id.gameImageView)
    ImageView mImageLabel;
    @BindView(R.id.gameNameTextView)
    TextView mNameLabel;
    @BindView(R.id.descriptionTextView)
    TextView mDescriptionLabel;
    @BindView(R.id.favouritesTextView)
    TextView mFavouritesLabel;
    @BindView(R.id.giantBombTextView)
    TextView mGiantBomb;
    @BindView(R.id.viewFavTextView)
    TextView mSaveGameButton;
    @BindView(R.id.addCommentButton)
    Button mAddCommentButton;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private DatabaseReference mReviewReference;
    private FirebaseRecyclerAdapter<Reviews, FirebaseReviewViewHolder> mFirebaseAdapter;
    private FirebaseAuth mAuth;
    private Result mResult;
    private ReviewsListAdapter adapterReviews;
    private Reviews mReview;
    List<Reviews> mListReviews = new ArrayList<>();

    public GameDetailFragment(){

    }
    public static GameDetailFragment newInstance(Result result) {
        GameDetailFragment gameDetailFragment= new GameDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("game", Parcels.wrap(result));
        args.putParcelable("position", Parcels.wrap(result.getId()));

        gameDetailFragment.setArguments(args);
        return gameDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mResult = Parcels.unwrap(getArguments().getParcelable("game"));
        Integer  gamePosition = Parcels.unwrap(getArguments().getParcelable("position"));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();


        final ArrayList<Reviews> reviews = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_REVIEWS)
                .child(gamePosition.toString());
        DatabaseReference pushRef = ref.getRef();
        String pushId = pushRef.getKey();

        Log.i("game position",gamePosition.toString());
        ref.child(pushId);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mListReviews.add(snapshot.getValue(Reviews.class));

                    for (int i = 0; i < mListReviews.size(); i++) {

                        Log.i("reviews", mListReviews.get(i).toString());
                    }
                    Log.i("reviews", mListReviews.get(0).toString());
                    adapterReviews = new ReviewsListAdapter(getContext(),mListReviews);
                    mRecyclerView.setAdapter(adapterReviews);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("database", "The read failed: " + error.getCode());
            }
        });

    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mResult.getImage().getScreenUrl()).into(mImageLabel);
        mNameLabel.setText(mResult.getName());
        mDescriptionLabel.setText(mResult.getDeck());
        mGiantBomb.setOnClickListener(this);
        mFavouritesLabel.setOnClickListener(this);
        mSaveGameButton.setOnClickListener(this);
        mAddCommentButton.setOnClickListener(this);
    return view;
    }


    @Override
    public void onClick(View v) {
        if (v == mGiantBomb) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mResult.getSiteDetailUrl()));
            startActivity(webIntent);
        }
        if(v == mFavouritesLabel){


                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .repeat(1)
                        .playOn(mAddCommentButton);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference gamesRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_GAMES)
                    .child(uid);

            DatabaseReference pushRef = gamesRef.push();
            String pushId = pushRef.getKey();
            mResult.setPushId(pushId);
            pushRef.setValue(mResult);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

        }
        if (v == mSaveGameButton) {
            Intent intent = new Intent(requireContext(), SavedGamesListActivity.class);

            startActivity(intent);
        }
        if (v == mAddCommentButton) {
            ReviewsActivity exampleDialog = new ReviewsActivity();
            exampleDialog.show(getFragmentManager(),"example dialog");

        }
    }

    @Override
    public void applyText(String comment, String rating) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        String username = user.getDisplayName();

        mReview=new Reviews(comment,rating,uid,username);
        mListReviews.add(mReview);

        DatabaseReference reviewsRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_REVIEWS);

        reviewsRef.push().setValue(mReview);

        Toast.makeText(getContext(), "Comment Added", Toast.LENGTH_SHORT).show();
    }
}