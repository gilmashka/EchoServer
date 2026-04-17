package ru.technocracy.echoserver.utils;

import org.springframework.stereotype.Component;
import ru.technocracy.echoserver.dto.CategoryFromKudaGoDto;
import ru.technocracy.echoserver.models.Category;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public List<Category> fromDtoListtoModelList(List<CategoryFromKudaGoDto> categoryFromKudaGoDtos){
        return categoryFromKudaGoDtos.stream()
                .map(
                        categoryFromKudaGoDto -> Category.builder()
                                .idFromKudaGo(categoryFromKudaGoDto.getId())
                                .name(categoryFromKudaGoDto.getName())
                                .slug(categoryFromKudaGoDto.getSlug())
                                .build())
                .collect(Collectors.toList());
    }
}
