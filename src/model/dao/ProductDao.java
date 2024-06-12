package model.dao;

import model.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> queryAllProducts();
    int addNewProduct(Product product);
    int updateProductById(Integer id);
    int deleteProductById(Integer id);
    Product findProductById(Integer id);
}
