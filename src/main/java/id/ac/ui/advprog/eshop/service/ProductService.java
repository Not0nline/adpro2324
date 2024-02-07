package id.ac.ui.advprog.eshop.service;

import id.ac.ui.advprog.eshop.model.Product;

import java.security.InvalidKeyException;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public boolean delete(String product);
    public boolean edit(Product product);
    public Product getProduct(String productId) throws InvalidKeyException;
    public List<Product> findAll();
}
