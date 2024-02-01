package org.coursework.page.logged_in.project;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.coursework.config.EnvConfig;
import org.coursework.page.common.LoggedInFilterPage;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;

public class ProjectPage extends LoggedInFilterPage {
    private final String PROJECT_URL_REGEX = String.format("%s/project/\\d+", EnvConfig.getEnvProperties().baseUrl);
    private Integer projectId;

    private SelenideElement projectIsOpenLabel = $x("//ul[@class='panel']/li[1]");
    private SelenideElement projectGeneralInfoBlock = $("ul.panel");

    @Step
    public void assertPageUrlIsRight() {
        confirmPageIsLoaded();
        String url = WebDriverRunner.url();
        Assert.assertTrue(url.matches(PROJECT_URL_REGEX), "Current url is wrong" + url);
    }

    @Step
    @Override
    public void openPage() {
        open(EnvConfig.getEnvProperties().baseUrl + "/project/" + projectId);
    }

    @Override
    protected SelenideElement readyElement() {
        return projectGeneralInfoBlock;
    }

    public int getProjectId() {
        String url = WebDriverRunner.url();
        String[] splitURL = url.split("/");
        return Integer.parseInt(splitURL[splitURL.length - 1]);
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}