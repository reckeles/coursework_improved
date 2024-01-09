package org.coursework.utils;

public class Wait {
    static public void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (Throwable e) { /* Ignore */ }
    }
}
