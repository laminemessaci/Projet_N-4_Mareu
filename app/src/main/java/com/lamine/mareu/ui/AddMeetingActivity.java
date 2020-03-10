package com.lamine.mareu.ui;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lamine.mareu.R;
import com.lamine.mareu.model.Meeting;
import com.lamine.mareu.service.MeetingApiServiceException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

import static com.lamine.mareu.ui.ListMeetingActivity.sApiService;

public class AddMeetingActivity extends AppCompatActivity {

    private Calendar mNow;
    private   boolean mError;
    private List<String> mRooms;

    @BindView(R.id.room_name_layout) TextInputLayout mRoomNameTextInputLayout;
    @BindView(R.id.room_name) AutoCompleteTextView mRoomNameAutoCompleteTextView;

    @BindView(R.id.topic_layout) TextInputLayout mTopicTextInputLayout;
    @BindView(R.id.topic) TextInputEditText mTopicTextInputEditText;

    @BindView(R.id.date_layout) TextInputLayout mDateTextInputLayout;
    @BindView(R.id.date) TextInputEditText mDateTextInputEditText;
    @BindView(R.id.from_layout) TextInputLayout mStartTimeTextInputLayout;
    @BindView(R.id.from) TextInputEditText mStartTimeTextInputEditText;
    @BindView(R.id.to_layout) TextInputLayout mEndTimeTextInputLayout;
    @BindView(R.id.to) TextInputEditText mEndTimeTextInputEditText;

    @BindView(R.id.participants) TextInputLayout mEmailsTextInputLayout;
    @BindView(R.id.emails_group)
    ChipGroup mEmailsChipGroup;
    @BindView(R.id.emails)
    TextInputEditText mEmailsTextInputEditText;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add_meeting);
        ButterKnife.bind (this);

        // Meeting room
        mRooms = sApiService.getRooms();

        // Meeting room Array of rooms
        mRoomNameAutoCompleteTextView.setAdapter(new ArrayAdapter<> (this, R.layout.room_item, mRooms));
        this.configureToolbar ();

    }

    private void configureToolbar(){
        Toolbar toolbar = (Toolbar) findViewById (R.id.activity_main_toolbar);
        setSupportActionBar (toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater ().inflate (R.menu.menu_add_meeting, menu);
        return super.onCreateOptionsMenu (menu);
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_meeting:
                addMeeting ();
                return true;
            case android.R.id.home:
                Toast.makeText (this.getApplicationContext (), R.string.abort_add_meeting, Toast.LENGTH_LONG).show ();
                Intent goHomeIntent = new Intent(this, ListMeetingActivity.class);
                goHomeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goHomeIntent);
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * scrolling menu for choice Room
     * @param v
     * @param event
     * @return true Action_down & Action_up
     */
    @OnTouch(R.id.room_name)
    boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            mRoomNameAutoCompleteTextView.showDropDown();
            return true;
        }
        return (event.getAction() == MotionEvent.ACTION_UP);
    }


    /**
     * this will display the calendar on touching TextInputEditText(date)
     */
    @OnClick(R.id.date)
    void displayDatePicker() {
         final Calendar calendar = Calendar.getInstance();
        DatePickerDialog mDatePickerDialog;

        mDatePickerDialog = new DatePickerDialog(AddMeetingActivity.this,
                (view, year, month, dayOfMonth) -> {
                    Calendar cal = Calendar.getInstance ();
                    cal.set (year, month, dayOfMonth);
                    mDateTextInputEditText.setText (DateFormat.getDateFormat (AddMeetingActivity.this.getApplicationContext ()).format (cal.getTime ()));
                    if (cal.before (calendar)) {
                        mDateTextInputLayout.setError (AddMeetingActivity.this.getText (R.string.error_date_passed));
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        mDatePickerDialog.show();
    }

    @OnClick({R.id.from, R.id.to})
    void displayTimePicker(View v) {
        final int id = v.getId();

        Calendar time = Calendar.getInstance();
        TimePickerDialog mTimePickerDialog;

        int roundedMinutes = time.get(Calendar.MINUTE) % 15;
        time.add(Calendar.MINUTE, roundedMinutes > 0 ? (15 - roundedMinutes) : 0);

        mTimePickerDialog = new TimePickerDialog(AddMeetingActivity.this,
                (view, hourOfDay, minute) -> {
                    Calendar tim = Calendar.getInstance();
                    tim.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    tim.set(Calendar.MINUTE, minute);
                    if (id == R.id.from)
                        mStartTimeTextInputEditText.setText(DateFormat.getTimeFormat(getApplicationContext()).format(tim.getTime()));
                    else if (id == R.id.to)
                        mEndTimeTextInputEditText.setText(DateFormat.getTimeFormat(getApplicationContext()).format(tim.getTime()));
                },
                time.get(Calendar.HOUR_OF_DAY),
                time.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(AddMeetingActivity.this));

        mTimePickerDialog.show();
    }

    /*
    ** Verification of user input and Add Meeting if all entries are OK
     */
    private void addMeeting(){
        String roomName = validateTextInput(mRoomNameTextInputLayout);
        String topic = validateTextInput(mTopicTextInputLayout);
        Calendar date = validateDateInput(mDateTextInputLayout);
        Calendar start = validateTimeInput(mStartTimeTextInputLayout);
        Calendar end = validateTimeInput(mEndTimeTextInputLayout);
        List<String> participants = validateEmailInput(mEmailsTextInputLayout, mEmailsChipGroup);

        if (start != null && end != null) {
            if (end.before(start)) {
                mEndTimeTextInputLayout.setError(getText(R.string.error_time_comparaison));
                mError = true;
            }
        }

        if (date != null && start != null) {
            start.set(Calendar.YEAR, date.get(Calendar.YEAR));
            start.set(Calendar.MONTH, date.get(Calendar.MONTH));
            start.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));

            if (start.before(mNow)) {
                mStartTimeTextInputLayout.setError(getText(R.string.error_time_passed));
                mError = true;
            }
        }

        if (date != null && end != null) {
            end.set(Calendar.YEAR, date.get(Calendar.YEAR));
            end.set(Calendar.MONTH, date.get(Calendar.MONTH));
            end.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
        }

        if (mError) {
            Toast.makeText(this.getApplicationContext(), R.string.error_add_new_meeting, Toast.LENGTH_LONG).show();
            mError = false;
        } else {
            try {
                sApiService.addMeeting(new Meeting(roomName, start, end, topic, participants));

                Toast.makeText(this.getApplicationContext(), R.string.add_new_meeting, Toast.LENGTH_LONG).show();

                finish();
            } catch (MeetingApiServiceException e) {
                mRoomNameTextInputLayout.setError(getText(R.string.error_meeting_room_already_booked));
                Toast.makeText(this.getApplicationContext(), R.string.error_meeting_room_already_booked, Toast.LENGTH_LONG).show();
                mError = false;
            }
        }

    };

    private String validateTextInput(TextInputLayout inputValue) {
        String tmpValue = Objects.requireNonNull(inputValue.getEditText()).getText().toString().trim();

        if (tmpValue.isEmpty()) {
            inputValue.setError(getText(R.string.error_empty_field));
            mError = true;
            return null;
        } else {
            inputValue.setError(null);
            return tmpValue;
        }
    }

    private Calendar validateDateInput(TextInputLayout inputValue) {
        String tmpValue = Objects.requireNonNull(inputValue.getEditText()).getText().toString().trim();

        if (tmpValue.isEmpty()) {
            inputValue.setError(getText(R.string.error_empty_field));
            mError = true;
            return null;
        } else {
            // valid date format ?
            try {
                Date dDate = DateFormat.getDateFormat(getApplicationContext()).parse(tmpValue);

                Calendar now = Calendar.getInstance();
                now.set(Calendar.HOUR_OF_DAY, 0);
                now.set(Calendar.MINUTE, 0);
                now.set(Calendar.SECOND, 0);
                now.set(Calendar.MILLISECOND, 0);

                Calendar date = (Calendar) now.clone();
                date.setTime(Objects.requireNonNull(dDate));

                if (date.before(now)) {
                    inputValue.setError(getText(R.string.error_date_passed));
                    mError = true;
                    return null;
                }

                inputValue.setError(null);

                return date;
            } catch (ParseException e) {
                inputValue.setError(getText(R.string.error_invalid_date_format));
                mError = true;
                return null;
            }
        }
    }

    private Calendar validateTimeInput(TextInputLayout inputValue) {
        String tmpValue = Objects.requireNonNull(inputValue.getEditText()).getText().toString().trim();

        if (tmpValue.isEmpty()) {
            inputValue.setError(getText(R.string.error_empty_field));
            mError = true;
            return null;
        } else {
            // valid time format ?
            try {
                Date dTime = android.text.format.DateFormat.getTimeFormat(getApplicationContext()).parse(tmpValue);
                Calendar time = Calendar.getInstance();
                time.setTime(Objects.requireNonNull(dTime));

                time.set(Calendar.YEAR, mNow.get(Calendar.YEAR));
                time.set(Calendar.MONTH, mNow.get(Calendar.MONTH));
                time.set(Calendar.DAY_OF_MONTH, mNow.get(Calendar.DAY_OF_MONTH));

                inputValue.setError(null);

                return time;
            } catch (ParseException e) {
                inputValue.setError(getText(R.string.error_invalid_time_format));
                mError = true;
                return null;
            }
        }
    }

    private List<String> validateEmailInput(TextInputLayout inputValue, ChipGroup emails) {
        inputValue.setError(null);
        int nb = emails.getChildCount();
        List<String> lEmails = new ArrayList<>();

        if (nb == 0) {
            inputValue.setError(getText(R.string.error_empty_field));
            mError = true;
            return null;
        } else {
            for (int i = 0; i < nb; i++) {
                Chip tmpEmail = (Chip) emails.getChildAt(i);
                String email = tmpEmail.getText().toString();

                lEmails.add(email);
            }
            return lEmails;
        }
    }
}
