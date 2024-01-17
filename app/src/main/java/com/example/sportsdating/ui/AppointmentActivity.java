package com.example.sportsdating.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.sportsdating.AppointmentAdapter;
import com.example.sportsdating.FriendsAdapter;
import com.example.sportsdating.R;

public class AppointmentActivity extends AppCompatActivity {
    private RecyclerView rv_appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        rv_appointment = findViewById(R.id.rv_appointment);

        rv_appointment.setLayoutManager(new LinearLayoutManager(AppointmentActivity.this));

        rv_appointment.setAdapter(new AppointmentAdapter(AppointmentActivity.this, new AppointmentAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Toast.makeText(AppointmentActivity.this,"cliked " + pos,Toast.LENGTH_LONG).show();
            }
        }));

    }
}