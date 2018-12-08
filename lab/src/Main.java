import database.operators.Initialization;
import database.operators.enums.ECustomerAttribute;
import database.operators.tables.CustomersTable;
import database.random.Randomize;
import input.Customer;
import program.CustomerProgram;

import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        var service = new Initialization("Retail_Service", "postgres", "Dima4532");
        service.createTables();
//        new Randomize(service::operate).randomize();
        System.out.println(new CustomerProgram().run());
        service.closeConnection();
    }
}