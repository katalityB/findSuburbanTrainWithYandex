import net.thucydides.core.annotations.Step;
import static org.assertj.core.api.Assertions.assertThat;

public class SampleSteps {

    GooglePage page;

    @Step
    public void open_google_search_main_page() {
        page.open();
    }

    @Step
    public void search_for(String search_phrase) {
        page.searchFor(search_phrase);
    }

    @Step
    public void assert_title_equal(String expected_title) {
        assertThat(page.getTitle()).isEqualTo(expected_title);
    }
}
