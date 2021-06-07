package com.example.gamersleague.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.gamersleague.Constants;
import com.example.gamersleague.R;
import com.example.gamersleague.adapters.GamePageAdapter;
import com.example.gamersleague.adapters.ReviewsListAdapter;
import com.example.gamersleague.models.GiantBombGamesResponse;
import com.example.gamersleague.models.Result;
import com.example.gamersleague.models.Reviews;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamesDetailActivity extends AppCompatActivity implements ReviewsActivity.ExampleDialogListener{
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private GamePageAdapter adapterViewPager;
    private GiantBombGamesResponse giantBombGamesResponse;

    private Reviews mReview;
    private Result mResult;

    List<Reviews> mReviews;

    List<Result> mResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_detail);
        ButterKnife.bind(this);

        mResults = Parcels.unwrap(getIntent().getParcelableExtra("games"));
        int startingPosition = getIntent().getIntExtra("position",0);


        adapterViewPager = new GamePageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mResults);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

    }

    @Override
    public void applyText(String comment, String rating) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        String username = user.getDisplayName();

        mResults = Parcels.unwrap(getIntent().getParcelableExtra("games"));
        int startingPosition = getIntent().getIntExtra("position",0);

        mReview=new Reviews(comment,rating,uid,username);
//        mListReviews.add(mReview);

        DatabaseReference reviewsRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_REVIEWS)
                .child(mResults.get(startingPosition).getId().toString());
//                .child(uid);
        reviewsRef.push().setValue(mReview);
//        adapterReviews = new ReviewsListAdapter(getContext(), mListReviews);
//        mRecyclerView.setAdapter(adapterReviews);
//        DatabaseReference pushRef = reviewsRef.push();
//        String pushId = pushRef.getKey();
//        mReview.setPushId(pushId);
//        pushRef.setValue(mReview);

        Toast.makeText(GamesDetailActivity.this, "Comment Added", Toast.LENGTH_SHORT).show();
    }

}
