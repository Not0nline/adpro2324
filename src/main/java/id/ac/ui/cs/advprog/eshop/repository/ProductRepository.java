package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import javax.management.openmbean.InvalidKeyException;
import java.util.*;

@Repository
public class ProductRepository {
    static int n = 1;
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        product.setProductId(Integer.toString(n++));
        return product;
    }

    public boolean delete(String productId) {
        int index = find(productId);
        if (index == -1) {
            return false;
        }
        productData.remove(index);
        return true;
    }

    public boolean edit(Product product) {
        int index = find(product.getProductId());
        if (index == -1) {
            return false;
        }
        Product currProduct = productData.get(index);
        currProduct.setProductName(product.getProductName());
        currProduct.setProductQuantity(product.getProductQuantity());
        return true;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    } 

    public int find(String productId) {
        int index = 0;
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return index;
            }
            index += 1;
        }
        return -1;
    }

    public Product getProduct(String productId) throws InvalidKeyException {
        int index=find(productId);
        if (index==-1) throw new InvalidKeyException();
        else {
            return productData.get(index);
        }
    }
}
