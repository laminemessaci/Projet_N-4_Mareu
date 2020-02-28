package com.lamine.mareu.ui;

import android.content.Context;
import android.view.ViewGroup;

import com.lamine.mareu.model.Meeting;
import com.lamine.mareu.view.ItemMeeting;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Lamine MESSACI on 28/02/2020.
 */
public class ItemMeetingRecyclerViewApdater extends RecyclerView.Adapter<ItemMeeting> {


   private Context mContext;
   private List<Meeting> mMeetings;





    @NonNull
    @Override
    public ItemMeeting onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder (@NonNull ItemMeeting holder, int position) {

    }

    @Override
    public int getItemCount () {
        return 0;
    }
}
