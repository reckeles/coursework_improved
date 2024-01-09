package org.coursework.api.procedures;

import io.qameta.allure.Step;
import org.coursework.base.BaseAPIProcedures;
import org.coursework.api.model.Authorization;
import org.coursework.api.model.task.*;

import static org.coursework.api.APIUtil.*;
import static org.coursework.api.model.KanboardMethods.*;

abstract public class TaskProcedures extends BaseAPIProcedures {
    @Step
    public static TaskExtended getTaskById(Integer id, Authorization authorization) {
        return sendGetRequest(GET_TASK,
                TaskId.builder().task_id(id).build(),
                TaskExtended.class,
                authorization);
    }

    @Step
    public static Task createTask(Task task, Authorization authorization) {
        Integer id = sendCreateRequest(CREATE_TASK,
                task,
                authorization);
        task.setId(id);
        return task;
    }

    @Step
    public static Boolean removeTaskById(Integer id, Authorization authorization) {
        return sendRemoveRequest(REMOVE_TASK,
                TaskId.builder().task_id(id).build(),
                authorization);
    }
}
