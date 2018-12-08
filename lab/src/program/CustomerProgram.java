package program;

import database.operators.enums.ECustomerAttribute;
import database.operators.tables.CustomersTable;

public class CustomerProgram extends Program {
    private final CustomersTable table = new CustomersTable();

    public void run() {
        System.out.println(
                "Please, enter an action you want to perform with input\nFor help enter \'4\'"
        );
        while (true) {
            var num = scanner.nextByte();
            switch (num) {
                case 1:
                case 2:
                case 3:
                    var array = num == 1 ? input.create((byte) 1) : input.update();
                    table.setPhone(num == 3 ? input.set() : array[0]);
                    table.operate(() ->
                            num == 1 ? table.insert(array[1], array[2], array[3], array[4])
                                    : num == 2 ? table.update(ECustomerAttribute.valueOf(array[1]), array[2])
                                    : table.delete());
                    break;

                case 4:
                    System.out.println(
                            "1 - create a new customer\n" +
                                    "2 - update customer\n" +
                                    "3 - set customer\n" +
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