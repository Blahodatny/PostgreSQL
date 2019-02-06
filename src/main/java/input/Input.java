package input;

import database.operators.enums.CustomerAttribute;
import database.operators.enums.OrderAttribute;
import database.operators.enums.OrderItemAttribute;
import interfaces.DataScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Input implements DataScanner {
    public String[] create(byte num) {
        System.out.println("Please, enter your credentials");
        scanner.nextLine();
        var array = new String[num == 1 ? 5 : num == 2 ? 3 : 2];
        byte i = 0;
        for (var attr :
                num == 1 ? CustomerAttribute.values() :
                        num == 2 ? OrderAttribute.values() :
                                OrderItemAttribute.values()
        ) {
            System.out.print(attr + ": ");
            array[i] = scanner.nextLine();
            i++;
        }
        return array;
    }

    public String[] update() {
        System.out.println("Please, enter identification of table you wanna update");
        var id = scanner.next();
        System.out.println("Enter an attribute [whitespace] its update value");
        return new String[]{id, scanner.next(), scanner.next()};
    }

    public String[] set() {
        System.out.println("Please, enter identification of table you wanna delete\\set");
        return new String[]{scanner.next()};
    }

    public List<String> search() {
        System.out.println("Please, enter a string you wanna find");
        List<String> list = new ArrayList<>();
        scanner.nextLine();
        var st = new StringTokenizer(scanner.nextLine(), " ");
        while (st.hasMoreTokens())
            list.add(st.nextToken());
        return list;
    }
}