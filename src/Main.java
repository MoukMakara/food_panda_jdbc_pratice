import model.dao.CustomerDaoImpl;
import model.dao.OrderDaoImpl;
import model.dto.CreateCustomerDto;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import model.service.CustomerService;
import model.service.OrderService;
import model.service.ProductService;
import model.view.ViewDashboard;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        ProductService productService = new ProductService();


        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        do {
            printMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    displayAllCustomers(customerService);
                    break;
                case 2:
                    addNewCustomer(scanner, customerService);
                    break;
                case 3:
                    deleteCustomerById(scanner, customerService);
                    break;
                case 4:
                    updateCustomer(scanner, customerService);
                    break;
                case 5:
                    displayAllOrders(orderService);
                    break;
                case 6:
                    addNewOrder(scanner, orderService);
                    break;
                case 7:
                    displayAllProducts(productService);
                    break;
                case 8:
                    addNewProduct(productService);
                    break;
                case 9:
                    updateProduct(scanner, productService);
                    break;
                case 10:
                    deleteProduct(scanner, productService);
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

    private static void displayAllCustomers(CustomerService customerService) {
        ViewDashboard viewDashboard = new ViewDashboard();
        viewDashboard.displayMessage("All Customers:");
        List<Customer> customers = customerService.getAllCustomers();
        customers.forEach(customer -> viewDashboard.displayMessage(customer.toString()));
    }

    private static void addNewCustomer(Scanner scanner, CustomerService customerService) {
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

        //        new CustomerDaoImpl()
//                .addNewCustomer(Customer.builder()
//                        .name("Chan Bopha")
//                        .email("chanbopha@gmail.com")
//                        .password("*&^%$#@!!@#$%^&")
//                        .isDeleted(false)
//                        .createdDate(Date.valueOf(LocalDate.now()))
//                        .build());

        customerService.addNewCustomer(newCustomerDto);
        System.out.println("\nAdded new customer: " + newCustomerDto.name());
    }

    private static void deleteCustomerById(Scanner scanner, CustomerService customerService) {
        System.out.print("\nEnter customer ID to delete: ");
        int customerIdToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        customerService.deleteCustomer(customerIdToDelete);
        System.out.println("\nDeleted customer with ID: " + customerIdToDelete);
    }

    private static void updateCustomer(Scanner scanner, CustomerService customerService) {
        System.out.print("\nEnter customer ID to update: ");
        int customerIdToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        customerService.updateCustomer(customerIdToUpdate);
        System.out.println("\nUpdated customer with ID: " + customerIdToUpdate);
    }

    private static void displayAllOrders(OrderService orderService) {
        ViewDashboard viewDashboard = new ViewDashboard();
        viewDashboard.displayMessage("\nAll Orders:");
        List<Order> orders = orderService.getAllOrders();
        orders.forEach(order -> viewDashboard.displayMessage(order.toString()));
    }

    private static void addNewOrder(Scanner scanner, OrderService orderService) {

                new OrderDaoImpl().addNewOrder(Order.builder()
                .id(6)
                .orderName("Espresso")
                .orderDescription("strong coffee 2 sot")
                .orderedAt(Date.valueOf(LocalDate.now()))
                .customer(Customer.builder()
                        .id(6)
                        .build())
                .productsList(new ArrayList<>(List.of(Product.builder()
                        .id(4)
                        .build())))
                .build());

//        System.out.println("\nEnter new order details:");
//        System.out.print("Order Name: ");
//        String orderName = scanner.nextLine();
//        System.out.print("Order Description: ");
//        String orderDescription = scanner.nextLine();
//
//        // Assume customerId is known or retrieved from previous steps
//        Order newOrder = Order.builder()
//                .orderName(orderName)
//                .orderDescription(orderDescription)
//                .orderedAt(Date.valueOf(LocalDate.now()))
//                .customer(Customer.builder().id(1).build()) // Replace with actual customer object
//                .productsList(new ArrayList<>()) // Initialize empty product list
//                .build();
//
//        orderService.addNewOrder(newOrder);
//        System.out.println("\nAdded new order: " + newOrder.getOrderName());
    }

    private static void displayAllProducts(ProductService productService) {
        ViewDashboard viewDashboard = new ViewDashboard();
        viewDashboard.displayMessage("\nAll Products:");
        List<Product> products = productService.getAllProducts();
        products.forEach(product -> viewDashboard.displayMessage(product.toString()));
    }

    private static void addNewProduct(ProductService productService) {

        System.out.println("\nEnter new product details:");
        ViewDashboard viewDashboard = new ViewDashboard();
                Product newProduct = Product.builder()
                .productName("Ice Lemon")
                .productCode("Coffee-1002")
                .isDeleted(false)
                .importedAt(Date.valueOf(LocalDate.now()))
                .expiredAt(Date.valueOf(LocalDate.now().plusMonths(6)))
                .productDescription("Sample product description")
                .build();
        productService.addNewProduct(newProduct);
        viewDashboard.displayMessage("\nAdded new product: " + newProduct.getProductName());


//        System.out.println("\nEnter new product details:");
//        System.out.print("Product Name: ");
//        String productName = scanner.nextLine();
//        System.out.print("Product Code: ");
//        String productCode = scanner.nextLine();
//        System.out.print("Product Description: ");
//        String productDescription = scanner.nextLine();
//
//        Product newProduct = Product.builder()
//                .productName(productName)
//                .productCode(productCode)
//                .isDeleted(false)
//                .importedAt(Date.valueOf(LocalDate.now()))
//                .expiredAt(Date.valueOf(LocalDate.now().plusMonths(6)))
//                .productDescription(productDescription)
//                .build();
//
//        productService.addNewProduct(newProduct);
//        System.out.println("\nAdded new product: " + newProduct.getProductName());
    }

    private static void updateProduct(Scanner scanner, ProductService productService) {
        System.out.print("\nEnter product ID to update: ");
        int productIdToUpdate = scanner.nextInt();
        scanner.nextLine();
        productService.updateProduct(productIdToUpdate);
        System.out.println("\nUpdated product with ID: " + productIdToUpdate);
    }

    private static void deleteProduct(Scanner scanner, ProductService productService) {
        System.out.print("\nEnter product ID to delete: ");
        int productIdToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        productService.deleteProduct(productIdToDelete);
        System.out.println("\nDeleted product with ID: " + productIdToDelete);
    }
}
