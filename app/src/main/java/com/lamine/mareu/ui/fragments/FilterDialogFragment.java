package com.lamine.mareu.ui.fragments;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AlertDialog;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.lamine.mareu.R;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


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
    private OnButtonClickedListener mCallback;
    //contrat
    public interface OnButtonClickedListener{
        void onButtonClicked(Calendar date, String room, boolean reset);
    }

    //contructor

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
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener () {
            @Override
            public void onClick (DialogInterface dialog, int which) {
                mCallback.onButtonClicked (mDate, mRoomFilter.getEditableText ().toString (), false);
            }
        });

        //Button to cancel the choice "cancel"
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener () {
            @Override
            public void onClick (DialogInterface dialog, int which) {
                dialog.dismiss ();
            }
        });

        //Button to reset "reset"
        builder.setNeutralButton(R.string.reset, new DialogInterface.OnClickListener () {
            @Override
            public void onClick (DialogInterface dialog, int which) {
                mCallback.onButtonClicked (mDate, mRoomFilter.getEditableText ().toString (), true);
            }
        });

        return builder.create ();
    }
}
