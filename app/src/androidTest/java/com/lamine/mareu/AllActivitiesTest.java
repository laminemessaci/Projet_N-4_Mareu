package com.lamine.mareu;

import android.app.Activity;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Collection;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import com.lamine.mareu.di.DI;
import com.lamine.mareu.service.MeetingApiServiceException;
import com.lamine.mareu.ui.ListMeetingActivity;

import static androidx.test.runner.lifecycle.Stage.RESUMED;
import static com.lamine.mareu.utils.assertions.RecyclerViewItemCountAssertion.itemCountAssertion;
import static com.lamine.mareu.utils.assertions.TextInputLayoutErrorValueAssertion.matchesErrorText;
import static com.lamine.mareu.utils.dummies.DummyCalendarGenerator.TOMORROW;
import static com.lamine.mareu.utils.dummies.DummyCalendarGenerator.generateDateTimeFromTomorrow;
import static com.lamine.mareu.utils.dummies.DummyCalendarGenerator.generateTomorrowDateTime;
import static com.lamine.mareu.utils.dummies.DummyEmailGenerator.VALID_EMAIL_1;
import static com.lamine.mareu.utils.dummies.DummyMeetingGenerator.ITEMS_COUNT;
import static com.lamine.mareu.utils.dummies.DummyMeetingGenerator.MEETINGS_WITH_ROOM_NAME_1_COUNT;
import static com.lamine.mareu.utils.dummies.DummyMeetingGenerator.ROOM_NAME;
import static com.lamine.mareu.utils.dummies.DummyMeetingGenerator.ROOM_NAME_1;
import static com.lamine.mareu.utils.dummies.DummyMeetingGenerator.TOMORROW_MEETINGS_COUNT;
import static com.lamine.mareu.utils.dummies.DummyMeetingGenerator.TOMORROW_MEETING_WITH_ROOM_NAME_1;
import static com.lamine.mareu.utils.dummies.DummyMeetingGenerator.TOPIC;
import static com.lamine.mareu.utils.dummies.DummyMeetingGenerator.generateMeetings;
import static com.lamine.mareu.utils.dummies.DummyMeetingGenerator.generateRooms;
import static com.lamine.mareu.utils.matchers.ToastMatcher.isToast;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertTrue;


/**
 * Created by Lamine MESSACI on 27/03/2020.
 */
@RunWith(AndroidJUnit4.class)
public class AllActivitiesTest {


    /**
     * Define rule to initialize ListMeetingActivity with test data
     */
    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule<ListMeetingActivity>(ListMeetingActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    try {
                        DI.initializeMeetingApiService(generateRooms(), generateMeetings());
                    } catch (MeetingApiServiceException e) {
                        e.printStackTrace();
                    }
                }
            };

    /**
     * Initialize ListMeetingActivity with test data and check that it is not empty
     */
    @Before
    public void setUp() {
        ListMeetingActivity activity = mActivityRule.getActivity();
        assertThat(activity, notNullValue());
    }

    /**
     * Scenario: book an available room
     */

    /*
    @Test
    public void whenWeReserveAnAvailableRoom_thenItIsValidated() {
        // Init
        Calendar from = generateDateTimeFromTomorrow(3,9,0);
        Calendar to = generateDateTimeFromTomorrow(3,10,0);

        // Click to add meeting
        onView(withId(R.id.add_meeting)).perform(click());

        // Fill in the fields
        // room name
        onView(withId(R.id.room_name)).perform(click());
        onView(withText(ROOM_NAME))
                .inRoot(isPlatformPopup())
                .perform(click());
        // topic
        onView(withId(R.id.topic)).perform(typeText(TOPIC));
        // date
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(
                        from.get(Calendar.YEAR),
                        from.get(Calendar.MONTH) + 1,
                        from.get(Calendar.DAY_OF_MONTH)));
        onView(withText(android.R.string.ok)).perform(click());
        // from (time)
        onView(withId(R.id.from)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(
                        from.get(Calendar.HOUR_OF_DAY),
                        from.get(Calendar.MINUTE)));
        onView(withText(android.R.string.ok)).perform(click());
        // to (time)
        onView(withId(R.id.to)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(
                        to.get(Calendar.HOUR_OF_DAY),
                        to.get(Calendar.MINUTE)));
        onView(withText(android.R.string.ok)).perform(click());
        // email
        onView(withId(R.id.emails)).perform(typeText(VALID_EMAIL_1 + " "));

        // Validate the entry
        onView(withId(R.id.add_meeting)).perform(click());

        // Check that the meeting has been added
        onView(withText(R.string.add_new_meeting))
                .inRoot(isToast())
                .check(matches(isDisplayed()));
        onView(withId(R.id.list))
                .check(itemCountAssertion(ITEMS_COUNT + 1));
    }

     */

    /**
     * Scenario: book an unavailable room
     */
    /*
    @Test
    public void whenWeReserveAnUnavailableRoom_thenItIsRefused() {
        // Init
        ListMeetingActivity activity = mActivityRule.getActivity();
        Calendar from = generateTomorrowDateTime(9,0);
        Calendar to = generateTomorrowDateTime(10,0);

        // Click to add meeting
        onView(withId(R.id.add_meeting)).perform(click());

        // Fill in the fields
        // room name
        onView(withId(R.id.room_name)).perform(click());
        onView(withText(ROOM_NAME))
                .inRoot(isPlatformPopup())
                .perform(click());
        // topic
        onView(withId(R.id.topic)).perform(typeText(TOPIC));
        // date
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(
                        from.get(Calendar.YEAR),
                        from.get(Calendar.MONTH) + 1,
                        from.get(Calendar.DAY_OF_MONTH)));
        onView(withText(android.R.string.ok)).perform(click());
        // from (time)
        onView(withId(R.id.from)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(
                        from.get(Calendar.HOUR_OF_DAY),
                        from.get(Calendar.MINUTE)));
        onView(withText(android.R.string.ok)).perform(click());
        // to (time)
        onView(withId(R.id.to)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(
                        to.get(Calendar.HOUR_OF_DAY),
                        to.get(Calendar.MINUTE)));
        onView(withText(android.R.string.ok)).perform(click());
        // email
        onView(withId(R.id.emails)).perform(typeText(VALID_EMAIL_1 + " "));

        // Validate the entry
        onView(withId(R.id.add_meeting)).perform(click());

        // Check that the addition is refused
        onView(withText(R.string.error_meeting_room_already_booked))
                .inRoot(isToast())
                .check(matches(isDisplayed()));
        onView(withId(R.id.room_name_layout))
                .check(matchesErrorText(activity.getString(R.string.error_meeting_room_already_booked)));
    }

     */

    /**
     * Scenario: abort an booked in progress
     */
    /*
    @Test
    public void whenMakingReservationAndWhenWeClickToCancel_thenItIsAborted() {
        // Click to add meeting
        onView(withId(R.id.add))
                .perform(click());

        Activity currentAddMeetingActivity = getActivityInstance();

        // Abort
        onView(withContentDescription(R.string.abc_action_bar_up_description))
                .perform(click());

        // Check abort
        assertTrue(currentAddMeetingActivity.isFinishing());
        // TODO sometimes the check doesn't work
        onView(withText(R.string.abort_add_meeting))
                    .inRoot(isToast())
                    .check(matches(isDisplayed()));
    }

     */

    private Activity currentActivity = null;

    private Activity getActivityInstance(){
        getInstrumentation().runOnMainSync(() -> {
            Collection<Activity> resumedActivities =
                    ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
            for (Activity activity: resumedActivities){
                currentActivity = activity;
                break;
            }
        });

        return currentActivity;
    }

    /**
     * Check date filter
     */
    @Test
    public void check_date_filter() {
        onView(withId(R.id.list))
                .check(itemCountAssertion(ITEMS_COUNT));

        onView(withId(R.id.filter))
                .perform(click());

        onView(withId(R.id.date_filter))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(
                        TOMORROW.get(Calendar.YEAR),
                        TOMORROW.get(Calendar.MONTH) + 1,
                        TOMORROW.get(Calendar.DAY_OF_MONTH)));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.list))
                .check(itemCountAssertion(TOMORROW_MEETINGS_COUNT));
    }

    /**
     * Check room filter
     */
    @Test
    public void check_room_filter() {
        onView(withId(R.id.list))
                .check(itemCountAssertion(ITEMS_COUNT));

        onView(withId(R.id.filter))
                .perform(click());

        onView(withId(R.id.room_filter))
                .perform(click());

        onData(allOf(is(instanceOf(String.class)), is(ROOM_NAME_1)))
                .inRoot(isPlatformPopup())
                .perform(click());

        onView(withId(android.R.id.button1))
                .perform(click());

        onView(withId(R.id.list))
                .check(itemCountAssertion(MEETINGS_WITH_ROOM_NAME_1_COUNT));
    }

    /**
     * Check date and room filter
     */
    @Test
    public void check_date_and_room_filter() {
        onView(withId(R.id.list))
                .check(itemCountAssertion(ITEMS_COUNT));

        // Room
        onView(withId(R.id.filter))
                .perform(click());

        onView(withId(R.id.room_filter))
                .perform(click());

        onData(allOf(is(instanceOf(String.class)), is(ROOM_NAME_1)))
                .inRoot(isPlatformPopup())
                .perform(click());

        // Date
        onView(withId(R.id.date_filter))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(
                        TOMORROW.get(Calendar.YEAR),
                        TOMORROW.get(Calendar.MONTH) + 1,
                        TOMORROW.get(Calendar.DAY_OF_MONTH)));
        onView(withId(android.R.id.button1)).perform(click());

        // Valid
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.list))
                .check(itemCountAssertion(TOMORROW_MEETING_WITH_ROOM_NAME_1));
    }
}
