package program;

import database.operators.enums.EOrderItemAttribute;
import database.operators.tables.OrderItemsTable;

class OrderItemProgram extends Program {
    void run(OrderItemsTable table) {
        System.out.println(
                "Please, enter an action you want to perform with items\nFor help enter \'4\'"
        );
        while (true) {
            var num = scanner.nextByte();
            switch (num) {
                case 1:
                case 2:
                case 3:
                case 5:
                    var array = num == 1 ? input.create((byte) 3) : input.update();
                    if (num == 1)
                        table.insert(array[1], Integer.parseInt(array[2]));
                    else {
                        if (num == 5) {
                            table.setOrderNumber(Integer.parseInt(array[0]));
                            break;
                        }
                        table.operate(() -> num == 2 ? table.update(
                                EOrderItemAttribute.valueOf(array[1]),
                                Integer.parseInt(array[2]), array[0]
                        )
                                : table.delete(array[0]));
                    }
                    break;

                case 4:
                    System.out.println(
                            "1 - add new item\n" +
                                    "2 - update item\n" +
                                    "3 - delete item\n" +
                                    "4 - help\n" +
                                    "5 - set number of order\n" +
                                    "\tIf you just inserted or updated one there is no need to run\n" +
                                    "any other - exit to order loop"
                    );
                    break;

                default:
                    return;
            }
        }
    }
}