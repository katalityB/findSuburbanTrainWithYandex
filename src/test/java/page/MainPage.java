package page;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

@DefaultUrl("http://www.yandex.ru")
public class MainPage extends PageObject{

    @FindBys({@FindBy(className="widget_id_geo"), @FindBy(linkText = "Расписания")})
    WebElement raspLink;

    public void rasp_link_click() {
        raspLink.click();
    }
}
