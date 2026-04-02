package ru.technocracy.echoserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.technocracy.echoserver.integrations.ExternalClient;
import ru.technocracy.echoserver.models.Category;
import ru.technocracy.echoserver.repositories.CategoryRepository;
import ru.technocracy.echoserver.utils.CategoryMapper;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdatingCategoriesService {

    private final ExternalClient externalClient;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void updateCategories(){
        List<Category> actualCategories = categoryMapper.fromDtoListtoModelList(externalClient.getAllEventCategories());
        List<Category> savedCategory = categoryRepository.findAll();

        actualCategories.removeAll(savedCategory);

        if(!actualCategories.isEmpty()){
            categoryRepository.saveAll(actualCategories);
        }
    }
}
