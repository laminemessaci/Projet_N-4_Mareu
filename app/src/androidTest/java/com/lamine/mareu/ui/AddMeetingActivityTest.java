package com.lamine.mareu.ui;

import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;

import com.lamine.mareu.R;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.lamine.mareu.utils.actions.ClickCloseIconChipAction.clickOnCloseIconChip;
import static com.lamine.mareu.utils.assertions.ChipGroupNoValueAssertion.matchesChipGroupHasNoChip;
import static com.lamine.mareu.utils.assertions.ChipValueAssertion.matchesChipTextAtPosition;
import static com.lamine.mareu.utils.assertions.TextInputLayoutErrorValueAssertion.matchesErrorText;
import static com.lamine.mareu.utils.assertions.TextInputLayoutNoErrorValueAssertion.matchesNoErrorText;

import static com.lamine.mareu.utils.dummies.DummyCalendarGenerator.TODAY_INVALID_START_TIME;
import static com.lamine.mareu.utils.dummies.DummyCalendarGenerator.TOMORROW_INVALID_END_TIME;
import static com.lamine.mareu.utils.dummies.DummyCalendarGenerator.TOMORROW_VALID_END_TIME;
import static com.lamine.mareu.utils.dummies.DummyCalendarGenerator.TOMORROW_VALID_START_TIME;
import static com.lamine.mareu.utils.dummies.DummyCalendarGenerator.YESTERDAY;
import static com.lamine.mareu.utils.dummies.DummyEmailGenerator.INVALID_EMAIL;
import static com.lamine.mareu.utils.dummies.DummyEmailGenerator.VALID_EMAIL_1;
import static com.lamine.mareu.utils.dummies.DummyEmailGenerator.VALID_EMAIL_2;
import static com.lamine.mareu.utils.dummies.DummyMeetingGenerator.ROOM_NAME;
import static com.lamine.mareu.utils.dummies.DummyMeetingGenerator.TOPIC;
import static com.lamine.mareu.utils.matchers.ToastMatcher.isToast;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;
/**
 * Created by Lamine MESSACI on 27/03/2020.
 */

@RunWith(Enclosed.class)
public class AddMeetingActivityTest {

    public static class ComponentSingleTests {

        /**
         * Define rules to initialize AddMeetingActivity
         */
        @Rule
        public ActivityTestRule<ListMeetingActivity> mActivityRuleInit =
                new ActivityTestRule<>(ListMeetingActivity.class);

        @Rule
        public ActivityTestRule<AddMeetingActivity> mActivityRule =
                new ActivityTestRule<>(AddMeetingActivity.class);

        /**
         * Initialize AddMeetingActivity and check that it is not empty
         */
        @Before
        public void setUp() {
            AddMeetingActivity activity = mActivityRule.getActivity();
            assertThat(activity, notNullValue());
        }

        /**
         * Check selected entry of the Room name field
         */
        @Test
        public void givenRoom_whenClickToAddMeeting_thenGetRoomWithoutError() {
            onView(withId(R.id.room_name)).perform(click());
            onView(withText(ROOM_NAME))
                    .inRoot(RootMatchers.isPlatformPopup())
                    .perform(click());

            onView(withId(R.id.add_meeting)).perform(click());
            onView(withId(R.id.room_name)).check(matches(withText(ROOM_NAME)));
            onView(withId(R.id.room_name_layout)).check(matchesNoErrorText());
        }

        /**
         * Check the correct entry of the Topic field
         */
        @Test
        public void givenTopic_whenClickToAddMeeting_thenGetTopicWithoutError() {
            onView(withId(R.id.topic)).perform(typeText(TOPIC));
            onView(withId(R.id.add_meeting)).perform(click());

            onView(withId(R.id.topic)).check(matches(withText(TOPIC)));
            onView(withId(R.id.topic)).check(matches(withHint(R.string.meeting_topic)));
            onView(withId(R.id.topic_layout)).check(matchesNoErrorText());
        }

        /**
         * Check the correct entry of the Emails field (press Enter key)
         */
        @Test
        public void givenValidEmail_whenPressEnterKey_thenGetEmailWithoutError() {
            onView(withId(R.id.emails)).perform(typeText(VALID_EMAIL_1));
            onView(withId(R.id.emails)).perform(pressKey(KeyEvent.KEYCODE_ENTER));

            onView(withId(R.id.emails_group)).check(matchesChipTextAtPosition(1, VALID_EMAIL_1));
            onView(withId(R.id.emails)).check(matches(withHint(R.string.list_of_participants)));
            onView(withId(R.id.participants)).check(matchesNoErrorText());
        }

        /**
         * Check the correct entries of the Emails field (press Enter key)
         */
        @Test
        public void givenTwoValidEmail_whenPressEnterKey_thenGetEmailsWithoutError() {
            // Initialize test -->
            // add chip
            onView(withId(R.id.emails)).perform(typeText(VALID_EMAIL_1));
            onView(withId(R.id.emails)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
            // confirm that the first chip is present
            onView(withId(R.id.emails_group)).check(matchesChipTextAtPosition(1, VALID_EMAIL_1));
            // Initialize test <--

            // Test -->
            onView(withId(R.id.emails)).perform(typeText(VALID_EMAIL_2));
            onView(withId(R.id.emails)).perform(pressKey(KeyEvent.KEYCODE_ENTER));

            onView(withId(R.id.emails_group)).check(matchesChipTextAtPosition(2, VALID_EMAIL_2));
            onView(withId(R.id.emails)).check(matches(withHint(R.string.list_of_participants)));
            onView(withId(R.id.participants)).check(matchesNoErrorText());
            // Test <--
        }

        /**
         * Check invalid entry of the Emails field (press Enter key)
         */
        @Test
        public void givenInvalidEmail_whenPressEnterKey_thenGetErrorMessage() {
            AddMeetingActivity activity = mActivityRule.getActivity();

            onView(withId(R.id.emails)).perform(typeText(INVALID_EMAIL));
            onView(withId(R.id.emails)).perform(pressKey(KeyEvent.KEYCODE_ENTER));

            onView(withId(R.id.participants))
                    .check(matchesErrorText(activity.getString(R.string.error_invalid_email)));
            onView(withId(R.id.emails)).check(matches(withHint(R.string.list_of_participants)));
        }

        /**
         * Check invalid second entry of the Emails field (press Enter key)
         */
        @Test
        public void givenOneValidEmailAndOneInvalidEmail_whenPressEnterKey_thenGetMessageError() {
            // Initialize test -->
            AddMeetingActivity activity = mActivityRule.getActivity();
            // add chip
            onView(withId(R.id.emails)).perform(typeText(VALID_EMAIL_1));
            onView(withId(R.id.emails)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
            // confirm that the first chip is present
            onView(withId(R.id.emails_group)).check(matchesChipTextAtPosition(1, VALID_EMAIL_1));
            // Initialize test <--

            // Test -->
            onView(withId(R.id.emails)).perform(typeText(INVALID_EMAIL));
            onView(withId(R.id.emails)).perform(pressKey(KeyEvent.KEYCODE_ENTER));

            onView(withId(R.id.participants))
                    .check(matchesErrorText(activity.getString(R.string.error_invalid_email)));
            onView(withId(R.id.emails)).check(matches(withHint(R.string.list_of_participants)));
            // Test <--
        }

        /**
         * Check delete email from email list
         */
        @Test
        public void givenEmailCHip_whenClickToCloseIconChip_thenDeleteChip() {
            // Initialize test -->
            // add chip
            onView(withId(R.id.emails)).perform(typeText(VALID_EMAIL_1));
            onView(withId(R.id.emails)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
            // confirm that the chip is present
            onView(withId(R.id.emails_group)).check(matchesChipTextAtPosition(1, VALID_EMAIL_1));
            onView(withId(R.id.emails)).check(matches(withHint(R.string.list_of_participants)));
            onView(withId(R.id.participants)).check(matchesNoErrorText());
            // Initialize test <--

            // Test -->
            onView(withId(R.id.emails_group)).perform(clickOnCloseIconChip(1));

            onView(withId(R.id.emails_group)).check(matchesChipGroupHasNoChip());
            // Test <--
        }

        /**
         * Check valid date
         */
        @Test
        public void givenValidDate_whenClickToDatePicker_ThenGetValidDateStringFormat() {
            AddMeetingActivity activity = mActivityRule.getActivity();

            onView(withId(R.id.date)).perform(click());
            onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                    .perform(PickerActions.setDate(
                            TOMORROW_VALID_START_TIME.get(Calendar.YEAR),
                            TOMORROW_VALID_START_TIME.get(Calendar.MONTH) + 1,
                            TOMORROW_VALID_START_TIME.get(Calendar.DAY_OF_MONTH)));
            onView(withText(android.R.string.ok)).perform(click());

            onView(withId(R.id.add_meeting)).perform(click());
            onView(withId(R.id.date))
                    .check(matches(withText(DateFormat.getDateFormat(activity.getApplicationContext())
                            .format(TOMORROW_VALID_START_TIME.getTime()))));
            onView(withId(R.id.date_layout)).check(matchesNoErrorText());
        }

        /**
         * Check invalid date (passed)
         */
        @Test
        public void givenInvalidDate_whenClickToDatePicker_ThenGetErrorMessage() {
            AddMeetingActivity activity = mActivityRule.getActivity();

            onView(withId(R.id.date)).perform(click());
            onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                    .perform(PickerActions.setDate(
                            YESTERDAY.get(Calendar.YEAR),
                            YESTERDAY.get(Calendar.MONTH) + 1,
                            YESTERDAY.get(Calendar.DAY_OF_MONTH)));

            onView(withText(android.R.string.ok)).perform(click());

            onView(withId(R.id.add_meeting)).perform(click());

            onView(withId(R.id.date_layout))
                    .check(matchesErrorText(activity.getString(R.string.error_date_passed)));
            onView(withId(R.id.date)).check(matches(withHint(R.string.date)));
        }

        /**
         * Check valid time fields ('from' and 'to')
         */

        /*
        @Test
        public void givenValidTime_whenClickToTimePicker_ThenGetValidTimeStringFormat() {
            AddMeetingActivity activity = mActivityRule.getActivity();

            onView(withId(R.id.from)).perform(click());
            onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                    .perform(PickerActions.setTime(
                            TOMORROW_VALID_START_TIME.get(Calendar.HOUR_OF_DAY),
                            TOMORROW_VALID_START_TIME.get(Calendar.MINUTE)));
            onView(withText(android.R.string.ok)).perform(click());

            onView(withId(R.id.to)).perform(click());
            onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                    .perform(PickerActions.setTime(
                            TOMORROW_VALID_END_TIME.get(Calendar.HOUR_OF_DAY),
                            TOMORROW_VALID_END_TIME.get(Calendar.MINUTE)));
            onView(withText(android.R.string.ok)).perform(click());

            onView(withId(R.id.add_meeting)).perform(click());

            onView(withId(R.id.from))
                    .check(matches(withText(DateFormat
                            .getTimeFormat(activity.getApplicationContext())
                            .format(TOMORROW_VALID_START_TIME.getTime()))));
            onView(withId(R.id.from_layout)).check(matchesNoErrorText());

            onView(withId(R.id.to))
                    .check(matches(withText(DateFormat
                            .getTimeFormat(activity.getApplicationContext())
                            .format(TOMORROW_VALID_END_TIME.getTime()))));
            onView(withId(R.id.to_layout)).check(matchesNoErrorText());
        }

         */

        /**
         * Check invalid start time (passed)
         */
        /*
        @Test
        public void givenInvalidStartTime_whenClickToTimePicker_ThenGetErrorMessage() {
            AddMeetingActivity activity = mActivityRule.getActivity();

            onView(withId(R.id.date)).perform(click());
            onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                    .perform(PickerActions.setDate(
                            TODAY_INVALID_START_TIME.get(Calendar.YEAR),
                            TODAY_INVALID_START_TIME.get(Calendar.MONTH) + 1,
                            TODAY_INVALID_START_TIME.get(Calendar.DAY_OF_MONTH)));
            onView(withText(android.R.string.ok)).perform(click());

            onView(withId(R.id.from)).perform(click());
            onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                    .perform(PickerActions.setTime(
                            TODAY_INVALID_START_TIME.get(Calendar.HOUR_OF_DAY),
                            TODAY_INVALID_START_TIME.get(Calendar.MINUTE)));
            onView(withText(android.R.string.ok)).perform(click());

            onView(withId(R.id.add_meeting)).perform(click());

            onView(withId(R.id.from_layout))
                    .check(matchesErrorText(activity.getString(R.string.error_time_passed)));
            onView(withId(R.id.from)).check(matches(withHint(R.string.from)));
        }

         */

        /**
         * Check invalid end time (earlier than start time)
         */
        /*
        @Test
        public void givenInvalidEndTime_whenClickToTimePicker_ThenGetErrorMessage() {
            AddMeetingActivity activity = mActivityRule.getActivity();

            onView(withId(R.id.from)).perform(click());
            onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                    .perform(PickerActions.setTime(
                            TOMORROW_VALID_START_TIME.get(Calendar.HOUR_OF_DAY),
                            TOMORROW_VALID_START_TIME.get(Calendar.MINUTE)));
            onView(withText(android.R.string.ok)).perform(click());

            onView(withId(R.id.to)).perform(click());
            onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                    .perform(PickerActions.setTime(
                            TOMORROW_INVALID_END_TIME.get(Calendar.HOUR_OF_DAY),
                            TOMORROW_INVALID_END_TIME.get(Calendar.MINUTE)));
            onView(withText(android.R.string.ok)).perform(click());

            onView(withId(R.id.add_meeting)).perform(click());

            onView(withId(R.id.to_layout))
                    .check(matchesErrorText(activity.getString(R.string.error_time_comparaison)));
            onView(withId(R.id.to)).check(matches(withHint(R.string.to)));
        }


        @Test
        public void whenPerformClickToReturnActionBar_thenAbortAddMeetingActivity() {
            onView(withContentDescription(R.string.abc_action_bar_up_description))
                    .perform(click());

            assertTrue(mActivityRule.getActivity().isFinishing());
           // TODO sometimes the check doesn't work
           onView(withText(R.string.abort_add_meeting))
                   .inRoot(isToast())
                    .check(matches(isDisplayed()));
        }

         */
    }

    @RunWith(Parameterized.class)
    public static class ComponentParamTestsCheckEmptyFields {

        /**
         * Test data
         * @return list of field identifiers to check
         */
        @Parameters
        public static Collection<Object> data() {
            return Arrays.asList(new Object[] {
                    R.id.room_name_layout,
                    R.id.topic_layout,
                    R.id.date_layout,
                    R.id.to_layout,
                    R.id.from_layout,
                    R.id.participants
            });
        }

        @Parameterized.Parameter
        public int viewId;

        /**
         * Define rules to initialize AddMeetingActivity
         */
        @Rule
        public ActivityTestRule<ListMeetingActivity> mActivityRuleInit =
                new ActivityTestRule<>(ListMeetingActivity.class);

        @Rule
        public ActivityTestRule<AddMeetingActivity> mActivityRule =
                new ActivityTestRule<>(AddMeetingActivity.class);

        /**
         * Initialize AddMeetingActivity and check that it is not empty
         */
        @Before
        public void setUp() {
            AddMeetingActivity activity = mActivityRule.getActivity();
            assertThat(activity, notNullValue());
        }

        /**
         * Check that the unfilled fields display a valid error message
         */
        @Test
        public void givenNoValue_whenClickToAddMeeting_thenGetErrorMessage() {
            AddMeetingActivity activity = mActivityRule.getActivity();

            onView(withId(R.id.add_meeting)).perform(click());
            onView(withId(viewId))
                    .check(matchesErrorText(activity.getString(R.string.error_empty_field)));

            onView(withText(R.string.error_add_new_meeting))
                    .inRoot(isToast())
                    .check(matches(isDisplayed()));
        }
    }

    @RunWith(Parameterized.class)
    public static class ComponentParamTestsCheckEmailsField {

        /**
         * Test data
         *
         * @return list of internal field delimiter to check
         */
        @Parameters
        public static Collection<Object> data() {
            return Arrays.asList(new Object[]{" ", ","});
        }

        @Parameterized.Parameter
        public String internalFieldDelimiter;

        /**
         * Define rules to initialize AddMeetingActivity
         */
        @Rule
        public ActivityTestRule<ListMeetingActivity> mActivityRuleInit =
                new ActivityTestRule<>(ListMeetingActivity.class);

        @Rule
        public ActivityTestRule<AddMeetingActivity> mActivityRule =
                new ActivityTestRule<>(AddMeetingActivity.class);

        /**
         * Initialize AddMeetingActivity and check that it is not empty
         */
        @Before
        public void setUp() {
            AddMeetingActivity activity = mActivityRule.getActivity();
            assertThat(activity, notNullValue());
        }

        /**
         * Check the correct entry of the Emails field (press Enter key)
         */
        @Test
        public void givenValidEmail_whenTypeTextWithDelimiter_thenGetEmailWithoutError() {
            onView(withId(R.id.emails)).perform(typeText(VALID_EMAIL_1 + internalFieldDelimiter));

            onView(withId(R.id.emails_group)).check(matchesChipTextAtPosition(1, VALID_EMAIL_1));
            onView(withId(R.id.emails)).check(matches(withHint(R.string.list_of_participants)));
            onView(withId(R.id.participants)).check(matchesNoErrorText());
        }

        /**
         * Check the correct entries of the Emails field (press Enter key)
         */
        @Test
        public void givenTwoValidEmail_whenTypeTextWithDelimiter_thenGetEmailsWithoutError() {
            // Initialize test -->
            // add chip
            onView(withId(R.id.emails)).perform(typeText(VALID_EMAIL_1 + internalFieldDelimiter));
            // confirm that first chip is present
            onView(withId(R.id.emails_group)).check(matchesChipTextAtPosition(1, VALID_EMAIL_1));
            // Initialize test <--

            // Test -->
            onView(withId(R.id.emails)).perform(typeText(VALID_EMAIL_2 + internalFieldDelimiter));

            onView(withId(R.id.emails_group)).check(matchesChipTextAtPosition(2, VALID_EMAIL_2));
            onView(withId(R.id.emails)).check(matches(withHint(R.string.list_of_participants)));
            onView(withId(R.id.participants)).check(matchesNoErrorText());
            // Test <--
        }

        /**
         * Check invalid entry of the Emails field (press Enter key)
         */
        @Test
        public void givenInvalidEmail_whenTypeTextWithDelimiter_thenGetErrorMessage() {
            AddMeetingActivity activity = mActivityRule.getActivity();

            onView(withId(R.id.emails)).perform(typeText(INVALID_EMAIL + internalFieldDelimiter));

            onView(withId(R.id.participants))
                    .check(matchesErrorText(activity.getString(R.string.error_invalid_email)));
            onView(withId(R.id.emails)).check(matches(withHint(R.string.list_of_participants)));
        }

        /**
         * Check invalid entry of the Emails field (press Enter key)
         */
        @Test
        public void givenOneValidEmailAndOneInvalidEmail_whenTypeTextWithDelimiter_thenGetErrorMessage() {
            // Initialize test -->
            AddMeetingActivity activity = mActivityRule.getActivity();
            // add chip
            onView(withId(R.id.emails)).perform(typeText(VALID_EMAIL_1 + internalFieldDelimiter));
            // confirm that first chip is present
            onView(withId(R.id.emails_group)).check(matchesChipTextAtPosition(1, VALID_EMAIL_1));
            // Initialize test <--

            // test -->
            onView(withId(R.id.emails)).perform(typeText(INVALID_EMAIL + internalFieldDelimiter));

            onView(withId(R.id.participants))
                    .check(matchesErrorText(activity.getString(R.string.error_invalid_email)));
            onView(withId(R.id.emails)).check(matches(withHint(R.string.list_of_participants)));
            // Test <--
        }
    }

}
