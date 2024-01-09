package org.coursework.base;

import io.qameta.allure.Step;
import org.testng.Assert;

abstract public class BaseAPIProcedures {
    @Step("Created item's {field} is same as expected")
    public static void assertItemField(String actual, String expected, String field) {
        Assert.assertEquals(actual, expected);
    }

    @Step
    public static void itemRemovingRequestIsSuccessful(boolean flag) {
        Assert.assertTrue(flag, "Item is not deleted");
    }

    @Step
    public static <T> void itemIsRemoved(T info) {
        Assert.assertNull(info, "Item is not deleted");
    }
}
