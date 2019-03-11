package program.events;

import database.operators.enums.OrderAttribute;
import database.operators.tables.OrderItemsTable;
import interfaces.DataInput;
import interfaces.DataScanner;

public class OrderProgram implements DataScanner, DataInput {
    private final OrderItemsTable table = new OrderItemsTable();

    public void run() {
        System.out.println(
                "Please, enter an action you want" +
                        "to perform with order\nFor help enter \'4\'"
        );
        while (true) {
            var num = scanner.nextByte();
            switch (num) {
                case 1:
                case 2:
                case 3:
                case 5:
                    var array = num == 1 ? input.create((byte) 2) :
                            num == 2 ? input.update() : input.set();
                    if (num == 1) {
                        table.insert(array[0], array[1], array[2]);
                        System.out.println("ATTENTION!!! Your order number is: " +
                                table.getOrderNumber());
                    } else {
                        table.setOrderNumber(Integer.parseInt(array[0]));
                        if (num == 5) break;
                        table.operate(
                                () -> num == 2 ? table.update(
                                        OrderAttribute.valueOf(array[1]), array[2]
                                ) : table.delete()
                        );
                    }
                    break;
                case 4:
                    System.out.println(
                            "1 - create a new order\n" +
                                    "2 - update order\n" +
                                    "3 - delete order\n" +
                                    "4 - help\n" +
                                    "5 - set number of order\n" +
                                    "\tIf you just inserted or updated" +
                                    " one there is no need to run\n" +
                                    "6 - continue to set products in order\n" +
                                    "any other - exit to main loop"
                    );
                    break;
                case 6:
                    new OrderItemProgram().run(table);
                    break;
                default:
                    return;
            }
        }
    }
}