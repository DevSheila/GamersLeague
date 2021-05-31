package com.example.gamersleague;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.gamersleague.Constants.GIANT_BOMB_KEY;

public class GamesActivity extends AppCompatActivity {
    @BindView(R.id.usernameTextView)
    TextView mUsernameTextView;
    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

//    private String[] games = new String[]{"Dragon Age:Origins","Left  Dead 2","Uncharted 2:Among theives","Limbo","Mass Effect 2", "Red Dead Redemption","StarCraft||:Wings of Liberty","Super Mario Galaxy 2","Super Meat Boy","Batmman:Arkham City","Dark Souls","The Elder Scrolls V:Skyrim","MineCraft","Portal 2","Dishonoured","Journey"};
//    private String[] gameDetails = new String[]{ "GENRE:Role-playing ,PUBLISHER:Electronic Arts PLATFORM: PC,Play Station 3, Xbox 360","GENRE:fisrt person shooter PUBLISHER: Valve PLATFORM:PC,XBox 360 ","GENRE: Action-Adventure PUBLISHER: Sony Computer Entertainment PLATFORM:Play Station 3","GENRE:Platform PUBLISHER: Microsoft Game Studios PLATFORM:XBOX 360","GENRE: Action role-playing,PUBLISHER: Electronic Arts PLATFORM:PC ,Xbox 360","GENRE: Action-Adventure PUBLISHER:Rockstar games  PLATFORM: PlayStation 3,box 360","GENRE:Real-time  stratedgy PUBLISHER:Blizzard Entertainment  PLATFORM:PC","GENRE:Platform PUBLISHER:Nintendo  PLATFORM:Wii","GENRE:Platform PUBLISHER:Team Meat  PLATFORM:Xbox 360","GENRE: Action-adventure PUBLISHER:Warner Bros.  PLATFORM: Playstatsion 3,Xbox 360","GENRE: Action-role playing PUBLISHER: Namco Bandai Games PLATFORM:Playstaytion 3 ,Xbox 360","GENRE:Action-Role playing PUBLISHER:Bethesda Softworks  PLATFORM:PC,Play Station 3,Xbox 360","GENRE: Snadbox PUBLISHER:Mojang  PLATFORM:PC","GENRE: Puzzle-platformerPUBLISHER:Valve  PLATFORM:PC,Playstatsion 3,Xbox 360","GENRE: Stealth PUBLISHER:Bethesda Softworks PLATFORM:PC,Playstation 3, Xbox 360","GENRE:Adventure PUBLISHER:Sony Computer Entertainment  PLATFORM:PlayStation 3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        ButterKnife.bind(this);

//        ArrayList<String> allGames = new ArrayList<>();
//        allGames.addAll(Arrays.asList(games));

//        GamesArrayAdapter listAdapter = new GamesArrayAdapter(GamesActivity.this, android.R.layout.simple_list_item_1,games);
//        mListView.setAdapter(listAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i("game",games[position]);
//                Toast.makeText(GamesActivity.this, games[position], Toast.LENGTH_LONG).show();
//
//                Intent intent = new Intent(GamesActivity.this, GameActivity.class);
//                intent.putExtra("game", games[position]);
//                intent.putExtra("gameDetails", gameDetails[position]);
//                Log.i("gamede",gameDetails[position]);
//                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        mUsernameTextView.setText("Hello "+username+" .Welcome to Gamers League");

        GiantBombApi client = GiantBombClient.getClient();
        Call<GiantBombGamesResponse> call = client.getResults(GIANT_BOMB_KEY,"json");

        call.enqueue(new Callback<GiantBombGamesResponse>() {

            @Override
            public void onResponse(Call<GiantBombGamesResponse> call, Response<GiantBombGamesResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    List<Result> gamesList = response.body().getResults();
                    String[] games = new String[gamesList.size()];

                    for (int i = 0; i < games.length; i++){
                        games[i] = gamesList.get(i).getName();
                    }

                    GamesArrayAdapter listAdapter = new GamesArrayAdapter(GamesActivity.this, android.R.layout.simple_list_item_1,games);
                    mListView.setAdapter(listAdapter);

                    showGames();
                }
            }

            @Override
            public void onFailure(Call<GiantBombGamesResponse> call, Throwable t) {
                Log.d("msg",t.getMessage());
                hideProgressBar();
                showFailureMessage();
//                mErrorTextView.setText(t.getMessage());

            }
        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showGames() {
        mListView.setVisibility(View.VISIBLE);
        mUsernameTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}