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
