package com.lamine.mareu.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lamine.mareu.R;
import com.lamine.mareu.events.DeleteMeetingEvent;
import com.lamine.mareu.model.Meeting;
import com.lamine.mareu.view.ItemMeeting;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.lamine.mareu.ui.ListMeetingActivity.sApiService;


/**
 * Created by Lamine MESSACI on 28/02/2020.
 */

public class ItemMeetingRecyclerViewAdapter extends RecyclerView.Adapter<ItemMeeting> {


   private Context mContext;
   private List<Meeting> mMeetings;

    public ItemMeetingRecyclerViewAdapter (Context context, Calendar date, String room) {
       this.mContext = context;
        this.mMeetings = sApiService.getMeetings (date, room);
    }


    @NonNull
    @Override
    public ItemMeeting onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from (parent.getContext ())
                .inflate (R.layout.meeting_item, parent, false);
        return new ItemMeeting (view);
    }

    @Override
    public void onBindViewHolder (@NonNull ItemMeeting holder, int position) {
        final Meeting meeting = mMeetings.get(position);

        @SuppressLint ("SimpleDateFormat")
        String desc = TextUtils.join(" - ", Arrays.asList (
                meeting.getRoomName (),
                DateFormat.getTimeInstance().format(meeting.getStart ().getTime ()),
                meeting.getTopic ()));

        holder.mDescriptionTextView.setText (desc);
        holder.mParticipantsTextView.setText (
                TextUtils.join (", ", meeting.getParticipants ()));

        ((GradientDrawable)holder.mImageView.getBackground ()).setColor(meeting.getColor ());

        //delete Meeting
        holder.mDeleteImageButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {

                EventBus.getDefault ().post (new DeleteMeetingEvent (meeting.getId ()));

            }
        });


    }

    @Override
    public int getItemCount () {
        return mMeetings.size ();
    }
}
