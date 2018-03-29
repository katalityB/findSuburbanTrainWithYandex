package page;

import data.Search;
import data.SuburbanTrainTrip;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class SearchResultPage extends PageObject{
    @FindBy(tagName="h1")
    WebElement h1;

    @FindBy(className = "SearchTitle")
    WebElement searchTitle;

    @FindBy(className = "SearchSorting")
    WebElement searchSorting;

    @FindAll({@FindBy(tagName = "article"), @FindBy(className = "SearchSegment"), @FindBy(className = "SearchSegment_isVisible"), @FindBy(className = "SearchSegment_isNotGone")})
    List<WebElement> searchResultAllRows;

    @FindBy(className = "SearchSegments")
    WebElement resultTable;

    @FindBy(className = "CurrencySelect")
    WebElement currencySelect;

    public void assert_h1_equal(Search searchParams) {
        assertThat(h1.getText()).isEqualTo(searchParams.getExpectedH1());
    }

    public void assert_search_title_contains(Search searchParams) {
        assertThat(searchTitle.getText()).contains(searchParams.getExpectedSearchTitle());
    }

    public void assert_that_minimal_price_less_than_expected_maximum_price(Search searchParams) {
        Integer price = getPriceFromSearchTitle();
        assertThat(price).isNotEqualTo(-1);
        assertThat(price).isLessThanOrEqualTo(searchParams.getTicketMaxPrice());
    }

    public void assert_default_sorting() {
        assertThat(searchSorting.getText()).isEqualTo("по времени отправления\nсначала ранние");
    }

    private Integer getPriceFromSearchTitle() {
        return getFirstGroupOfDigitsFromString(searchTitle.getText());
    }

    private Integer getFirstGroupOfDigitsFromString(String str) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(str);
        Integer price = -1;
        if(m.find()) {
            price = Integer.parseInt(m.group());
        }
        return price;
    }

    public SuburbanTrainTrip getAppropriateTrip(Search searchParams) {
        SuburbanTrainTrip foundTrip = new SuburbanTrainTrip();
        int index = 0;
        for (WebElement row : searchResultAllRows) {
            String departureTime = getRowDepartureTime(row);
            Integer price = getRowPrice(row);
            if(getHours(departureTime) >= getHours(searchParams.getStartFromHour())
                    && getMinutes(departureTime) >= getMinutes(searchParams.getStartFromHour())
                        && price <= searchParams.getTicketMaxPrice())
            {
                foundTrip = fillFoundTrip(index, foundTrip);
                selectCurrency("USD");
                waitForAnyTextToAppear(getRowPriceElement(row), "$");
                foundTrip.dollarPrice = getRowPriceElement(row).getText();
                row.findElement(By.tagName("a")).click();
                break;
            }
            index++;
        }

        System.out.println("Время отправления: " + foundTrip.arriveTime );
        System.out.println("Цена в рублях: " + foundTrip.price);
        System.out.println("Цена в долларах: " + foundTrip.dollarPrice);

        return foundTrip;
    }

    public void foundTripClick(String tripName) {
        resultTable.findElement(By.linkText(tripName)).click();
    }

    private SuburbanTrainTrip fillFoundTrip(int index, SuburbanTrainTrip foundTrip) {
        foundTrip.departureTime = getRowDepartureTime(searchResultAllRows.get(index));
        foundTrip.price = getRowPrice(searchResultAllRows.get(index));
        foundTrip.arriveTime = resultTable.findElement(By.xpath("//article[" + (index + 1) + "]//div[contains(@class, 'SearchSegment__times')]/div[3]/span")).getText();
        foundTrip.tripName = searchResultAllRows.get(index).findElement(By.className("SegmentTitle__title")).getText();
        foundTrip.arriveTo = foundTrip.tripName.split(" ")[2];
        foundTrip.departureFrom = foundTrip.tripName.split(" ")[0];
        foundTrip.tripDuration = searchResultAllRows.get(index).findElement(By.className("SearchSegment__duration")).getText();
        foundTrip.tripNumber = searchResultAllRows.get(index).findElement(By.className("SegmentTitle__number")).getText();
        return foundTrip;
    }

    private String getRowDepartureTime(WebElement row) {
        return row.findElement(By.className("SearchSegment__time")).getText();
    }

    private Integer getRowPrice(WebElement row) {
        return getFirstGroupOfDigitsFromString(getRowPriceElement(row).getText());
    }

    private WebElement getRowPriceElement(WebElement row) {
        return row.findElement(By.className("SuburbanTariffs__buttonPrice"));
    }

    private Integer getHours(String time) {
        return Integer.parseInt(time.split(":")[0]);
    }

    private Integer getMinutes(String time) {
        return Integer.parseInt(time.split(":")[1]);
    }

    private void selectCurrency(String currency) {
        currencySelect.click();
        waitForRenderedElementsToBePresent(By.xpath("//div[@data-value='" + currency + "']"));
        currencySelect.findElement(By.xpath("//div[@data-value='" + currency + "']")).click();
    }

}
