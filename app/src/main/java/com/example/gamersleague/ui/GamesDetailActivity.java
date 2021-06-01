package com.example.gamersleague.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.gamersleague.R;
import com.example.gamersleague.adapters.GamePageAdapter;
import com.example.gamersleague.models.GiantBombGamesResponse;
import com.example.gamersleague.models.Result;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamesDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private GamePageAdapter adapterViewPager;
    private GiantBombGamesResponse giantBombGamesResponse;

    List<Result> mResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_detail);
        ButterKnife.bind(this);

        mResults = Parcels.unwrap(getIntent().getParcelableExtra("games"));
        int startingPosition = getIntent().getIntExtra("position",0);

        adapterViewPager = new GamePageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mResults);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

    }
}
