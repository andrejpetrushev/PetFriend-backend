package com.petfriendbackend.service;

import com.petfriendbackend.model.Category;
import com.petfriendbackend.model.Pet;
import com.petfriendbackend.model.dto.CategoryDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    Optional<Category> findById(Long id);

    Category create(String name, String description, Set<Pet> pets);

    Optional<Category> create(CategoryDto categoryDto);

    Category update(Long id, String name, String description, Set<Pet> pets);

    Optional<Category> update(Long id, CategoryDto categoryDto);

    void delete(String name);

    void deleteById(Long id);

    List<Category> listCategories();

    List<Category> searchCategories(String searchText);
}
