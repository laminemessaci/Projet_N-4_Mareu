package com.lamine.mareu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamine.mareu.R;
import com.lamine.mareu.di.DI;
import com.lamine.mareu.events.DeleteMeetingEvent;
import com.lamine.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

import androidx.annotation.NonNull;
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


        sApiService = DI.getApiService ();

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
        EventBus.getDefault ().unregister (this);
        super.onStop ();
    }


    /**
     * display menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_filter, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {

        if (item.getItemId () ==R.id.filter){
            // TO DO
            return true;
        }

        return super.onOptionsItemSelected (item);
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {


    }


    private void init(Calendar date, String room) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager (this));
        mItemMeetingRecyclerViewAdapter = new ItemMeetingRecyclerViewAdapter(getApplicationContext (), date, room);
        mRecyclerView.setAdapter(mItemMeetingRecyclerViewAdapter);
    }



}
