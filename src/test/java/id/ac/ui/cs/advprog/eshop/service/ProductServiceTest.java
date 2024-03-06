package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    ProductRepository productRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    @InjectMocks
    ProductServiceImpl service;

    private List<Product> allProducts;

    @BeforeEach
    void setUp(){
        allProducts = new ArrayList<>();
        createAndSaveProduct("nama","id",1);
    }

    Product createAndSaveProduct(String name, String id, int quantity){
        Product product = createProduct(name,id,quantity);
        service.create(product);
        allProducts.add(product);

        return product;
    }

    @AfterEach
    void deleteProduct(){
        allProducts = null;
    }

    boolean editRepoMock(Product product){
        for(Product products : allProducts){
            if (products.getProductId().equals(product.getProductId())){
                products.setProductName(product.getProductName());
                products.setProductQuantity(product.getProductQuantity());
                return true;
            }
        }
        return false;
    }

    Product createProduct(String name, String id, int quantity){
        Product product = new Product();
        product.setProductId(id);
        product.setProductName(name);
        product.setProductQuantity(quantity);

        return product;
    }

    @Test
    void testCreateProduct(){
        createAndSaveProduct("nama","id",1);
        when(productRepository.findAll()).thenReturn(allProducts.iterator());
        List<Product> list = service.findAll();

    }

    @Test
    void testFindAllProduct(){
        createAndSaveProduct("namaaaa","idLagi",11);
        when(productRepository.findAll()).thenReturn(allProducts.iterator());
        List<Product> list = service.findAll();
    }

    @Test
    void testEditProduct(){
        Product product2 = createAndSaveProduct("namaaaa", "idLagi",11);
        Product product3 = createProduct("namaaLagi","idLagiLagi",3);

        when(productRepository.edit(product3)).thenReturn(editRepoMock(product3));
        boolean hasEdit = service.edit(product3);
        when(productRepository.getProduct(product2.getProductId())).thenReturn(product2);
        Product resultEdit = null;
        try {
            resultEdit = service.getProduct(product2.getProductId());
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDeleteProduct(){
        Product product2 = createAndSaveProduct("mau", "delete",22);
        when(productRepository.delete("delete")).thenReturn(allProducts.remove(product2));
        boolean hasDelete = service.delete("delete");

        when(productRepository.findAll()).thenReturn(allProducts.iterator());
        List<Product> list = service.findAll();
    }
}