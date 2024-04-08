package pl.klosowska.ecommerce.catalog;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.List;
import java.util.UUID;

public class HashMapProductStorageTest {
    @Test
    void itAllowsToStoreProduct(){
        //Arrange
        Product product = thereIsExampleProduct();
        ProductStorage hashmapStorage = thereIsHashMapStorage();
        // Act
        hashmapStorage.add(product);
        // Assert
        List<Product> products = hashmapStorage.allProducts();
        assertThat(products)
                .hasSize(1)
                .extracting(Product::getName)
                .contains("test-it");
    }


    @Test
    void itAllowsToLoadAllProducts() {
    }

    @Test
    void itAllowsToLoadProductById() {
    }

    @Test
    void thereIsHashMapStorage() {
    }

    @Test
    void thereIsExampleProduct() {
    }
}
