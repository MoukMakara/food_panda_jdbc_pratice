package model.service;

import model.dao.ProductDao;
import model.dao.ProductDaoImpl;
import model.entity.Product;

import java.util.List;

public class ProductService {
    private final ProductDao productDao = new ProductDaoImpl();

    public List<Product> getAllProducts() {
        return productDao.queryAllProducts();
    }

    public void addNewProduct(Product product) {
        productDao.addNewProduct(product);
    }

    public void updateProduct(Integer id) {
        productDao.updateProductById(id);
    }

    public void deleteProduct(Integer id) {
        productDao.deleteProductById(id);
    }

    public Product getProductById(Integer id) {
        return productDao.findProductById(id);
    }
}
