package ru.technocracy.echoserver.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.technocracy.echoserver.integrations.ExternalClient;
import ru.technocracy.echoserver.dto.CategoryFromKudaGoDto;
import java.util.List;

@Service
@RequiredArgsConstructor // Создаст конструктор для внедрения ExternalClient
@Slf4j // Добавит логгер
public class TestApiService {

    private final ExternalClient externalClient;

    @PostConstruct
    public void testApi() {
        log.info("Запрашиваем категории у KudaGo...");

        try {
            List<CategoryFromKudaGoDto> categories = externalClient.getAllEventCategories();

            log.info("Получено категорий: {}", categories.size());
            categories.forEach(cat -> log.info("ID: {}, Название: {}", cat.getId(), cat.getName()));

        } catch (Exception e) {
            log.error("Ошибка при запросе к API: {}", e.getMessage());
        }
    }
}
