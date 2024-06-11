import model.dao.CustomerDaoImpl;
import model.entity.Customer;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        new CustomerDaoImpl()
                .addNewCustomer(new Customer(
                        1,
                        "Mouk Makara",
                        "makaramouk@gmail.com",
                        "!@#$%^&**&^%$#@!",
                        false,
                        Date.valueOf(LocalDate.now())
                ));
        /**
         * use @Builder
         * */
        Customer newCustomer1 = new Customer().builder()
                .id(2)
                .name("Pich Lyhour")
                .email("pichlyhour@gmail.com")
                .password("*&^%$#@!!@#$%^&*")
                .isDeleted(false)
                .createdDate(Date.valueOf(LocalDate.now()))
                .build();
        Customer newCustomer2 = new Customer().builder()
                .id(3)
                .name("Chan Bopha")
                .email("chanbopha@gmail.com")
                .password("*&^%$#@!!@#$%^&")
                .isDeleted(false)
                .createdDate(Date.valueOf(LocalDate.now()))
                .build();

        new CustomerDaoImpl().addNewCustomer(newCustomer1);
        new CustomerDaoImpl().addNewCustomer(newCustomer2);

        /**
         * queryAllCustomers
         * */
        new CustomerDaoImpl().queryAllCustomers()
        .forEach(System.out::println);
    }
}