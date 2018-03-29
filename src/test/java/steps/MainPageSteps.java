package steps;

import net.thucydides.core.annotations.Step;
import page.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

public class MainPageSteps {

    MainPage page;

    @Step
    public void open_yandex_main_page() {
        page.open();
    }

    @Step
    public void click_link_timetable() {
        page.rasp_link_click();
    }

    @Step
    public void assert_title_equal(String expected_title) {
        assertThat(page.getTitle()).isEqualTo(expected_title);
    }
}
