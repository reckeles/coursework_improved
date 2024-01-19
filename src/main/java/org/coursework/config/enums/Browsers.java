package org.coursework.config.enums;

public enum Browsers {
    CHROME,
    FIREFOX;

    public String getName() {
        return this.name().toLowerCase();
    }
}
