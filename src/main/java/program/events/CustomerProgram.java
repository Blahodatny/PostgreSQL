package program.events;

import dao.attributes.Customer;
import dao.tables.Customers;
import program.data.DataInput;
import program.data.DataScanner;

public class CustomerProgram implements DataScanner, DataInput {
    private Customers table = new Customers();

    public void run() {
        System.out.println(
                "Please, enter an action you want " +
                        "to perform with customer\nFor help enter \'4\'"
        );
        while (true) {
            var num = scanner.nextByte();
            switch (num) {
                case 1:
                case 2:
                case 3:
                    var array = num == 1 ? input.create(num) :
                            num == 2 ? input.update() : input.set();
                    table.setPhone(array[0]);
                    table.operate(
                            () -> num == 1 ?
                                    table.insert(array[1], array[2], array[3], array[4]) :
                                    num == 2 ? table.update(
                                            Customer.valueOf(array[1]), array[2]
                                    ) : table.delete()
                    );
                    break;
                case 4:
                    System.out.println(
                            "1 - create a new customer\n" +
                                    "2 - update customer\n" +
                                    "3 - delete customer\n" +
                                    "4 - help\n" +
                                    "any other - exit to main loop"
                    );
                    break;
                default:
                    return;
            }
        }
    }
}