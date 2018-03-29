package steps;

import net.thucydides.core.annotations.Step;
import page.MainPage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import net.thucydides.core.pages.PageObject;

public class CommonSteps extends PageObject {

    @Step
    public void wait_for_title_contains(String title) {
        waitFor(titleContains(title));
    }
}
