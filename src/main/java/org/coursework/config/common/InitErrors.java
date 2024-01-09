package org.coursework.config.common;

import java.util.ArrayList;
import java.util.List;

public class InitErrors {
    private static final List<String> ERRORS_LIST = new ArrayList<>();

    public static void addError(String errorMessage) {
        ERRORS_LIST.add(errorMessage);
    }

    public static void showErrors() {
        if (ERRORS_LIST.size() > 0) {
            throw new RuntimeException("\n" + String.join("\n", ERRORS_LIST));
        }
    }
}
