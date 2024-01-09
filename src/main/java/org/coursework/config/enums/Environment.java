package org.coursework.config.enums;

public enum Environment {
    LOCAL,
    GRID;

    public String getEnvName() {
        return this.name().toLowerCase();
    }
}
