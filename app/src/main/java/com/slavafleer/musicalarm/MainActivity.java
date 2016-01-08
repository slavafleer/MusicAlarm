package com.slavafleer.musicalarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewTones;
    private ArrayList<Tone> mTones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerViewTones = (RecyclerView) findViewById(R.id.recyclerViewTones);

        initTones();

        mRecyclerViewTones.setLayoutManager(new LinearLayoutManager(this));

        ToneAdapter toneAdapter = new ToneAdapter(this, mTones);
        mRecyclerViewTones.setAdapter(toneAdapter);
    }

    private void initTones() {

        mTones.add(new Tone(1, "1st Tone"));
        mTones.add(new Tone(2, "2nd Tone"));
        mTones.add(new Tone(3, "3rd Tone"));
        mTones.add(new Tone(4, "4th Tone"));
        mTones.add(new Tone(5, "5th Tone"));
        mTones.add(new Tone(6, "6th Tone"));
        mTones.add(new Tone(7, "7th Tone"));
        mTones.add(new Tone(8, "8th Tone"));
        mTones.add(new Tone(9, "9th Tone"));
    }

    // Start Service for playing selected music on alarm time.
    public void buttonSetTAlarmTime_onClick(View view) {
    }

    // Stop Service.
    public void buttonCancelAlarm_onClick(View view) {
    }
}
