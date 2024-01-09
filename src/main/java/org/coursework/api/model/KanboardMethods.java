package org.coursework.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum KanboardMethods {
    CREATE_USER("createUser", 1423501287),
    GET_USER("getUser", 1769674781),
    REMOVE_USER("removeUser", 2094191872),
    CREATE_PROJECT("createProject", 1797076613),
    GET_PROJECT_BY_ID("getProjectById", 226760253),
    REMOVE_PROJECT("removeProject", 46285125),
    CREATE_TASK("createTask", 1176509098),
    GET_TASK("getTask", 700738119),
    REMOVE_TASK("removeTask", 1423501287);

    private String method;
    private long id;
}
