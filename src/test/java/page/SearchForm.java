package page;

import data.Search;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.byLessThan;

@DefaultUrl("http://rasp.yandex.ru/")
public class SearchForm extends PageObject{
    @FindBy(name="fromName")
    WebElement from;

    @FindBy(name="toName")
    WebElement to;

    @FindBy(className="search-form__from")
    WebElement searchFormFrom;

    @FindBy(className="search-form__to")
    WebElement searchFormTo;

    @FindBy(className="search-form__when")
    WebElement whenDiv;

    @FindBy(className="_today")
    WebElement today;

    @FindBy(className = "date-input_search__input")
    WebElement whenInput;

    @FindBy(xpath="//label[text() = 'Электричка']")
    WebElement suburbanTrain;

    @FindBy(className = "calendar")
    WebElement calendar;

    @FindBy(className = "y-button_islet-rasp-search")
    WebElement searchBtn;

    public void fill_from(Search searchParams) {
        from.clear();
        from.sendKeys(searchParams.getFrom());
    }

    public void fill_to(Search searchParams) {
        to.clear();
        to.sendKeys(searchParams.getTo());
    }

    public void whenDiv_click() {
        whenDiv.click();
        waitFor(calendar);
    }

    public void assert_today_date(Search searchParams) {
        assertThat(today.getAttribute("data-date")).isEqualTo(searchParams.getToday());
    }

    public void click_next_saturday(Search searchParams) {
        calendar.findElement(By.cssSelector("div[data-date=\"" + searchParams.getNextSaturday() + "\"]")).click();
    }

    public void click_search_btn() {
        searchBtn.click();
    }

    public void select_transport_type_as_suburban_train() {
        suburbanTrain.click();
    }
}
