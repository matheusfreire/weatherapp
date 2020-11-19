package br.com.msf.weather;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.msf.weather.view.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class WeatherInstrumentedTest {

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        activityScenario.onActivity((ActivityScenario.ActivityAction<MainActivity>) activity -> {
            mIdlingResource = activity.getIdlingResource();
            IdlingRegistry.getInstance().register(mIdlingResource);
        });
    }

    @Test
    public void latitudeIsDisplayed() {
        onView(withId(R.id.txtInputLati)).check(matches(isDisplayed()));
    }

    @Test
    public void longitudeIsDisplayed() {
        onView(withId(R.id.txtInputLong)).check(matches(isDisplayed()));
    }

    @Test
    public void test_errorOnLati(){
        Context applicationContext = ApplicationProvider.getApplicationContext();
        onView(withId(R.id.txtEditLong)).perform(pressImeActionButton());
        onView(withId(R.id.txtInputLati)).check(matches(hasDescendant(
                withText(applicationContext.getString(R.string.provide_value))))
        );
    }

    @Test
    public void test_errorOnLong(){
        Context applicationContext = ApplicationProvider.getApplicationContext();
        onView(withId(R.id.txtEditLati)).perform(ViewActions.typeText("59.33"));
        onView(withId(R.id.txtEditLong)).perform(pressImeActionButton());
        onView(withId(R.id.txtInputLong)).check(matches(hasDescendant(
                withText(applicationContext.getString(R.string.provide_value))))
        );
    }

    @Test
    public void test_noErrorOnLatiAndLong(){
        Context applicationContext = ApplicationProvider.getApplicationContext();
        onView(withId(R.id.txtEditLati)).perform(ViewActions.typeText("59.33"));
        onView(withId(R.id.txtEditLong)).perform(ViewActions.typeText("18.06"));
        onView(withId(R.id.txtEditLong)).perform(pressImeActionButton());
        onView(withId(R.id.txtInputLong)).check(matches(not(hasDescendant(
                withText(applicationContext.getString(R.string.provide_value)))))
        );
        onView(withId(R.id.txtInputLati)).check(matches(not(hasDescendant(
                withText(applicationContext.getString(R.string.provide_value)))))
        );
    }


    @Test
    public void test_onSuccess() {
        onView(withId(R.id.txtEditLati)).perform(ViewActions.typeText("59.33"));
        onView(withId(R.id.txtEditLong)).perform(ViewActions.typeText("18.06"));
        onView(withId(R.id.txtEditLong)).perform(pressImeActionButton());
        onView(withId(R.id.txtViewError)).check(matches(not(isDisplayed())));
    }

    @Test
    public void test_onError() {
        onView(withId(R.id.txtEditLati)).perform(ViewActions.typeText("12391023910239123.33"));
        onView(withId(R.id.txtEditLong)).perform(ViewActions.typeText("1023019230129310293.06"));
        onView(withId(R.id.txtEditLong)).perform(pressImeActionButton());
        onView(withId(R.id.txtViewError)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
