package com.example.gamersleague;

import android.content.Context;
import android.widget.ArrayAdapter;

public class ReviewArrayAdapter extends ArrayAdapter {

    private Context mContext;
    private String[] mComment;
    private String[] mRating;


    public ReviewArrayAdapter(Context  mContext,int resource,String[] mComment,String[] mRating){
        super(mContext,resource);
        this.mContext = mContext;
        this.mComment = mComment;
        this.mRating = mRating;
    }


    @Override
    public Object getItem(int position) {
        String comment = mComment[position];
        String rating = mRating[position];
        return String.format(" %s \n \n Rating: %s", comment, rating);


    }

    @Override
    public int getCount() {
        return mComment.length;
    }
}
