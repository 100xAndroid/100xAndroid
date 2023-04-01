package cz.stokratandroid.testovani;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void testovaciMetoda_isCorrect() {

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        int vysledekTestu = MainActivity.testovaciMetoda2();
        assertTrue(vysledekTestu > 20);
    }
}