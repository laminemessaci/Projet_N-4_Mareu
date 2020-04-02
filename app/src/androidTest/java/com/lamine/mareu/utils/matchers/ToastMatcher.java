package com.lamine.mareu.utils.matchers;

import android.os.IBinder;
import android.view.WindowManager;

import androidx.test.espresso.Root;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Lamine MESSACI on 27/03/2020.
 */
public class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    protected boolean matchesSafely(Root item) {
        int type = item.getWindowLayoutParams().get().type;

        if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
            IBinder windowToken = item.getDecorView().getWindowToken();
            IBinder appToken = item.getDecorView().getApplicationWindowToken();

            // true means this window isn't contained by any other windows.
            return windowToken == appToken;
        }

        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is toast");
    }

    public static ToastMatcher isToast() {
        return new ToastMatcher();
    }
}
