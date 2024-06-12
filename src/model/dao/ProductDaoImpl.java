package model.dao;

import model.entity.Customer;
import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> queryAllProducts() {
        String sql = """
                SELECT * FROM product
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "8892"
                );
//                PreparedStatement preparedStatement = (PreparedStatement) connection.createStatement();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                productList.add(Product.builder()
                                .id(resultSet.getInt("id"))
                                .productName(resultSet.getString("product_name"))
                                .productCode(resultSet.getString("product_code"))
                                .isDeleted(resultSet.getBoolean("is_deleted"))
                                .importedAt(resultSet.getDate("imported_at"))
                                .expiredAt(resultSet.getDate("expired_at"))
                                .productDescription(resultSet.getString("product_description"))
                        .build());
            }
            return productList;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int addNewProduct(Product product) {
        String sql = """
                INSERT INTO "product" (product_name, product_code, is_Deleted, imported_at, expired_at, product_description)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "8892"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductCode());
            preparedStatement.setBoolean(3, product.getIsDeleted());
            preparedStatement.setDate(4, product.getImportedAt());
            preparedStatement.setDate(5, product.getExpiredAt());
            preparedStatement.setString(6, product.getProductDescription());

            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected > 0) {
                System.out.println("insert product successfully");
            }

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int updateProductById(Integer id) {
        String sql = """
                UPDATE product 
                    SET product_name = ? , product_code = ? , is_deleted = ? , product_description = ? 
                        WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "8892"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            Product product = findProductById(id);
            if(product != null){
                System.out.print("input product_name: ");
                String product_name = new Scanner(System.in).next();
                System.out.print("input product_code: ");
                String product_code = new Scanner(System.in).next();
                System.out.print("input isDeleted: ");
                boolean isDeleted = new Scanner(System.in).nextBoolean();
                System.out.print("input product_description: ");
                String product_description  = new Scanner(System.in).next();

                preparedStatement.setString(1, product_name);
                preparedStatement.setString(2, product_code);
                preparedStatement.setBoolean(3, isDeleted);
                preparedStatement.setString(4, product_description);
                preparedStatement.setInt(5, id);

                int rowsAffected = preparedStatement.executeUpdate();
                String message = rowsAffected > 0 ? "update is successfully" : "update failed";
                System.out.println(message);
            }else {
                System.out.println("product with id = " + id + " not found");
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteProductById(Integer id) {
        String sql = """
                DELETE FROM product WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "8892"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            Product product = findProductById(id);
            if(product == null){
                System.out.println("cannot delete product with id " + id);
            }else {
                preparedStatement.setInt(1, id);
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("deleted " + rowsAffected + " rows from product table");
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public Product findProductById(Integer id) {
        String sql = """
                SELECT * FROM product WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "8892");
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Product product = null;
            while (resultSet.next()){
                product = Product.builder()
                        .id(resultSet.getInt("id"))
                        .productName(resultSet.getString("product_name"))
                        .productCode(resultSet.getString("product_code"))
                        .isDeleted(resultSet.getBoolean("is_Deleted"))
                        .importedAt(resultSet.getDate("imported_at"))
                        .expiredAt(resultSet.getDate("expired_at"))
                        .productDescription(resultSet.getString("product_description"))
                        .build();
            }
            return product;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }
}
