package com.lamine.mareu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamine.mareu.R;
import com.lamine.mareu.di.DI;
import com.lamine.mareu.events.DeleteMeetingEvent;
import com.lamine.mareu.service.MeetingApiService;
import com.lamine.mareu.service.MeetingApiServiceException;
import com.lamine.mareu.ui.fragments.FilterDialogFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListMeetingActivity extends AppCompatActivity implements FilterDialogFragment.OnButtonClickedListener {

    public static MeetingApiService sApiService;

    @BindView(R.id.list)
    RecyclerView mRecyclerView;
    @BindView(R.id.add)
    FloatingActionButton mFloatingActionButton;
    private ItemMeetingRecyclerViewAdapter mItemMeetingRecyclerViewAdapter;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_list_meeting);

        ButterKnife.bind (this);

        sApiService = DI.getApiService ();

        mFloatingActionButton.setOnClickListener (v ->
                ListMeetingActivity.this.startActivity
                        (new Intent (ListMeetingActivity.this,
                                AddMeetingActivity.class)));

     this.configureToolbar ();

    }

    private void configureToolbar(){
        Toolbar toolbar = (Toolbar) findViewById (R.id.activity_main_toolbar);
        setSupportActionBar (toolbar);

     }

    /**
     * Fragment Filter (room & date)
     */

    private void performeFilter () {

        FilterDialogFragment filterDialogFragment = new FilterDialogFragment (sApiService.getRooms ());
        filterDialogFragment.show (getSupportFragmentManager (), "Filter");
    }


    @Override

    public boolean onOptionsItemSelected (@NonNull MenuItem item) {

        if (item.getItemId () ==R.id.filter){
          performeFilter();
          return true;
        }

       return super.onOptionsItemSelected (item);
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
        return true;
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) throws MeetingApiServiceException {
        sApiService.delMeeting(event.getMeetingId());
        Toast.makeText(getApplicationContext(), "The Meeting has been deleted", Toast.LENGTH_SHORT).show();
        init(null, "");
    }


    private void init(Calendar date, String room) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager (this));
        mItemMeetingRecyclerViewAdapter = new ItemMeetingRecyclerViewAdapter(getApplicationContext (), date, room);
        mRecyclerView.setAdapter(mItemMeetingRecyclerViewAdapter);
    }

    @Override
    public void onButtonClicked (Calendar date, String room, boolean reset) {
        if (reset || date != null || ! room.isEmpty ())
            init (date, room);

    }
}
