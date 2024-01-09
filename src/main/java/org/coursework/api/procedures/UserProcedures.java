package org.coursework.api.procedures;

import io.qameta.allure.Step;
import org.coursework.base.BaseAPIProcedures;
import org.coursework.api.model.Authorization;
import org.coursework.api.model.user.*;

import static org.coursework.api.APIUtil.*;
import static org.coursework.api.model.KanboardMethods.*;

abstract public class UserProcedures extends BaseAPIProcedures {
    @Step
    public static User createUser(User user, Authorization authorization) {
        Integer id = sendCreateRequest(CREATE_USER,
                user,
                authorization);
        user.setId(id);
        return user;
    }

    @Step
    public static UserExtended getUserById(Integer id, Authorization authorization) {
        return sendGetRequest(GET_USER,
                UserId.builder().user_id(id).build(),
                UserExtended.class,
                authorization);
    }

    @Step
    public static Boolean removeUserById(Integer id, Authorization authorization) {
        return sendRemoveRequest(REMOVE_USER,
                UserId.builder().user_id(id).build(),
                authorization);
    }
}
