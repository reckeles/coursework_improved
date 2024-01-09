package org.coursework.config.enums;

public enum TextLocale {
    EN;

    public String getLocale() {
        return this.name().toLowerCase();
    }
}
