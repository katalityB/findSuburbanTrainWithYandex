import data.Search;
import org.junit.*;
import org.junit.runner.RunWith;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import page.MainPage;
import page.SearchForm;
import page.SearchResultPage;
import steps.CommonSteps;
import steps.MainPageSteps;
import steps.SearchFormSteps;
import steps.SearchResultSteps;

@RunWith(SerenityRunner.class)
public class SearchSuburbanTrainTest {

    @Steps
    MainPageSteps mainPageSteps;

    @Steps
    CommonSteps commonSteps;

    @Steps
    SearchFormSteps searchFormSteps;

    @Steps
    SearchResultSteps searchResultSteps;

    @Managed(driver="chrome")
    WebDriver driver;

    MainPage mainPage;
    SearchForm searchForm;
    SearchResultPage searchResultPage;

    Search searchParams = new Search();

    @Test
    public void searchSuburbanTrain() {

        // GIVEN
        mainPageSteps.open_yandex_main_page();
        mainPageSteps.click_link_timetable();
        commonSteps.wait_for_title_contains("Расписание самолётов, поездов, электричек и автобусов");

        // WHEN
        searchForm.select_transport_type_as_suburban_train();
        searchForm.fill_from(searchParams);
        searchForm.fill_to(searchParams);
        searchFormSteps.select_next_saturday_as_departure_date(searchParams);
        searchForm.click_search_btn();

        // THEN
        searchResultPage.assert_h1_equal(searchParams);
        searchResultPage.assert_search_title_contains(searchParams);
        searchResultPage.assert_that_minimal_price_less_than_expected_maximum_price(searchParams);
        searchResultPage.assert_default_sorting();
        searchResultSteps.assert_that_trip_found(searchParams);
        searchResultSteps.compare_trip_info_between_route_page_and_search_result_page();
    }
}
