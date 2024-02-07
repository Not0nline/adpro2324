package id.ac.ui.advprog.eshop.service;


import id.ac.ui.advprog.eshop.model.Product;
import id.ac.ui.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.util.*;


@Service
public class ProductServiceImpl implements ProductService {

    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public boolean delete(String productId) {
        return productRepository.delete(productId);
    }

    @Override
    public boolean edit(Product product) {
        return productRepository.edit(product);
    }

    @Override
    public Product getProduct(String productId) throws InvalidKeyException {
        return productRepository.getProduct(productId);
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct :: add);
        return allProduct;
    }
}
