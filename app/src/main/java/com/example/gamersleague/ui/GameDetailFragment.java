package com.example.gamersleague.ui;

import android.content.Intent;
import android.icu.util.ULocale;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gamersleague.Constants;
import com.example.gamersleague.R;
import com.example.gamersleague.models.Result;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.gameImageView)
    ImageView mImageLabel;

    @BindView(R.id.gameNameTextView)
    TextView mNameLabel;
    @BindView(R.id.descriptionTextView)
    TextView mDescriptionLabel;
    @BindView(R.id.ratingTextView)
    TextView mRatingLabel;
    @BindView(R.id.favouritesTextView)
    TextView mFavouritesLabel;
    @BindView(R.id.giantBombTextView)
    TextView mGiantBomb;
    @BindView(R.id.saveGameButton)
    Button mSaveGameButton;

    private Result mResult;

    public GameDetailFragment(){

    }
    public static GameDetailFragment newInstance(Result result) {
        GameDetailFragment gameDetailFragment= new GameDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("game", Parcels.wrap(result));
        gameDetailFragment.setArguments(args);
        return gameDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mResult = Parcels.unwrap(getArguments().getParcelable("game"));
    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_game_detail, container, false);
        ButterKnife.bind(this, view);


        Picasso.get().load(mResult.getImage().getScreenUrl()).into(mImageLabel);
        mNameLabel.setText(mResult.getName());
        mDescriptionLabel.setText(mResult.getDeck());
        mRatingLabel.setText(  "/5");
        mGiantBomb.setOnClickListener(this);
        mFavouritesLabel.setOnClickListener(this);
        mSaveGameButton.setOnClickListener(this);


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
//            gamesRef.push().setValue(mResult);
//            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
        if (v == mSaveGameButton) {
            Intent intent = new Intent(requireContext(), SavedGamesListActivity.class);

            startActivity(intent);
        }
    }
}