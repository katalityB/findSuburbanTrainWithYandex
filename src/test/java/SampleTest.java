import org.junit.*;
import org.junit.runner.RunWith;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class SampleTest {

    @Steps
    SampleSteps sampleSteps;

        @Managed(driver="chrome")
        WebDriver driver;

        GooglePage googlePage;

        @Test
        public void searchInGoogle() {

            System.out.println();

            // GIVEN
            sampleSteps.open_google_search_main_page();

            // WHEN
            sampleSteps.search_for("firefly");

            // THEN
            sampleSteps.assert_title_equal("firefly - ค้นหาด้วย Google");
        }
}
