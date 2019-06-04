package com.uiautomator.lib.support.asserts;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

public class TestAssert {
    public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher, Runnable callback) {
        if (!matcher.matches(actual)) {
            Description description = new StringDescription();
            description.appendText(reason)
                    .appendText("\nExpected: ")
                    .appendDescriptionOf(matcher)
                    .appendText("\n     but: ");
            matcher.describeMismatch(actual, description);
            callback.run();
            throw new AssertionError(description.toString());
        }
    }
}
