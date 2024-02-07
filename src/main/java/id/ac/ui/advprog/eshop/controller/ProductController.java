package id.ac.ui.advprog.eshop.controller;

import id.ac.ui.advprog.eshop.model.Product;
import id.ac.ui.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    
    @Autowired
    private ProductService service;

    @GetMapping("")
    public String main(Model model) {
        return "main";
    }


    @GetMapping("/product/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/product/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @RequestMapping(value="/product/edit/{id}", method = RequestMethod.GET)
    public String editProductReq(Model model, @PathVariable("id") String productId) {
        try {
            Product product = service.getProduct(productId);
            model.addAttribute("product", product);
            return "editProduct";
        } catch (Exception e) {
            return "redirect:../list";
        }
    }

    @PostMapping(value="product/edit/{id}")
    public String editProductPost(@ModelAttribute Product product, Model model, @PathVariable("id") String productId) {
        product.setProductId(productId);
        service.edit(product);
        return "redirect:../list";
    }

    @GetMapping("/product/delete/{idDelete}")
    public String deleteProduct(Model model, @PathVariable String idDelete) {
        service.delete(idDelete);
        return "redirect:../list";
    }



    @GetMapping("/product/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }
}
