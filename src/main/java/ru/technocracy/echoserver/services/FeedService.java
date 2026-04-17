package ru.technocracy.echoserver.services;

import jakarta.transaction.Transactional;
import ru.technocracy.echoserver.dto.event.ShortEventDto;

import java.util.List;

public interface FeedService {


    @Transactional
    List<ShortEventDto> getUserFavoritesEventFeed(Long userId);

    @Transactional
    List<ShortEventDto> getUserNeutralEventFeed(Long userId);
}
