package com.lamine.mareu.ui;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lamine.mareu.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

import static com.lamine.mareu.ui.ListMeetingActivity.sApiService;

public class AddMeetingActivity extends AppCompatActivity {

    private Calendar mNow;

    private List<String> mRooms;

    @BindView (R.id.room_name_layout) TextInputLayout mRommNameTextInputLayout;
    @BindView(R.id.room_name)
    AutoCompleteTextView mRoomNameAutoCompleteTextView;

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
    }



    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater ().inflate (R.menu.menu_add_meeting, menu);
        return super.onCreateOptionsMenu (menu);
    }


//To commit................. a changer par une toolbar plutard  +
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_meeting:
                addMeeting ();
                return true;
            case android.R.id.home:
                Toast.makeText (this.getApplicationContext (), R.string.abort_add_meeting, Toast.LENGTH_LONG).show ();
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
    @OnTouch(R.id.date)
    void displayDatePicker() {
         final Calendar calendar = Calendar.getInstance();
        DatePickerDialog mDatePickerDialog;

        mDatePickerDialog = new DatePickerDialog(AddMeetingActivity.this,
                new DatePickerDialog.OnDateSetListener () {
                    @Override
                    public void onDateSet (DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar cal = Calendar.getInstance ();
                        cal.set (year, month, dayOfMonth);
                        mDateTextInputEditText.setText (DateFormat.getDateFormat (AddMeetingActivity.this.getApplicationContext ()).format (cal.getTime ()));
                        if (cal.before (calendar)) {
                            mDateTextInputLayout.setError (AddMeetingActivity.this.getText (R.string.error_date_passed));
                        }
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        mDatePickerDialog.show();
    }



    private void addMeeting(){

        //TO DO
    }

}
