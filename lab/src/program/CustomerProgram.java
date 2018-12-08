package program;

import database.operators.enums.ECustomerAttribute;
import database.operators.tables.CustomersTable;
import input.Customer;

import java.util.Scanner;

public class CustomerProgram extends CustomersTable {
    private final Scanner scanner = new Scanner(System.in);
    private final Customer customer = new Customer();

    public void run() {
        System.out.println(
                "Please, enter an action you want to perform with customer\nFor help enter \'4\'"
        );
        while (true) {
            var num = scanner.nextByte();
            switch (num) {
                case 1:
                case 2:
                case 3:
                    var array = num == 1 ? customer.createCustomer() : customer.updateCustomer();
                    setPhone(num == 3 ? customer.deleteCustomer() : array[0]);
                    operate(() ->
                            num == 1 ? insert(array[1], array[2], array[3], array[4])
                                    : num == 2 ? update(ECustomerAttribute.valueOf(array[1]), array[2])
                                    : delete());
                    break;

                case 4:
                    System.out.println(
                            "1 - create a new customer\n" +
                                    "2 - update customer\n" +
                                    "3 - delete customer\n" +
                                    "4 - help\n" +
                                    "5 - exit to main loop"
                    );
                    break;

                case 5:
                    return;
            }
        }
    }
}