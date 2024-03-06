package org.coursework.page.logged_in.project;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.base.BasePage;
import org.coursework.utils.HTMLTags;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static org.coursework.config.TextConfig.getTextProperties;

public class ProjectPage extends BasePage {
    private SelenideElement projectStatusLabel = $x("//ul[@class='panel']/li[1]/strong");
    private SelenideElement projectGeneralInfoBlock = $("ul.panel");

    private Integer projectId;

    public ProjectPage() {
        super("/project/", null);
        this.projectId = getItemIdFromUrl();
    }

    public ProjectPage(Integer projectId) {
        super("/project/", projectId);
        this.projectId = projectId;
    }

    @Step
    public void projectNameIsVisible(String taskName) {
        findElementByText(HTMLTags.SPAN, taskName);
    }

    @Step
    public void projectStatusIsOpened() {
        Assert.assertEquals(projectStatusLabel.text(), getTextProperties().projectStatusOpened, "Comment's creator name is not same as expected.");
    }

    public int getProjectId() {
        return projectId;
    }

    @Override
    protected SelenideElement readyElement() {
        return projectGeneralInfoBlock;
    }
}