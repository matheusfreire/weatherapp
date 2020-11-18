package br.com.msf.weather;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.msf.weather.view.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), MainActivity.class);
        ActivityScenario.launch(intent);
        onView(withId(R.id.txtInputLati)).check(matches(isDisplayed()));
    }

    @Test
    public void longitudeIsDisplayed() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), MainActivity.class);
        ActivityScenario.launch(intent);
        onView(withId(R.id.txtInputLong)).check(matches(isDisplayed()));
    }

    @Test
    public void noConnection(){
        onView(withId(R.id.txtEditLati)).perform(ViewActions.typeText("59.33"));
        onView(withId(R.id.txtEditLong)).perform(ViewActions.typeText("18.06"));
        onView(withId(R.id.txtEditLong)).perform(pressImeActionButton());
        onView(withText(R.string.no_internet)).check(matches(isDisplayed()));
    }

    @Test
    public void inputValues() {
        onView(withId(R.id.txtEditLati)).perform(ViewActions.typeText("59.33"));
        onView(withId(R.id.txtEditLong)).perform(ViewActions.typeText("18.06"));
        onView(withId(R.id.txtEditLong)).perform(pressImeActionButton());
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
