package com.example.sportsdating.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportsdating.FriendsAdapter;
import com.example.sportsdating.MainActivity;
import com.example.sportsdating.MomentPostAdapter;
import com.example.sportsdating.PostActivity;
import com.example.sportsdating.R;
import com.example.sportsdating.user.UserMgr;
import com.example.sportsdating.user.model.User;

import java.util.List;
import java.util.Set;

public class FriendsActivity extends AppCompatActivity {
    private RecyclerView rv_friends;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        //1 RecyclerView
        rv_friends = findViewById(R.id.rv_friends);
        rv_friends.setLayoutManager(new LinearLayoutManager(FriendsActivity.this));
        rv_friends.setAdapter(new FriendsAdapter(FriendsActivity.this,new FriendsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(FriendsActivity.this,"cliked " + pos,Toast.LENGTH_LONG).show();
            }
        }));

        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

}