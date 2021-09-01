package com.example.hw1solo;

        import android.content.Context;

        import androidx.test.platform.app.InstrumentationRegistry;
        import androidx.test.ext.junit.runners.AndroidJUnit4;

        import org.junit.Test;
        import org.junit.runner.RunWith;

        import static org.junit.Assert.*;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    @Test
    public void samePasswords() {
        assertEquals("com.example.hw1solo", LoginActivity.passwordCheck("testing", "testing"));
    }

    @Test
    public void differentPasswords() {
        assertEquals("com.example.hw1solo", LoginActivity.passwordCheck("testing", "notTesting"));
    }

    @Test
    public void validUsername() {
        List<User> validUsers =  new ArrayList<>();
        User testUser = new User(1, "David", "c0w0kie", "legitEmail@wow.com");
        validUsers.add(testUser);
        assertEquals("com.example.hw1solo", LoginActivity.usernameCheck(validUsers, "c0w0kie"));
    }

    @Test
    public void invalidUsernames() {
        List<User> validUsers =  new ArrayList<>();
        User testUser = new User(1, "David", "c0w0kie", "legitEmail@wow.com");
        validUsers.add(testUser);
        assertEquals("com.example.hw1solo", LoginActivity.usernameCheck(validUsers, "notAUser"));
    }
}