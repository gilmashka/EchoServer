package ru.technocracy.echoserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.technocracy.echoserver.dto.UserCategoryPreferenceDto;
import ru.technocracy.echoserver.services.CategoryService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserCategoryPreferenceDto>> getAll(@PathVariable Long userId){
        List<UserCategoryPreferenceDto> categories =  categoryService.getAllCategoriesWithPreference(userId);
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/{userId}/preference")
    public ResponseEntity<Void> setUserPreference(
            @PathVariable Long userId,
            @RequestBody UserCategoryPreferenceDto dto) {

        categoryService.setUserPreference(dto, userId);
        return ResponseEntity.status(201).build();
    }
}
