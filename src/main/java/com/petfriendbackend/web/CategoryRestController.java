package com.petfriendbackend.web;

import com.petfriendbackend.model.Category;
import com.petfriendbackend.model.dto.CategoryDto;
import com.petfriendbackend.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> listCategories(){
        return this.categoryService.listCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return this.categoryService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Category> create(@RequestBody CategoryDto categoryDto) {
        return this.categoryService.create(categoryDto)
                .map(pet -> ResponseEntity.ok().body(pet))
                .orElseGet(() ->  ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return this.categoryService.update(id, categoryDto)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        this.categoryService.deleteById(id);
        if(this.categoryService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
