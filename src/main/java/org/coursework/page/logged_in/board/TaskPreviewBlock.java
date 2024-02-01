package org.coursework.page.logged_in.board;

import com.codeborne.selenide.SelenideElement;
import org.coursework.base.BaseBlock;

public class TaskPreviewBlock extends BaseBlock {
    public final SelenideElement name;
    private final SelenideElement baseElement;

    public TaskPreviewBlock(SelenideElement baseElement) {
        this.baseElement = baseElement;
        name = baseElement.$x(".//div[contains(@class, 'title')]/a");

    }

    @Override
    protected SelenideElement baseElement() {
        return baseElement;
    }
}
