package ru.technocracy.echoserver.integrations;

import org.springframework.web.service.annotation.GetExchange;
import ru.technocracy.echoserver.dto.CategoryFromKudaGoDto;
import java.util.List;

public interface ExternalClient {

    @GetExchange("/event-categories/?lang=ru&order_by=id&fields=id,name")
    public List<CategoryFromKudaGoDto> getAllEventCategories();
}
