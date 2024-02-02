package org.coursework.page.logged_in.task;

import com.codeborne.selenide.SelenideElement;
import org.coursework.base.BaseBlock;

public class CommentBlock extends BaseBlock {
    private final SelenideElement baseElement;
    public final SelenideElement text;
    public final SelenideElement creator;

    public CommentBlock(SelenideElement baseElement) {
        this.baseElement = baseElement;
        text = baseElement.$x(".//div[@class='comment-content']//p");
        creator = baseElement.$x(".//div[@class='comment-title']/strong");
    }

    @Override
    protected SelenideElement baseElement() {
        return baseElement;
    }
}
