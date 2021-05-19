package com.example.gamersleague;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.submitUsernameButton)
    Button mUsernameButton;
    @BindView(R.id.usernameEditText)
    EditText mUsernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mUsernameButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == mUsernameButton){
            String username = mUsernameEditText.getText().toString();

            if(username.isEmpty()){
                Log.i("username",username);
                Toast.makeText(MainActivity.this, "Please Enter Username", Toast.LENGTH_LONG).show();
                mUsernameEditText.setError("Please Enter Your Username");
            }else{
                Log.i("username",username);
                Intent intent = new Intent(MainActivity.this, GamesActivity.class);
                intent.putExtra("username", username);
                mUsernameEditText.setText(" ");

                startActivity(intent);


            }

        }


    }
}