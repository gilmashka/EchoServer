package ru.technocracy.echoserver.services;

import ru.technocracy.echoserver.dto.UserCategoryPreferenceDto;

import java.util.List;

public interface CategoryService {

    List<UserCategoryPreferenceDto> getAllCategoriesWithPreference(Long userId);

    void setUserPreference(UserCategoryPreferenceDto userPreference, Long userId);
}
