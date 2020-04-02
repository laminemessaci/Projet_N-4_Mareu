package com.lamine.mareu.utils.assertions;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * Created by Lamine MESSACI on 27/03/2020.
 */
public class RecyclerViewItemCountAssertion implements ViewAssertion {

    private final int mExpectedCount;

    private RecyclerViewItemCountAssertion(int expectedCount) {
        this.mExpectedCount = expectedCount;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat(Objects.requireNonNull(adapter).getItemCount(), is(mExpectedCount));
    }

    public static RecyclerViewItemCountAssertion itemCountAssertion(int expectedCount) {
        return new RecyclerViewItemCountAssertion(expectedCount);
    }
}
