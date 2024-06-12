import model.dao.CustomerDaoImpl;
import model.dao.OrderDaoImpl;
import model.dao.ProductDaoImpl;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        /**
         * table product
         * */
//        new ProductDaoImpl().addNewProduct(Product.builder()
//                        .id(1)
//                        .productName("Milk-Tea")
//                        .productCode("Coffee-1001")
//                        .isDeleted(false)
//                        .importedAt(Date.valueOf(LocalDate.now()))
//                        .expiredAt(Date.valueOf(LocalDate.now()))
//                        .productDescription("coffee is your life")
//                .build());
//
//        new ProductDaoImpl().addNewProduct(Product.builder()
//                .id(2)
//                .productName("Hot-Tea")
//                .productCode("Coffee-1002")
//                .isDeleted(false)
//                .importedAt(Date.valueOf(LocalDate.now()))
//                .expiredAt(Date.valueOf(LocalDate.now()))
//                .productDescription("coffee is your life")
//                .build());

        new ProductDaoImpl().queryAllProducts().forEach(System.out::println);

//        new ProductDaoImpl().deleteProductById(1);
        new ProductDaoImpl().updateProductById(2);

        new ProductDaoImpl().queryAllProducts().forEach(System.out::println);
        /////////////////////////////////////////////////

//        new CustomerDaoImpl()
//                .addNewCustomer(Customer.builder()
//                        .id(1)
//                        .name("Mouk Makara")
//                        .email("moukmakara@gmail.com")
//                        .password("*&^%$#@!!@#$%^&*")
//                        .isDeleted(false)
//                        .createdDate(Date.valueOf(LocalDate.now()))
//                        .build());
//
//        new CustomerDaoImpl()
//                .addNewCustomer(Customer.builder()
//                        .id(2)
//                        .name("Pich Lyhour")
//                        .email("pichlyhour@gmail.com")
//                        .password("*&^%$#@!!@#$%^&*")
//                        .isDeleted(false)
//                        .createdDate(Date.valueOf(LocalDate.now()))
//                        .build());
//
//        new CustomerDaoImpl()
//                .addNewCustomer(Customer.builder()
//                        .id(3)
//                        .name("Chan Bopha")
//                        .email("chanbopha@gmail.com")
//                        .password("*&^%$#@!!@#$%^&")
//                        .isDeleted(false)
//                        .createdDate(Date.valueOf(LocalDate.now()))
//                        .build());
//        /**
//         * queryAllCustomers
//         * */
//        System.out.println("before");
//        new CustomerDaoImpl().queryAllCustomers()
//        .forEach(System.out::println);

        /**
         * delete customer
         * */
//        new CustomerDaoImpl().deleteCustomerById(2);
//        new CustomerDaoImpl().updateCustomer(3);
//
//        System.out.println("after");
//        new CustomerDaoImpl().queryAllCustomers()
//                .forEach(System.out::println);

        /**
         * table Order
         * */
        /**
         * addNewOrders
         * */
//        new OrderDaoImpl().addNewOrder(Order.builder()
//                        .id(1)
//                        .orderName("Ice-Latte")
//                        .orderDescription("ice")
//                        .customer(Customer.builder()
//                                .id(1)
//                                .build())
//                        .orderedAt(Date.valueOf(LocalDate.now()))
//                .build());
//
//        new OrderDaoImpl().addNewOrder(Order.builder()
//                .id(2)
//                .orderName("Hot-Latte")
//                .orderDescription("hot")
//                .customer(Customer.builder()
//                        .id(3)
//                        .build())
//                .orderedAt(Date.valueOf(LocalDate.now()))
//                .build());
//
//        new OrderDaoImpl().addNewOrder(Order.builder()
//                .id(3)
//                .orderName("Ice-Green-Latte")
//                .orderDescription("ice")
//                .customer(Customer.builder()
//                        .id(1)
//                        .build())
//                .orderedAt(Date.valueOf(LocalDate.now()))
//                .build());

//        new OrderDaoImpl().queryAllOrders().forEach(System.out::println);
    }
}