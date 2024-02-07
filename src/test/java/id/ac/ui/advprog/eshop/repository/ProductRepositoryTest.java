package id.ac.ui.advprog.eshop.repository;
import id.ac.ui.advprog.eshop.model.Product;
import id.ac.ui.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp(){}

    Product createAndSaveProduct(String name, String id, int quantity){
        Product product = createProduct(name,id,quantity);
        productRepository.create(product);
        product.setProductId(id); // productRepo.create changes the id

        return product;
    }

    Product createProduct(String name, String id, int quantity){
        Product product = new Product();
        product.setProductId(id);
        product.setProductName(name);
        product.setProductQuantity(quantity);

        return product;
    }

    @Test
    void testCreateAndFind(){
        Product product = createAndSaveProduct("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        assertEquals(savedProduct.getProductId(), product.getProductId());
        assertEquals(savedProduct.getProductName(), product.getProductName());
        assertEquals(savedProduct.getProductQuantity(), product.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = createAndSaveProduct("Sampo Cap Bambang","eb558e9f-1c39-460e-8860-71af6af63bd6",100);

        Product product2 = createAndSaveProduct("Sampo Cap Usep","a0f9de45-90b1-437d-a0bf-d0821dde9096",50);

        Iterator<Product> productIterator = productRepository.findAll();
      
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

}