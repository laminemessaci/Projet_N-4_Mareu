package com.lamine.mareu.utils.assertions;

import android.view.View;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lamine MESSACI on 27/03/2020.
 */
public class TextInputLayoutErrorValueAssertion implements ViewAssertion {

    private final String mExpectedText;

    TextInputLayoutErrorValueAssertion(String expectedText) {
        mExpectedText = expectedText;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        String text;
        try {
            text = Objects.requireNonNull(((TextInputLayout) view).getError()).toString();
        } catch (Exception e) {
            text = "";
        }

        assertEquals(mExpectedText, text);
    }

    public static TextInputLayoutErrorValueAssertion matchesErrorText(String expectedText) {
        return new TextInputLayoutErrorValueAssertion(expectedText);
    }
}
