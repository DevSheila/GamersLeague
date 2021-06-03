package com.example.gamersleague.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamersleague.adapters.GamesListAdapter;
import com.example.gamersleague.network.GiantBombApi;
import com.example.gamersleague.network.GiantBombClient;
import com.example.gamersleague.R;
import com.example.gamersleague.models.GiantBombGamesResponse;
import com.example.gamersleague.models.Result;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.gamersleague.Constants.GIANT_BOMB_KEY;

public class GamesActivity extends AppCompatActivity {
    @BindView(R.id.usernameTextView)TextView mUsernameTextView;
    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    private GamesListAdapter mAdapter;
    public List<Result> games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        ButterKnife.bind(this);




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
                    games= response.body().getResults();
                    mAdapter = new GamesListAdapter(GamesActivity.this, games);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(GamesActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);
                    showGames();
                }else{
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<GiantBombGamesResponse> call, Throwable t) {
                Log.d("msg",t.getMessage());
                hideProgressBar();
                showFailureMessage();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView)searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(GamesActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
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
        mRecyclerView.setVisibility(View.VISIBLE);
        mUsernameTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}