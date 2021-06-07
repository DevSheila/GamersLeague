package com.example.gamersleague.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamersleague.R;
import com.example.gamersleague.models.Result;
import com.example.gamersleague.ui.GamesDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.GamesViewHolder>{

    private List<Result> mGames;
    private Context mContext;


    public GamesListAdapter(Context context, List<Result> games) {
        mContext = context;
        mGames = games;
    }



    @NonNull
    @Override
    public GamesListAdapter.GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_list_item, parent, false);
        GamesViewHolder viewHolder = new GamesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GamesListAdapter.GamesViewHolder holder, int position) {
        holder.bindGame(mGames.get(position));
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }



    public class GamesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.gameImageView)ImageView mGameImageView;
        @BindView(R.id.gameNameTextView)TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
//        @BindView(R.id.ratingTextView) TextView mRatingTextView;
        @BindView(R.id.platformNames) TextView mPlatformsTextView;

        private Context mContext;

        public GamesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindGame(Result result) {
            Picasso.get().load(result.getImage().getOriginalUrl()).into(mGameImageView);
            mNameTextView.setText(result.getName());
            mCategoryTextView.setText(result.getDateAdded());
            for (int i= 0;i<result.getPlatforms().size();i++){
                mPlatformsTextView.append(result.getPlatforms().get(i).getName() + ",");
            }



        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, GamesDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("games", Parcels.wrap(mGames));
            mContext.startActivity(intent);
        }
    }
}
