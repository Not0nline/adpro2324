package id.ac.ui.advprog.eshop.repository;

import id.ac.ui.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    private Product create(Product product) {
        productData.add(product);
        return product;
    }

    private Iterator<Product> findAll() {
        return productData.iterator();
    } 
}
