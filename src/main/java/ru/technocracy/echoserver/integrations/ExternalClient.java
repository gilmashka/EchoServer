package ru.technocracy.echoserver.integrations;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import ru.technocracy.echoserver.dto.CategoryFromKudaGoDto;
import ru.technocracy.echoserver.dto.event.EventFromKudaGoDto;
import ru.technocracy.echoserver.dto.event.ShortEventFromKudaGoDto;

import java.util.List;

public interface ExternalClient {

    @GetExchange("/event-categories/?lang=ru&order_by=id&fields=id,name,slug")
    public List<CategoryFromKudaGoDto> getAllEventCategories();

    @GetExchange("/events/{id}/?lang=ru&fields=title,dates,place,body_text,location,age_restriction,price,site_url,description")
    public EventFromKudaGoDto getEventDetails(@PathVariable("id") int idFromKudaGo);

    @GetExchange("events/{id}/?lang=ru&fields=id")
    public EventFromKudaGoDto checkEventExists(@PathVariable("id") int idFromKudaGo);

    @GetExchange("/events/?lang=ru&fields=id,dates,title,price,images&categories={categories}&location={location}&actual_since={since}")
    public List<ShortEventFromKudaGoDto> getUserFavouriteEventFeed(
            @PathVariable("categories") String categories,
            @PathVariable("location") String location,
            @PathVariable("since") long since
    );


}
