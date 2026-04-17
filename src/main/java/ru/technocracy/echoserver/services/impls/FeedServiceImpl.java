package ru.technocracy.echoserver.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.technocracy.echoserver.dto.UserCategoryPreferenceDto;
import ru.technocracy.echoserver.dto.event.KudaGoResponse;
import ru.technocracy.echoserver.dto.event.ShortEventDto;
import ru.technocracy.echoserver.exceptions.NotFoundException;
import ru.technocracy.echoserver.integrations.ExternalClient;
import ru.technocracy.echoserver.models.usercategorypreference.PreferenceType;
import ru.technocracy.echoserver.services.CategoryService;
import ru.technocracy.echoserver.services.FeedService;
import ru.technocracy.echoserver.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final UserService userService;
    private final CategoryService categoryService;
    private final ExternalClient externalClient;

    @Override
    public List<ShortEventDto> getUserFavoritesEventFeed(Long userId) {
        return getFeed(userId, PreferenceType.LIKE);
    }

    @Override
    public List<ShortEventDto> getUserNeutralEventFeed(Long userId) {
        return getFeed(userId, PreferenceType.NEUTRAL);
    }

    private List<ShortEventDto> getFeed(Long userId, PreferenceType type) throws NotFoundException{
        String categories = categoryService.getAllCategoriesWithPreference(userId).stream()
                .filter(c -> c.getPreference() == type)
                .map(UserCategoryPreferenceDto::getSlug)
                .collect(Collectors.joining(","));

        if (categories.isEmpty()){
            throw new NotFoundException("Вы не выбрали ни одной " + type.name() + "категории");
        }

        String location = userService.getUserCityById(userId).name();
        long since = System.currentTimeMillis() / 1000;

        KudaGoResponse response = externalClient.getUserFavouriteEventFeed(
                categories,
                userService.getUserCityById(userId).name(),
                System.currentTimeMillis() / 1000
        );

        return response.getResults();
    }
}