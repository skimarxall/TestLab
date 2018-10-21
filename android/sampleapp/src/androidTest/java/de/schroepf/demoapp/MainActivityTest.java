package de.schroepf.demoapp;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.screenshot.ScreenCaptureProcessor;
import android.support.test.runner.screenshot.Screenshot;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import de.schroepf.demoapp.screenshot.LocaleTestRule;
import de.schroepf.demoapp.screenshot.ScreenshotProcessor;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeThat;

/**
 * @author Tobias Schröpf on 17.08.16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public LocaleTestRule<MainActivity> localeTestRule = new LocaleTestRule<>(MainActivity.class, "de-DE", "en-US", "fr-FR");

    @Before
    public void setup() {
        Set<ScreenCaptureProcessor> processors = new HashSet<>();
        processors.add(new ScreenshotProcessor());
        Screenshot.addScreenCaptureProcessors(processors);
    }

    @Test
    @Ignore
    public void testHelloWorld() {
        onView(withId(R.id.hello_world_text_view)).check(matches(withText("This is a failing test")));
    }

    @Test
    public void testWithFailingAssumption() {
        assumeThat("This is a failing assumption", 0, is(1));
    }

    @Test
    public void testFailingTest() {
        fail("This is just a failing test");
    }

    @Test
    public void screenshotTest() throws IOException {
        onView(isRoot()).check(matches(isDisplayed()));
        Screenshot.capture()
                .setName(localeTestRule.getCurrentLocale().toLanguageTag())
                .process();
    }
}