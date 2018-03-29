package steps;

import data.Search;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import page.SearchForm;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class SearchFormSteps {

    SearchForm searchForm;

    @Step
    public void select_next_saturday_as_departure_date(Search searchParams) {
        searchForm.whenDiv_click();
        searchForm.assert_today_date(searchParams);
        searchForm.click_next_saturday(searchParams);
    }
}
