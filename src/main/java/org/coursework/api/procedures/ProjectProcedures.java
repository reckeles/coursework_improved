package org.coursework.api.procedures;

import io.qameta.allure.Step;
import org.coursework.base.BaseAPIProcedures;
import org.coursework.api.model.Authorization;
import org.coursework.api.model.project.Project;
import org.coursework.api.model.project.ProjectExtended;
import org.coursework.api.model.project.ProjectId;

import static org.coursework.api.APIUtil.*;
import static org.coursework.api.model.KanboardMethods.*;

abstract public class ProjectProcedures extends BaseAPIProcedures {
    @Step
    public static ProjectExtended getProjectById(Integer id, Authorization authorization) {
        return sendGetRequest(GET_PROJECT_BY_ID,
                ProjectId.builder().project_id(id).build(),
                ProjectExtended.class,
                authorization);
    }

    @Step
    public static Project createProject(Project project, Authorization authorization) {
        Integer id = sendCreateRequest(CREATE_PROJECT,
                project,
                authorization);
        project.setId(id);
        return project;
    }

    @Step
    public static Boolean removeProjectById(Integer id, Authorization authorization) {
        return sendRemoveRequest(REMOVE_PROJECT,
                ProjectId.builder().project_id(id).build(),
                authorization);
    }
}
