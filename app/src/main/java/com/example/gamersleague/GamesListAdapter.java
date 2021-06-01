package com.example.gamersleague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamersleague.models.Result;

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

    public class GamesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gameImageView)ImageView mRestaurantImageView;
        @BindView(R.id.gameNameTextView)TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public GamesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindGame(Result result) {
            mNameTextView.setText(result.getName());
            mCategoryTextView.setText(result.getDateAdded());
            mRatingTextView.setText("Rating: " + result.getOriginalGameRating() + "/5");
        }
    }
}
