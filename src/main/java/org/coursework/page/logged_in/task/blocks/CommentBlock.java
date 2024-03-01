package org.coursework.page.logged_in.task.blocks;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.coursework.base.BaseBlock;
import org.testng.Assert;

public class CommentBlock extends BaseBlock {
    private final SelenideElement baseElement;
    public final SelenideElement text;
    public final SelenideElement creator;

    public CommentBlock(SelenideElement baseElement) {
        this.baseElement = baseElement;
        text = baseElement.$x(".//div[@class='comment-content']//p");
        creator = baseElement.$x(".//div[@class='comment-title']/strong");
    }

    @Step
    public void assertCreatorName(String expectedUsername){
        Assert.assertEquals(creator.text(), expectedUsername, "Comment's creator name is not same as expected.");
    }

    @Step
    public void assertText(String expectedText){
        Assert.assertEquals(text.text(), expectedText, "Comment's text is not same as expected.");
    }

    @Override
    protected SelenideElement baseElement() {
        return baseElement;
    }
}
