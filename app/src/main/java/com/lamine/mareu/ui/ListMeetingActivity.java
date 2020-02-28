package com.lamine.mareu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamine.mareu.R;
import com.lamine.mareu.di.DI;
import com.lamine.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMeetingActivity extends AppCompatActivity {

    public static  MeetingApiService sApiService;


    @BindView (R.id.list) RecyclerView mRecyclerView;
    @BindView (R.id.add) FloatingActionButton mFloatingActionButton;
    private ItemMeetingRecyclerViewAdapter mItemMeetingRecyclerViewAdapter;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_list_meeting);

        ButterKnife.bind (this);


        sApiService = DI.getApieService ();

        mFloatingActionButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                ListMeetingActivity.this.startActivity (new Intent (ListMeetingActivity.this, AddMeetingActivity.class));
            }
        });


    }

    @Override
    protected void onPostResume () {
        super.onPostResume ();
        init(null, "");
    }



    @Override
    protected void onStart () {
        super.onStart ();
        EventBus.getDefault ().register (this);

    }

    @Override
    protected void onStop () {
        super.onStop ();
    }

    private void init(Calendar date, String room) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager (this));
        mItemMeetingRecyclerViewAdapter = new ItemMeetingRecyclerViewAdapter(getApplicationContext (), date, room);
        mRecyclerView.setAdapter(mItemMeetingRecyclerViewAdapter);


    }
}
