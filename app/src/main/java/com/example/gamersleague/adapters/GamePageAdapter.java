package com.example.gamersleague.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gamersleague.models.GiantBombGamesResponse;
import com.example.gamersleague.models.Result;
import com.example.gamersleague.ui.GameDetailFragment;

import java.util.List;

public class GamePageAdapter extends FragmentPagerAdapter {
    private GiantBombGamesResponse giantBombGamesResponse;
    private List<Result> mResults ;

    public GamePageAdapter(@NonNull FragmentManager fm, int behavior, List<Result> results) {
        super(fm, behavior);
        mResults= results;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return GameDetailFragment.newInstance(mResults.get(position));
    }

    @Override
    public int getCount() {
        return mResults.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mResults.get(position).getName();
    }
}
