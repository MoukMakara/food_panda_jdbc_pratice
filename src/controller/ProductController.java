package controller;

import model.entity.Product;
import model.service.ProductService;

import java.util.List;

public class ProductController {
    private final ProductService productService = new ProductService();

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public void addNewProduct(Product product) {
        productService.addNewProduct(product);
    }

    public void updateProduct(Integer id) {
        productService.updateProduct(id);
    }

    public void deleteProduct(Integer id) {
        productService.deleteProduct(id);
    }

    public Product getProductById(Integer id) {
        return productService.getProductById(id);
    }
}
