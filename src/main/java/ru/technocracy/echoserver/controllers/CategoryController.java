package ru.technocracy.echoserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.technocracy.echoserver.dto.UserCategoryPreferenceDto;
import ru.technocracy.echoserver.models.User;
import ru.technocracy.echoserver.services.CategoryService;
import ru.technocracy.echoserver.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserCategoryPreferenceDto>> getAll(Principal principal){
        User user = userService.getUserByNickname(principal.getName());

        List<UserCategoryPreferenceDto> categories =  categoryService.getAllCategoriesWithPreference(user.getId());
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/preference")
    public ResponseEntity<Void> setUserPreference(
            Principal principal,
            @RequestBody UserCategoryPreferenceDto dto) {

        User user = userService.getUserByNickname(principal.getName());

        categoryService.setUserPreference(dto, user.getId());
        return ResponseEntity.status(201).build();
    }
}
