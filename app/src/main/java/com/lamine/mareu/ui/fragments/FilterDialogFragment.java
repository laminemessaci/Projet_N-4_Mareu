package com.lamine.mareu.ui.fragments;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AlertDialog;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.lamine.mareu.R;
import com.lamine.mareu.events.FiltersUpdatesEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;


/**
 * Created by Lamine MESSACI on 06/03/2020.
 */
public class FilterDialogFragment extends DialogFragment {

    @BindView (R.id.date_filter)  TextInputEditText mDateFilter;
    @BindView (R.id.room_filter) AutoCompleteTextView mRoomFilter;

    private List<String> mRooms;
    private Calendar mDate;
    private String mRoom;

    //callback
    //private OnButtonClickedListener mCallback;
    //contrat
    //public interface OnButtonClickedListener{
      //  void onButtonClicked(Calendar date, String room, boolean reset);
    //}

    //constructor

    public FilterDialogFragment (List<String> rooms) {
        mRooms = rooms;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog (@Nullable Bundle savedInstanceState) {

         super.onCreateDialog (savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();

        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.filter, null);

        ButterKnife.bind(this, view);

        mRoomFilter.setAdapter(new ArrayAdapter<> (Objects.requireNonNull(getContext()), R.layout.room_item, mRooms));

        builder.setView(view);
        builder.setTitle(R.string.select_filter);

        //Button to confirm the choice and validation "ok"
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            //mCallback.onButtonClicked (mDate, mRoomFilter.getEditableText ().toString (), false);
            EventBus.getDefault().post(new FiltersUpdatesEvent(mDate, mRoomFilter.getEditableText ().toString (), false));
        });

        //Button to cancel the choice "cancel"
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss ());

        //Button to reset "reset"
        builder.setNeutralButton(R.string.reset, (dialog, which) -> {
            //mCallback.onButtonClicked (mDate, mRoomFilter.getEditableText ().toString (), true);
            EventBus.getDefault().post(new FiltersUpdatesEvent(mDate, mRoomFilter.getEditableText ().toString (),true));
        });

        return builder.create ();
    }

    //private void createCallbackToParentActivity() {
       // mCallback = (OnButtonClickedListener) getActivity();
    //}

    /**
     * OnClick on our date_filter witch display Calendar for getting our date filter
     */

    @OnClick(R.id.date_filter)
    void displayDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog mDatePickerDialog;

        mDatePickerDialog = new DatePickerDialog(Objects.requireNonNull(getContext()),
                (view, year, month, dayOfMonth) -> {
                    Calendar cal = Calendar.getInstance ();
                    cal.set (year, month, dayOfMonth);
                    mDateFilter.setText (DateFormat.getDateFormat (FilterDialogFragment.this.getContext ()).format (cal.getTime ()));
                    mDate = cal;
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        mDatePickerDialog.show();
    }

    /**
     * function OnTouch our room_filter
     * @param v view
     * @param event Action
     * @return true if Action_Down & Action_Up
     */
    @OnTouch(R.id.room_filter)
    boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            mRoomFilter.showDropDown();
            return true;
        }

        return (event.getAction() == MotionEvent.ACTION_UP);
    }

    //in this method we are sure of the attachment so we make a callback
    //@Override
    //public void onAttach(@NonNull Context context) {
    //    super.onAttach(context);

    //    createCallbackToParentActivity();
    //}
}
