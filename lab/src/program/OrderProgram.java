package program;

import database.operators.enums.EOrderAttribute;
import database.operators.tables.OrdersTable;

public class OrderProgram extends Program {
    private final OrdersTable table = new OrdersTable();

    public int run() {
        System.out.println(
                "Please, enter an action you want to perform with order\nFor help enter \'4\'"
        );
        while (true) {
            var num = scanner.nextByte();
            switch (num) {
                case 1:
                case 2:
                case 3:
                    var array = num == 1 ? input.create((byte) 2) : input.update();
                    if (num == 1) {
                        table.insert(array[1], array[2], array[3]);
                        System.out.println("ATTENTION!!! Your order number is: " + table.getOrderNumber());
                    } else {
                        table.setOrderNumber(Integer.parseInt(num == 3 ? input.delete() : array[0]));
                        table.operate(() -> num == 2 ? table.update(EOrderAttribute.valueOf(array[1]), array[2])
                                : table.delete());
                    }
                    break;

                case 4:
                    System.out.println(
                            "1 - create a new order\n" +
                                    "2 - update order\n" +
                                    "3 - delete order\n" +
                                    "4 - help\n" +
                                    "any other - exit to main loop"
                    );
                    break;

                default:
                    return table.getOrderNumber();
            }
        }
    }
}