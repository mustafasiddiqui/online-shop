package com.example.shop.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

}
