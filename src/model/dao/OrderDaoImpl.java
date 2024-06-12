package model.dao;

import model.entity.Customer;
import model.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao{
    @Override
    public List<Order> queryAllOrders() {
        String sql = """
                SELECT * FROM "order"
                INNER JOIN customer c ON "order".cus_id = c.id;
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "8892"
                );
                Statement statement = connection.createStatement();
        ) {

            ResultSet resultSet = statement.executeQuery(sql);
            List<Order> orderList = new ArrayList<>();
            while (resultSet.next()) {
                orderList.add(Order.builder()
                                .id(resultSet.getInt("id"))
                                .orderName(resultSet.getString("order_name"))
                                .orderDescription(resultSet.getString("order_description"))
                                .orderedAt(resultSet.getDate("ordered_at"))
                                .customer(Customer.builder()
                                        .id(resultSet.getInt("cus_id"))
                                        .name(resultSet.getString("name"))
                                        .email(resultSet.getString("password"))
                                        .isDeleted(resultSet.getBoolean("is_deleted"))
                                        .createdDate(resultSet.getDate("created_date"))
                                        .build())
                        .build());
            }
            return orderList;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public int addNewOrder(Order order) {
        String sql = """
                INSERT INTO "order" (order_name, order_description, cus_id, ordered_at)
            VALUES (?, ?, ?, ?)
            """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "8892"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            preparedStatement.setString(1, order.getOrderName());
            preparedStatement.setString(2, order.getOrderDescription());
            preparedStatement.setInt(3, order.getCustomer().getId());
            preparedStatement.setDate(4, order.getOrderedAt());

            int rowsAffected = preparedStatement.executeUpdate();
            String message = rowsAffected > 0 ? "Order added successfully" : "Order added failed";
            System.out.println(message);
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int updateOrderById(Integer id) {
        return 0;
    }

    @Override
    public int deleteOrderById(Integer id) {
        return 0;
    }

    @Override
    public int findOrderById(Integer id) {
        return 0;
    }
}
