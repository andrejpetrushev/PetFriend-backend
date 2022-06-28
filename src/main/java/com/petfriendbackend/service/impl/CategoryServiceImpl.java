package com.petfriendbackend.service.impl;

import com.petfriendbackend.model.Category;
import com.petfriendbackend.model.Pet;
import com.petfriendbackend.model.dto.CategoryDto;
import com.petfriendbackend.model.exceptions.CategoryNotFoundException;
import com.petfriendbackend.repository.CategoryRepository;
import com.petfriendbackend.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    @Transactional
    public Category create(String name, String description, Set<Pet> pets) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,description,pets);
        this.categoryRepository.save(c);
        return c;
    }

    @Override
    public Optional<Category> create(CategoryDto categoryDto) {
        Category cat = new Category();

        cat.setName(categoryDto.getName());
        cat.setDescription(categoryDto.getDescription());
        cat.setDescription(categoryDto.getPets().toString());

        this.categoryRepository.save(cat);
        return Optional.of(cat);
    }

    @Override
    @Transactional
    public Category update(Long id, String name, String description, Set<Pet> pets) {
        Category c = this.categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

        c.setName(name);
        c.setDescription(description);
        c.setPets(pets);

        this.categoryRepository.save(c);
        return c;
    }

    @Override
    public Optional<Category> update(Long id, CategoryDto categoryDto) {
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setPets(categoryDto.getPets());

        this.categoryRepository.save(category);
        return Optional.of(category);
    }

    @Override
    @Transactional
    public void delete(String name) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteByName(name);
    }

    @Override
    public void deleteById(Long id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }
}
