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

    @Test
    public void categoryDetails() throws Exception {
        given(categoryService.getCategoryByName("cat-foo")).willReturn(new Category("cat-foo", "cat-parent"));

        mockMvc.perform(get("/category/cat-foo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType((MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("name").value("cat-foo"))
                .andExpect(jsonPath("parentId").value("cat-parent"));

        verify(categoryService).getCategoryByName("cat-foo");
    }

    @Test
    public void categorySummary() throws Exception {

        List<Category> testCategories = Arrays.asList(new Category("cat-shirts", "cat-tops"),
                new Category("cat-blouses"));
        given(categoryService.getAllCategories())
                .willReturn(testCategories);

        mockMvc.perform(get("/category"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]name").value("cat-shirts"))
                .andExpect(jsonPath("$[0]parentId").value("cat-tops"))
                .andExpect(jsonPath("$[1]name").value("cat-blouses"))
                .andExpect(jsonPath("$[1]parentId").doesNotExist());

        verify(categoryService).getAllCategories();

    }

    @Test
    public void addCategory() throws Exception {
        Category newCategory = new Category("cat-new");
        given(categoryService.getCategoryByName(any(String.class)))
                .willReturn(null);

        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestHelper.asJsonString(newCategory)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/category/cat-new"));

        verify(categoryService).getCategoryByName(any(String.class));

    }


    @Test
    public void addDuplicateCategory() throws Exception {
        Category newCategory = new Category("cat-new");
        given(categoryService.getCategoryByName(any(String.class)))
                .willReturn(new Category("cat-new", "cat-parent"));

        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestHelper.asJsonString(newCategory)))
                .andExpect(status().isConflict());

        verify(categoryService).getCategoryByName(any(String.class));

    }

    @Test
    public void updateCategory() throws Exception {
        Category category = new Category("cat-new");

        given(categoryService.getCategoryByName("cat-new"))
                .willReturn(category);

        Category requestBody = new Category("cat-main", "cat-parent");

        mockMvc.perform(put("/category/cat-new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.asJsonString(requestBody)))
                .andExpect(status().isNoContent());

        verify(categoryService).getCategoryByName("cat-new");
        assertEquals("cat-main", category.getName());
        assertEquals("cat-parent", category.getParentId());
    }

    @Test
    public void updateNonexistentCategory() throws Exception {

        given(categoryService.getCategoryByName(any(String.class)))
                .willReturn(null);

        Category requestBody = new Category("cat-main", "cat-parent");

        mockMvc.perform(put("/category/cat-new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.asJsonString(requestBody)))
                .andExpect(status().isNotFound());

        verify(categoryService).getCategoryByName("cat-new");
    }

    @Test
    public void deleteCategory() throws Exception {
        Category category = new Category("cat-new");

        given(categoryService.getCategoryByName("cat-new"))
                .willReturn(category);

        mockMvc.perform(delete("/category/cat-new"))
                .andExpect(status().isNoContent());

        verify(categoryService).getCategoryByName("cat-new");

        assertEquals("cat-new", category.getName());
        assertNull(category.getParentId());
    }

    @Test
    public void deleteNonexistentCategory() throws Exception {

        given(categoryService.getCategoryByName("cat-new"))
                .willReturn(null);

        mockMvc.perform(delete("/category/cat-new"))
                .andExpect(status().isNotFound());

        verify(categoryService).getCategoryByName("cat-new");
    }
}
