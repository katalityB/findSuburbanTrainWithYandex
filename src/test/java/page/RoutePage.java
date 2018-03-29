package page;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class RoutePage extends PageObject{
    @FindBy(tagName="h1")
    WebElement h1;

    @FindBy(css = ".b-timetable__cell_type_departure strong")
    WebElement departure;

    @FindAll(@FindBy(className = "b-timetable__city"))
    List<WebElement> stationNames;

    @FindAll({@FindBy(tagName = "td"), @FindBy(className = "b-timetable__cell"), @FindBy(className = "b-timetable__cell_type_arrival")})
    List<WebElement> arrivals;

    @FindAll({@FindBy(tagName = "td"), @FindBy(className = "b-timetable__cell_type_time"), @FindBy(className = "b-timetable__cell_position_last")})
    List<WebElement> tripDurations;

    @FindBy(xpath = "//h1/span[2]")
    WebElement routeNumber;

    public String h1() {
        return h1.getText();
    }

    public String departureTime() {
        return departure.getText();
    }

    public String departureFrom() {
        return stationNames.get(0).getText();
    }

    public String arrivalTo() {
        return stationNames.get(stationNames.size() - 1).getText();
    }

    public String arrivalTime() {
        return arrivals.get(arrivals.size() - 1).getText();
    }

    public String tripDuration() {
        return tripDurations.get(tripDurations.size() - 1).getText();
    }

    public String routeNumber() {
        return routeNumber.getText();
    }
}
