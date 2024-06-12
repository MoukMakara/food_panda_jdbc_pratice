package model.dao;

import model.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDaoImpl implements CustomerDao{
    @Override
    public List<Customer> queryAllCustomers() {
        String sql = """
                SELECT * FROM customer
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
            List<Customer> customerList = new ArrayList<>();
            while (resultSet.next()) {
                customerList.add(new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("is_Deleted"),
                        resultSet.getDate("created_date")
                ));
            }
            return customerList;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int deleteCustomerById(int id) {
        String sql = """
                DELETE FROM customer WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "8892"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            Customer customer = findCustomerById(id);
            if(customer == null){
                System.out.println("cannot delete customer with id " + id);
            }else {
                preparedStatement.setInt(1, id);
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("deleted " + rowsAffected + " rows from customer table");
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int updateCustomer(int id) {
        String sql = """
                UPDATE customer 
                    SET name = ? , email = ? , password = ? , is_deleted = ? 
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
            Customer customer = findCustomerById(id);
            if(customer != null){
                System.out.print("input name: ");
                String name = new Scanner(System.in).next();
                System.out.print("input email: ");
                String email = new Scanner(System.in).next();
                System.out.print("input password: ");
                String password = new Scanner(System.in).next();
                System.out.print("input isDeleted: ");
                boolean isDeleted = new Scanner(System.in).nextBoolean();

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.setBoolean(4, isDeleted);
                preparedStatement.setInt(5, id);

                int rowsAffected = preparedStatement.executeUpdate();
                String message = rowsAffected > 0 ? "update is successfully" : "update failed";
                System.out.println(message);
            }else {
                System.out.println("customer with id = " + id + " not found");
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int addNewCustomer(Customer customer) {
        String sql = """
                INSERT INTO customer (name, email, password, is_Deleted, created_date)
                VALUES (?, ?, ?, ?, ?)
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "8892"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPassword());
            preparedStatement.setBoolean(4, customer.getIsDeleted());
            preparedStatement.setDate(5, customer.getCreatedDate());
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected > 0) {
                System.out.println("insert customer successfully");
            }

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public Customer findCustomerById(Integer id) {
        String sql = """
                SELECT * FROM customer WHERE id = ?
                """;
        try(
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "8892");
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Customer customer = null;
            while (resultSet.next()){
                customer = Customer.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .isDeleted(resultSet.getBoolean("is_Deleted"))
                        .createdDate(resultSet.getDate("created_date"))
                        .build();
            }
            return customer;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return null;
    }
}
