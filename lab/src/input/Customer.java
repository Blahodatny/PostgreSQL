package input;

import database.operators.enums.ECustomerAttribute;

import java.util.Scanner;

public class Customer {
    private final Scanner scanner = new Scanner(System.in);

    public String[] createCustomer() {
        System.out.println("Please, enter your credentials");
        var array = new String[5];
        byte i = 0;
        for (var attr : ECustomerAttribute.values()) {
            System.out.print(attr + ": ");
            array[i] = scanner.nextLine();
            i++;
        }
        return array;
    }

    public String[] updateCustomer() {
        System.out.println("Please, enter phone of customer you wanna update");
        var phone = scanner.next();
        System.out.println("Enter an attribute [whitespace] its update value");
        return new String[]{phone, scanner.next(), scanner.next()};
    }

    public String deleteCustomer() {
        System.out.println("Please, enter phone of customer you wanna delete");
        return scanner.next();
    }
}