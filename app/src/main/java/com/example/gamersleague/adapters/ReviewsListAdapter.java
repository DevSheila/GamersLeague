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
import com.example.gamersleague.models.Reviews;
import com.example.gamersleague.ui.GamesDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsListAdapter extends RecyclerView.Adapter<ReviewsListAdapter.ReviewsViewHolder>{

    private List<Reviews> mReviews;
    private Context mContext;




    public ReviewsListAdapter(Context context, List<Reviews> reviews) {
        mContext = context;
        mReviews = reviews;
    }



    @NonNull
    @Override
    public ReviewsListAdapter.ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_list_item, parent, false);
        ReviewsViewHolder viewHolder = new ReviewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsListAdapter.ReviewsViewHolder holder, int position) {
        holder.bindReview(mReviews.get(position));
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }



    public class ReviewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.userName)TextView mUsernameTextView;
        @BindView(R.id.userComment) TextView mCommentTextView;
        @BindView(R.id.userRating) TextView mRatingTextView;

        private Context mContext;

        public ReviewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindReview(Reviews review) {
           mUsernameTextView.setText(review.getUserName());
            mCommentTextView.setText(review.getComment());
            mRatingTextView.setText(review.getRating());



        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, GamesDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("reviews", Parcels.wrap(mReviews));
            mContext.startActivity(intent);
        }
    }
}
