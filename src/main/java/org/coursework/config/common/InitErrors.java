package org.coursework.config.common;

import java.util.ArrayList;
import java.util.List;

public class InitErrors {
    private static final ThreadLocal<List<String>> ERRORS_LIST = ThreadLocal.withInitial(ArrayList::new);

    public static void addError(String errorMessage) {
        ERRORS_LIST.get().add(errorMessage);
    }

    public static void showErrors() {
        if (ERRORS_LIST.get().size() > 0) {
            String errorMessage = String.join("\n", ERRORS_LIST.get());
            ERRORS_LIST.set(null);
            throw new RuntimeException(errorMessage);
        }
    }
}
