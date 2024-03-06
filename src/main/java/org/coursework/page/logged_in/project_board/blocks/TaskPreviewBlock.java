package org.coursework.page.logged_in.project_board.blocks;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.base.BaseBlock;
import org.testng.Assert;

import java.util.List;

public class TaskPreviewBlock extends BaseBlock {
    public final SelenideElement name;
    private final SelenideElement baseElement;

    public TaskPreviewBlock(SelenideElement baseElement) {
        this.baseElement = baseElement;
        name = baseElement.$x(".//div[contains(@class, 'title')]/a");
    }

    @Step
    public void assertTaskName(String expectedName) {
        Assert.assertEquals(name.text(), expectedName, "Task name is not same as expected.");
    }

    @Override
    protected SelenideElement baseElement() {
        return baseElement;
    }
}
