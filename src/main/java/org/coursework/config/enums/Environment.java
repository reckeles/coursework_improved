package org.coursework.config.enums;

public enum Environment {
    LOCAL,
    CI;

    public String getEnvName() {
        return this.name().toLowerCase();
    }
}
