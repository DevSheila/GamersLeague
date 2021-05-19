package com.example.gamersleague;

import android.content.Context;
import android.widget.ArrayAdapter;

public class GamesArrayAdapter extends ArrayAdapter {

    private Context mContext;
    private String[] mGames;
    private String[] mGameDetails;


    public GamesArrayAdapter(Context  mContext,int resource,String[] mGames,String[] mGameDetails){
        super(mContext,resource);
        this.mContext = mContext;
        this.mGames = mGames;
        this.mGameDetails = mGameDetails;
    }

    public GamesArrayAdapter(Context  mContext,int resource,String[] mGames){
        super(mContext,resource);
        this.mContext = mContext;
        this.mGames = mGames;

    }

    @Override
    public Object getItem(int position) {
        String games = mGames[position];

        if(mGameDetails == null){
            return String.format("Game Name :%s  \n \nSee More>>>",games);

        }
        String gameDetails = mGameDetails[position];
        return String.format("Game Name: %s .\n Game Details: %s .\n \nSee More >>>", games, gameDetails);


    }

    @Override
    public int getCount() {
        return mGames.length;
    }
}
