package org.coursework.api;

import org.coursework.base.BaseAPITest;
import org.coursework.api.model.user.User;
import org.coursework.api.model.user.UserExtended;
import org.coursework.utils.FieldsHelper;
import org.testng.annotations.Test;

import static org.coursework.api.procedures.UserProcedures.*;
import static org.coursework.utils.TestData.generateDefaultUserData;

public class UserTest extends BaseAPITest {
    @Test(groups = {"CRUD_user_API", "API", "smoke_API"})
    public void userFlow() {
        User user = createUser(generateDefaultUserData(), ADMIN);

        UserExtended userInfo = getUserById(user.getId(), ADMIN);
        assertItemField(user.getUsername(), userInfo.getUsername(), FieldsHelper.getUserUsernameField());
        assertItemField(user.getName(), userInfo.getName(), FieldsHelper.getUserNameField());
        assertItemField(user.getEmail(), userInfo.getEmail(), FieldsHelper.getUserEmailField());
        assertItemField(user.getRole(), userInfo.getRole(), FieldsHelper.getUserRoleField());

        boolean isUserRemoved = removeUserById(user.getId(), ADMIN);
        itemRemovingRequestIsSuccessful(isUserRemoved);

        UserExtended userInfoAfterRemoving = getUserById(user.getId(), ADMIN);
        itemIsRemoved(userInfoAfterRemoving);
    }
}
