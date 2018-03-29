package steps;

import data.Search;
import data.SuburbanTrainTrip;
import page.RoutePage;
import page.SearchResultPage;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultSteps {
    SearchResultPage searchResultPage;
    SuburbanTrainTrip foundTrip;
    RoutePage routePage;

    public void assert_that_trip_found(Search searchParams) {
        this.foundTrip = searchResultPage.getAppropriateTrip(searchParams);
        assertThat(this.foundTrip.tripNumber).isNotEmpty();
    }

    public void compare_trip_info_between_route_page_and_search_result_page() {
        assertThat(this.foundTrip.tripNumber).isEqualToIgnoringCase(routePage.routeNumber());
        assertThat(this.foundTrip.tripDuration).isEqualToIgnoringCase(routePage.tripDuration());
        assertThat(routePage.h1()).contains(this.foundTrip.tripName);
        assertThat(this.foundTrip.arriveTime).isEqualToIgnoringCase(routePage.arrivalTime());
        assertThat(this.foundTrip.arriveTo).isEqualToIgnoringCase(routePage.arrivalTo());
        assertThat(this.foundTrip.departureFrom).isEqualToIgnoringCase(routePage.departureFrom());
        assertThat(this.foundTrip.departureTime).isEqualToIgnoringCase(routePage.departureTime());

        System.out.println("Название таблицы: " + routePage.h1());
        System.out.println("Время отправления: " + routePage.departureTime());
        System.out.println("Пункт отправления: " + routePage.departureFrom());
        System.out.println("Время прибытия: " + routePage.arrivalTime());
        System.out.println("Пункт прибытия: " + routePage.arrivalTo());
        System.out.println("Время в пути: " + routePage.tripDuration());
    }
}
