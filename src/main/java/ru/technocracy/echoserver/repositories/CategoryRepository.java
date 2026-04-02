package ru.technocracy.echoserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.technocracy.echoserver.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
