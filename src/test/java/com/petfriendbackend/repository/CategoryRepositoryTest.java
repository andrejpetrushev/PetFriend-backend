package com.petfriendbackend.repository;

import com.petfriendbackend.model.Category;
import com.petfriendbackend.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    public static final String CATEGORY_NAME = "Category";
    public static final String CATEGORY_DESCRIPTION = "Category Description";

    public static final String NAME = "Category";

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Before
    public void init() {
        if (categoryRepository.count() == 0) {
            Category category = getCategory();
            categoryRepository.save(category);
        }
    }

    @Test
    public void testFindAllByNameLike() {
        List<Category> category = this.categoryService.searchCategories(NAME);
        assertEquals(CATEGORY_NAME, category.get(0).getName());
    }

    @Test
    public void testDeleteByName() {
        if (this.categoryRepository.findAll().size() != 0) {
            this.categoryService.delete(NAME);
            assertEquals(0, this.categoryRepository.findAll().size());
        }
    }

    private Category getCategory() {
        Category category = new Category();
        category.setName(CATEGORY_NAME);
        category.setDescription(CATEGORY_DESCRIPTION);

        return category;
    }
}
