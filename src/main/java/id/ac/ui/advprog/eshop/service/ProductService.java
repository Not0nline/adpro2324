package id.ac.ui.advprog.eshop.service;

import id.ac.ui.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
}
