package ru.technocracy.echoserver.services;

import jakarta.transaction.Transactional;
import ru.technocracy.echoserver.dto.event.ShortEventFromKudaGoDto;

import java.util.List;

public interface FeedService {


    @Transactional
    List<ShortEventFromKudaGoDto> getUserFavoritesEventFeed(Long userId);

    @Transactional
    List<ShortEventFromKudaGoDto> getUserNeutralEventFeed(Long userId);
}
