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

public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.GamesViewHolder> implements Filterable {

    private List<Result> mGames;
    private Context mContext;

    private List<Result> mGamesFull;

    public GamesListAdapter(Context context, List<Result> games) {
        mContext = context;
        mGames = games;
        mGamesFull =new ArrayList<>(games);
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

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private  Filter exampleFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
           List<Result> filteredList = new ArrayList<>();

           if(constraint == null || constraint.length()==0){
               filteredList.addAll(mGamesFull);
           }else{
               String filterPattern = constraint.toString().toLowerCase().trim();

               for(Result item: mGamesFull){
                   //intead of contains below,, you can use starts with & other methods
                   //try filtering with other fields
                   if(item.getName().toLowerCase().contains(filterPattern)){
                       filteredList.add(item);
                   }
               }
           }
            FilterResults results = new FilterResults();
            results.values= filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mGamesFull.clear();
            mGamesFull.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

    public class GamesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.gameImageView)ImageView mGameImageView;
        @BindView(R.id.gameNameTextView)TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;


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

            mRatingTextView.setText("Rating: " + result.getOriginalGameRating() + "/5");
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
