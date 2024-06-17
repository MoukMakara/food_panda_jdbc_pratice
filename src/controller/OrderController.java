package controller;

import model.entity.Order;
import model.service.OrderService;

import java.util.List;

public class OrderController {
    private final OrderService orderService = new OrderService();

    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    public void addNewOrder(Order order) {
        orderService.addNewOrder(order);
    }

    public void updateOrder(Integer id) {
        orderService.updateOrder(id);
    }

    public void deleteOrder(Integer id) {
        orderService.deleteOrder(id);
    }

    public Order getOrderById(Integer id) {
        return orderService.getOrderById(id);
    }
}
