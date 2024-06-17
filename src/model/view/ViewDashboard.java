package model.view;

import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import model.dto.CreateCustomerDto;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ViewDashboard {
    private static final CustomerController customerController = new CustomerController();
    private static final OrderController orderController = new OrderController();
    private static final ProductController productController = new ProductController();

    public static void view() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            printMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    displayAllCustomers();
                    break;
                case 2:
                    addNewCustomer(scanner);
                    break;
                case 3:
                    deleteCustomerById(scanner);
                    break;
                case 4:
                    updateCustomer(scanner);
                    break;
                case 5:
                    displayAllOrders();
                    break;
                case 6:
                    addNewOrder();
                    break;
                case 7:
                    displayAllProducts();
                    break;
                case 8:
                    addNewProduct();
                    break;
                case 9:
                    updateProduct(scanner);
                    break;
                case 10:
                    deleteProduct(scanner);
                    break;
                case 11:
                    System.out.println("\nExiting...");
                    break;
                default:
                    System.out.println("\nInvalid option. Please choose again.");
                    break;
            }
        } while (option != 11);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n==== MENU ====");
        System.out.println("1. Display all Customers");
        System.out.println("2. Add new Customer");
        System.out.println("3. Delete Customer by ID");
        System.out.println("4. Update Customer by ID");
        System.out.println("5. Display all Orders");
        System.out.println("6. Add new Order");
        System.out.println("7. Display all Products");
        System.out.println("8. Add new Product");
        System.out.println("9. Update Product by ID");
        System.out.println("10. Delete Product by ID");
        System.out.println("11. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayAllCustomers() {
        System.out.println("All Customers:");
        List<Customer> customers = customerController.getAllCustomers();
        customers.forEach(customer -> System.out.println(customer.toString()));
    }

    private static void addNewCustomer(Scanner scanner) {
        System.out.println("\nEnter new customer details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        CreateCustomerDto newCustomerDto = CreateCustomerDto.builder()
                .name(name)
                .email(email)
                .password(password)
                .is_deleted(false)
                .created_at(Date.valueOf(LocalDate.now()))
                .build();

        customerController.addNewCustomer(newCustomerDto);
        System.out.println("\nAdded new customer: " + newCustomerDto.name());
    }

    private static void deleteCustomerById(Scanner scanner) {
        System.out.print("\nEnter customer ID to delete: ");
        int customerIdToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        customerController.deleteCustomer(customerIdToDelete);
        System.out.println("\nDeleted customer with ID: " + customerIdToDelete);
    }

    private static void updateCustomer(Scanner scanner) {
        System.out.print("\nEnter customer ID to update: ");
        int customerIdToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        customerController.updateCustomer(customerIdToUpdate);
        System.out.println("\nUpdated customer with ID: " + customerIdToUpdate);
    }

    private static void displayAllOrders() {
        System.out.println("\nAll Orders:");
        List<Order> orders = orderController.getAllOrders();
        orders.forEach(order -> System.out.println(order.toString()));
    }

    private static void addNewOrder() {
        orderController.addNewOrder(Order.builder()
                .id(12)
                .orderName("Ice-Capuccino")
                .orderDescription("1 sot sugar 60%")
                .orderedAt(Date.valueOf(LocalDate.now()))
                .customer(Customer.builder().id(1).build())
                .productsList(List.of(Product.builder().id(2).build()))
                .build());
    }

    private static void displayAllProducts() {
        System.out.println("\nAll Products:");
        List<Product> products = productController.getAllProducts();
        products.forEach(product -> System.out.println(product.toString()));
    }

    private static void addNewProduct() {
        System.out.println("\nEnter new product details:");
        Product newProduct = Product.builder()
                .productName("Ice Juice")
                .productCode("Coffee-1002")
                .isDeleted(false)
                .importedAt(Date.valueOf(LocalDate.now()))
                .expiredAt(Date.valueOf(LocalDate.now().plusMonths(6)))
                .productDescription("Sample product description")
                .build();
        productController.addNewProduct(newProduct);
        System.out.println("\nAdded new product: " + newProduct.getProductName());
    }

    private static void updateProduct(Scanner scanner) {
        System.out.print("\nEnter product ID to update: ");
        int productIdToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        productController.updateProduct(productIdToUpdate);
        System.out.println("\nUpdated product with ID: " + productIdToUpdate);
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.print("\nEnter product ID to delete: ");
        int productIdToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        productController.deleteProduct(productIdToDelete);
        System.out.println("\nDeleted product with ID: " + productIdToDelete);
    }
}
