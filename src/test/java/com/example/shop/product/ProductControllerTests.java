package com.example.shop.product;

import com.example.shop.category.Category;
import com.example.shop.util.TestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private static final String RANDOM_ID = "randomId";

    @Test
    public void productDetails() throws Exception {
        List<Category> categories = Arrays.asList(new Category("id1", "category-tops", "category-parent"),
                new Category("id2", "category-men", "category-root"));
        Product product = new Product(RANDOM_ID, "product-shirt", "SKU-1234", categories);
        given(productService.getProductById(RANDOM_ID)).willReturn(product);

        mockMvc.perform(get("/product/randomId"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType((MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("id").value(RANDOM_ID))
                .andExpect(jsonPath("name").value("product-shirt"))
                .andExpect(jsonPath("sku").value("SKU-1234"))
                .andExpect(jsonPath("$.categories[0]id").value("id1"))
                .andExpect(jsonPath("$.categories[0]name").value("category-tops"))
                .andExpect(jsonPath("$.categories[0]parentId").value("category-parent"))
                .andExpect(jsonPath("$.categories[1]id").value("id2"))
                .andExpect(jsonPath("$.categories[1]name").value("category-men"))
                .andExpect(jsonPath("$.categories[1]parentId").value("category-root"));

        verify(productService).getProductById(RANDOM_ID);
    }

    @Test
    public void productSummary() throws Exception {
        List<Category> categories = Arrays.asList(new Category("id1", "category-tops", "category-parent"),
                new Category("id2", "category-men", "category-root"));

        List<Product> testProducts = Arrays.asList(new Product(RANDOM_ID, "product-shirt", "SKU-1234", categories),
                new Product("randomId2", "product-blouse", "SKU-1235", categories));
        given(productService.getAllProducts())
                .willReturn(testProducts);

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]id").value(RANDOM_ID))
                .andExpect(jsonPath("$[0]name").value("product-shirt"))
                .andExpect(jsonPath("$[0]sku").value("SKU-1234"))
                .andExpect(jsonPath("$[0]categories[0]id").value("id1"))
                .andExpect(jsonPath("$[0]categories[0]name").value("category-tops"))
                .andExpect(jsonPath("$[0]categories[0]parentId").value("category-parent"))
                .andExpect(jsonPath("$[1]id").value("randomId2"))
                .andExpect(jsonPath("$[1]name").value("product-blouse"))
                .andExpect(jsonPath("$[1]sku").value("SKU-1235"))
                .andExpect(jsonPath("$[1]categories[1]id").value("id2"))
                .andExpect(jsonPath("$[1]categories[1]name").value("category-men"))
                .andExpect(jsonPath("$[1]categories[1]parentId").value("category-root"));

        verify(productService).getAllProducts();
    }

    @Test
    public void addProduct() throws Exception {
        Product newProduct = new Product(RANDOM_ID, "product-new", "sku-new");
        given(productService.getProductByName("product-new"))
                .willReturn(null);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestHelper.asJsonString(newProduct)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/product/randomId"));

        verify(productService).getProductByName("product-new");
    }

    @Test
    public void addDuplicateProduct() throws Exception {
        Product newProduct = new Product(RANDOM_ID, "product-new", "sku-new");
        given(productService.getProductByName("product-new"))
                .willReturn(new Product(null, "product-new", null, null));

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestHelper.asJsonString(newProduct)))
                .andExpect(status().isConflict());

        verify(productService).getProductByName("product-new");
    }

    @Test
    public void updateProduct() throws Exception {
        Product product = new Product(RANDOM_ID, "product-shirt", null, null);
        given(productService.getProductById(RANDOM_ID))
                .willReturn(product);

        Product requestBody = new Product(null, "name-new", "SKU-new");

        mockMvc.perform(put("/product/randomId")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestHelper.asJsonString(requestBody)))
                .andExpect(status().isNoContent());

        verify(productService).getProductById(RANDOM_ID);
    }

    @Test
    public void updateNonexistentProduct() throws Exception {

        given(productService.getProductById(RANDOM_ID))
                .willReturn(null);

        Product requestBody = new Product(null, "name-new", "SKU-new");

        mockMvc.perform(put("/product/randomId")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestHelper.asJsonString(requestBody)))
                .andExpect(status().isNotFound());

        verify(productService).getProductById(RANDOM_ID);
    }

    @Test
    public void deleteProduct() throws Exception {
        Product product = new Product(RANDOM_ID, "product-shirt", null, null);
        given(productService.getProductById(RANDOM_ID))
                .willReturn(product);

        mockMvc.perform(delete("/product/randomId"))
                .andExpect(status().isNoContent());

        verify(productService).getProductById(RANDOM_ID);
    }

    @Test
    public void deleteNonexistentProduct() throws Exception {

        given(productService.getProductById(RANDOM_ID))
                .willReturn(null);

        mockMvc.perform(delete("/product/randomId"))
                .andExpect(status().isNotFound());

        verify(productService).getProductById(RANDOM_ID);
    }

}
