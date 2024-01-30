package org.coursework.config.enums;

public enum Environment {
    LOCAL,
    CI;

    public String getName() {
        return this.name().toLowerCase();
    }
}
