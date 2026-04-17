package ru.technocracy.echoserver.services.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.technocracy.echoserver.dto.UserCategoryPreferenceDto;
import ru.technocracy.echoserver.models.Category;
import ru.technocracy.echoserver.models.usercategorypreference.PreferenceType;
import ru.technocracy.echoserver.models.usercategorypreference.UserCategoryPreference;
import ru.technocracy.echoserver.models.usercategorypreference.UserCategoryPreferencePK;
import ru.technocracy.echoserver.repositories.CategoryRepository;
import ru.technocracy.echoserver.repositories.UserCategoryPreferenceRepository;
import ru.technocracy.echoserver.repositories.UserRepository;
import ru.technocracy.echoserver.services.CategoryService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserCategoryPreferenceRepository userCategoryPreferenceRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<UserCategoryPreferenceDto> getAllCategoriesWithPreference(Long userId) {

         List<Category> allCategories = categoryRepository.findAll();
         List<UserCategoryPreference> userCategoryPreferences = userCategoryPreferenceRepository.findAllById_UserId(userId);

        Map<Integer, PreferenceType> prefsMap = userCategoryPreferences.stream()
                .collect(Collectors.toMap(
                        p -> p.getCategory().getIdFromKudaGo(),
                        UserCategoryPreference::getPreferenceType
                ));

        return allCategories.stream()
                .map(category -> UserCategoryPreferenceDto.builder()
                        .id(category.getIdFromKudaGo())
                        .name(category.getName())
                        .slug(category.getSlug())
                        .preference(prefsMap.getOrDefault(category.getIdFromKudaGo(), PreferenceType.NEUTRAL))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setUserPreference(UserCategoryPreferenceDto dto, Long userId){

        UserCategoryPreferencePK pk = new UserCategoryPreferencePK(userId, dto.getId());

        Optional<UserCategoryPreference> existing = userCategoryPreferenceRepository.findById(pk);
        if(dto.getPreference() == PreferenceType.NEUTRAL){
            existing.ifPresent(i -> userCategoryPreferenceRepository.delete(i));
        }
        else{

            UserCategoryPreference preference;

            if(existing.isPresent()){
                preference = existing.get();
            }
            else{
                preference = new UserCategoryPreference();
                preference.setId(pk);
                preference.setUser(userRepository.getReferenceById(userId));
                preference.setCategory(categoryRepository.getReferenceById(dto.getId()));
            }

            preference.setPreferenceType(dto.getPreference());
            userCategoryPreferenceRepository.save(preference);
            return;
        }
    }
}
