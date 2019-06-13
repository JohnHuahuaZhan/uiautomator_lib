package com.uiautomator.lib.support.scroll;

import androidx.test.uiautomator.Tracer;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;

public class ExtUIScrollable extends UiScrollable {
    /**
     * Constructor.
     *
     * @param container a {@link UiSelector} selector to identify the scrollable
     *                  layout element.
     * @since API Level 16
     */
    public ExtUIScrollable(UiSelector container) {
        super(container);
    }

    public boolean scrollForwardIntoView(UiSelector selector) throws UiObjectNotFoundException {
        Tracer.trace(selector);
        // if we happen to be on top of the text we want then return here
        UiSelector childSelector = getSelector().childSelector(selector);
        if (exists(childSelector)) {
            return (true);
        } else {
            if (exists(childSelector)) {
                return (true);
            }
            for (int x = 0; x < getMaxSearchSwipes(); x++) {
                boolean scrolled = scrollForward();
                if(exists(childSelector)) {
                    return true;
                }
                if (!scrolled) {
                    return false;
                }
            }
        }
        return false;
    }
    public boolean scrollBackwardIntoView(UiSelector selector) throws UiObjectNotFoundException {
        Tracer.trace(selector);
        // if we happen to be on top of the text we want then return here
        UiSelector childSelector = getSelector().childSelector(selector);
        if (exists(childSelector)) {
            return (true);
        } else {
            if (exists(childSelector)) {
                return (true);
            }
            for (int x = 0; x < getMaxSearchSwipes(); x++) {
                boolean scrolled = scrollBackward();
                if(exists(childSelector)) {
                    return true;
                }
                if (!scrolled) {
                    return false;
                }
            }
        }
        return false;
    }
}
