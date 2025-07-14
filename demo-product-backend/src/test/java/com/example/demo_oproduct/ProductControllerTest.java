package com.example.demo_oproduct;

import com.example.demo_oproduct.authorization.jwt.JwtService;
import com.example.demo_oproduct.product.Product;
import com.example.demo_oproduct.product.ProductController;
import com.example.demo_oproduct.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;


    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private Product getValidProduct() {
        return Product.builder()
                .name("Sample Product")
                .description("Sample Description")
                .price(1200.0)
                .category("Sample Category")
                .build();
    }

    @Test
    void givenValidProduct_whenCreateProduct_thenReturnOk() throws Exception {
        Product validProduct = getValidProduct();

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validProduct)))
                .andExpect(status().isOk());
    }

    @Test
    void givenEmptyName_whenCreateProduct_thenReturnBadRequest() throws Exception {
        Product product = getValidProduct();
        product.setName("");

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenEmptyDescription_whenCreateProduct_thenReturnBadRequest() throws Exception {
        Product product = getValidProduct();
        product.setDescription("");

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenNegativePrice_whenCreateProduct_thenReturnBadRequest() throws Exception {
        Product product = getValidProduct();
        product.setPrice(-10.0);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenEmptyCategory_whenCreateProduct_thenReturnBadRequest() throws Exception {
        Product product = getValidProduct();
        product.setCategory("");

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenValidProduct_whenUpdateProduct_thenReturnOk() throws Exception {
        Product validProduct = getValidProduct();
        String id = UUID.randomUUID().toString();

        mockMvc.perform(put("/products/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validProduct)))
                .andExpect(status().isOk());
    }

    @Test
    void givenInvalidName_whenUpdateProduct_thenReturnBadRequest() throws Exception {
        Product product = getValidProduct();
        product.setName("");
        String id = UUID.randomUUID().toString();

        mockMvc.perform(put("/products/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenInvalidDescription_whenUpdateProduct_thenReturnBadRequest() throws Exception {
        Product product = getValidProduct();
        product.setDescription("");
        String id = UUID.randomUUID().toString();

        mockMvc.perform(put("/products/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenNegativePrice_whenUpdateProduct_thenReturnBadRequest() throws Exception {
        Product product = getValidProduct();
        product.setPrice(-5.0);
        String id = UUID.randomUUID().toString();

        mockMvc.perform(put("/products/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenInvalidCategory_whenUpdateProduct_thenReturnBadRequest() throws Exception {
        Product product = getValidProduct();
        product.setCategory("");
        String id = UUID.randomUUID().toString();

        mockMvc.perform(put("/products/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isBadRequest());
    }
}
