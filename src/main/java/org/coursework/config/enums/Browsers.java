package org.coursework.config.enums;

public enum Browsers {
    CHROMIUM,
    CHROME,
    FIREFOX;

    public String getName() {
        return name().toLowerCase();
    }
}
