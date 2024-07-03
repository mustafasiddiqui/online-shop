package com.example.shop.category;

import com.example.shop.util.TestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private static final String RANDOM_ID = "randomId";

    Category parentCategory = new Category("id1", "category-parent", null);

    @Test
    public void categoryDetails() throws Exception {
        given(categoryService.getCategoryById(RANDOM_ID)).willReturn(new Category("cat-foo", parentCategory));

        mockMvc.perform(get("/category/randomId"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType((MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("name").value("cat-foo"))
                .andExpect(jsonPath("parentCategory.id").value("id1"))
                .andExpect(jsonPath("parentCategory.name").value("category-parent"))
                .andExpect(jsonPath("parentCategory.parentCategory").doesNotExist());

        verify(categoryService).getCategoryById(RANDOM_ID);
    }

    @Test
    public void categorySummary() throws Exception {

        List<Category> testCategories = Arrays.asList(new Category("cat-shirts", parentCategory),
                new Category("cat-blouses"));
        given(categoryService.getAllCategories())
                .willReturn(testCategories);

        mockMvc.perform(get("/category"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]id").doesNotExist())
                .andExpect(jsonPath("$[0]name").value("cat-shirts"))
                .andExpect(jsonPath("$[0]parentCategory.id").value("id1"))
                .andExpect(jsonPath("$[0]parentCategory.name").value("category-parent"))
                .andExpect(jsonPath("$[0]parentCategory.parentCategory").doesNotExist())
                .andExpect(jsonPath("$[1]id").doesNotExist())
                .andExpect(jsonPath("$[1]name").value("cat-blouses"))
                .andExpect(jsonPath("$[1]parentCategory").doesNotExist());

        verify(categoryService).getAllCategories();

    }

    @Test
    public void addCategory() throws Exception {
        Category newCategory = new Category("cat-new");
        newCategory.setId(RANDOM_ID);
        given(categoryService.getCategoryByName(any(String.class)))
                .willReturn(null);

        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestHelper.asJsonString(newCategory)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/category/randomId"));

        verify(categoryService).getCategoryByName(any(String.class));

    }


    @Test
    public void addDuplicateCategory() throws Exception {
        Category newCategory = new Category("cat-new");
        given(categoryService.getCategoryByName(any(String.class)))
                .willReturn(new Category("cat-new", parentCategory));

        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestHelper.asJsonString(newCategory)))
                .andExpect(status().isConflict());

        verify(categoryService).getCategoryByName(any(String.class));

    }

    @Test
    public void updateCategory() throws Exception {
        Category category = new Category("cat-main");
        category.setId(RANDOM_ID);

        given(categoryService.getCategoryById(RANDOM_ID))
                .willReturn(category);

        Category requestBody = new Category("cat-new", parentCategory);

        mockMvc.perform(put("/category/randomId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.asJsonString(requestBody)))
                .andExpect(status().isNoContent());

        verify(categoryService).getCategoryById(RANDOM_ID);
        assertEquals(RANDOM_ID, category.getId());
        assertEquals("cat-new", category.getName());
        assertEquals("id1", category.getParentCategory().getId());
        assertEquals("category-parent", category.getParentCategory().getName());
        assertNull(category.getParentCategory().getParentCategory());
    }

    @Test
    public void updateNonexistentCategory() throws Exception {

        given(categoryService.getCategoryById(any(String.class)))
                .willReturn(null);

        Category requestBody = new Category("cat-new", parentCategory);

        mockMvc.perform(put("/category/randomId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.asJsonString(requestBody)))
                .andExpect(status().isNotFound());

        verify(categoryService).getCategoryById(RANDOM_ID);
    }

    @Test
    public void deleteCategory() throws Exception {
        Category category = new Category("cat-new");
        category.setId(RANDOM_ID);

        given(categoryService.getCategoryById(RANDOM_ID))
                .willReturn(category);

        mockMvc.perform(delete("/category/randomId"))
                .andExpect(status().isNoContent());

        verify(categoryService).getCategoryById(RANDOM_ID);

        assertEquals("cat-new", category.getName());
        assertNull(category.getParentCategory());
    }

    @Test
    public void deleteNonexistentCategory() throws Exception {

        given(categoryService.getCategoryById(any(String.class)))
                .willReturn(null);

        mockMvc.perform(delete("/category/randomId"))
                .andExpect(status().isNotFound());

        verify(categoryService).getCategoryById(RANDOM_ID);
    }
}
