package com.example.gamersleague;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamesActivity extends AppCompatActivity {
    @BindView(R.id.usernameTextView)
    TextView mUsernameTextView;
    @BindView(R.id.listView)
    ListView mListView;

    private String[] games = new String[]{"Sweet Hereafter", "Cricket", "Hawthorne Fish House", "Viking Soul Food", "Red Square", "Horse Brass", "Dick's Kitchen", "Taco Bell", "Me Kha Noodle Bar", "La Bonita Taqueria", "Smokehouse Tavern", "Pembiche", "Kay's Bar", "Gnarly Grey", "Slappy Cakes", "Mi Mero Mole"};
    private String[] gameDetails = new String[]{"Vegan Food", "Breakfast", "Fishs Dishs", "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food", "Noodle Soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar", "Breakfast", "Mexican"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        ButterKnife.bind(this);

        ArrayList<String> allGames = new ArrayList<>();
        allGames.addAll(Arrays.asList(games));

        GamesArrayAdapter listAdapter = new GamesArrayAdapter(GamesActivity.this, android.R.layout.simple_list_item_1,games);
        mListView.setAdapter(listAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("game",games[position]);
                Toast.makeText(GamesActivity.this, games[position], Toast.LENGTH_LONG).show();

                Intent intent = new Intent(GamesActivity.this, GameActivity.class);
                intent.putExtra("game", games[position]);
                intent.putExtra("gameDetails", gameDetails[position]);

                Log.i("gamede",gameDetails[position]);

                startActivity(intent);
            }
        });

    }
}