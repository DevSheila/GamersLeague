package com.example.gamersleague.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamersleague.Constants;
import com.example.gamersleague.R;
import com.example.gamersleague.models.Result;
import com.example.gamersleague.models.Reviews;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FirebaseReviewViewHolder  extends RecyclerView.ViewHolder {

    View mView;
    Context mContext;
    private Result result;
    List<Result> mListResults;
    public FirebaseReviewViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        Log.i("view", String.valueOf(itemView.getContext()));
    }
    public void bindReview(Reviews review) {
        firebaseAdd();

        Log.i("reviews", review.getComment());
        TextView userNameTextView = (TextView) mView.findViewById(R.id.userName);
        TextView commentTextView = (TextView) mView.findViewById(R.id.userComment);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.userRating);
//
        userNameTextView.setText(review.getUserName());
        commentTextView.setText(review.getComment());
        ratingTextView.setText(review.getRating());

    }
       public void firebaseAdd(){
           final ArrayList<Reviews> reviews = new ArrayList<>();

           FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            String uid = user.getUid();
            int itemPosition = getLayoutPosition();

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_REVIEWS);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        reviews.add(snapshot.getValue(Reviews.class));

                        for (int i = 0; i < reviews.size(); i++) {

                            Log.i("reviews", reviews.get(i).toString());

                        }
                        Log.i("reviews", reviews.get(0).toString());



                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Log.i("database","The read failed: " + error.getCode());
                }
            });


        }



}








