package com.lamine.mareu.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamine.mareu.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMeetingActivity extends AppCompatActivity {


    @BindView (R.id.list) RecyclerView mRecyclerView;
    @BindView (R.id.add) FloatingActionButton mFloatingActionButton;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_list_meeting);
        ButterKnife.bind (this);

    }
}
