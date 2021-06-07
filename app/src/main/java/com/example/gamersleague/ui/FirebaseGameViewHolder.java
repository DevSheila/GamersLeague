package com.example.gamersleague.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamersleague.Constants;
import com.example.gamersleague.R;
import com.example.gamersleague.models.Result;
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

import butterknife.BindView;

public class FirebaseGameViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseGameViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindGame(Result mResult) {
        ImageView gameImageView = (ImageView) mView.findViewById(R.id.gameImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.gameNameTextView);
        TextView descriptionTextView = (TextView) mView.findViewById(R.id.descriptionTextView);
//        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView platformsTextView = (TextView) mView.findViewById(R.id.platformNames);


        Picasso.get().load(mResult.getImage().getScreenUrl()).into(gameImageView);
        nameTextView.setText(mResult.getName());
        categoryTextView.setText(mResult.getDateAdded());
        for (int i= 0;i<mResult.getPlatforms().size();i++){
            platformsTextView.append(mResult.getPlatforms().get(i).getName() + ",");
        }
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Result> games = new ArrayList<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_GAMES).child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                   games.add(snapshot.getValue(Result.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, GamesDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("restaurants", Parcels.wrap(games));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
}
}








