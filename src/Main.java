import model.dao.CustomerDaoImpl;
import model.entity.Customer;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

//        new CustomerDaoImpl()
//                .addNewCustomer(new Customer(
//                        1,
//                        "Mouk Makara",
//                        "makaramouk@gmail.com",
//                        "!@#$%^&**&^%$#@!",
//                        false,
//                        Date.valueOf(LocalDate.now())
//                ));
        /**
         * use @Builder
         * */
        Customer newCustomer = new Customer().builder()
                .id(2)
                .name("Pich Lyhour")
                .email("pichlyhour@gmail.com")
                .password("*&^%$#@!!@#$%^&*")
                .isDeleted(false)
                .createdDate(Date.valueOf(LocalDate.now()))
                .build();
        new CustomerDaoImpl().addNewCustomer(newCustomer);

        /**
         * queryAllCustomers
         * */
        new CustomerDaoImpl().queryAllCustomers()
        .forEach(System.out::println);
    }
}