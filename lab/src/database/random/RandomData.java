package database.random;

import database.RetailService;
import database.operators.tables.CustomersTable;
import database.operators.tables.OrderItemsTable;
import database.operators.tables.OrdersTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RandomData {
    private Consumer<Supplier<PreparedStatement>> consumer;

    final private String dir = System.getProperty("user.dir") + "/data/";

    public RandomData(Consumer<Supplier<PreparedStatement>> consumer) {
        this.consumer = consumer;
    }

    private void insertCustomers(String file) throws FileNotFoundException {
        var sc = new Scanner(new File(dir + file));
        var table = new CustomersTable(sc.next());
        while (sc.hasNextLine()) {
            consumer.accept(() -> table.insert(sc.next(), sc.next(), sc.next() + sc.next(), sc.next()));
            CustomersTable.setPhone(sc.next());
        }
    }

    private void insertOrders(String file) throws FileNotFoundException {
        var sc = new Scanner(new File(dir + file));
        var table = new OrdersTable();
        while (sc.hasNextLine())
            consumer.accept(() -> table.insert(sc.next(), sc.next() + sc.next(), sc.next()));
    }

    private void insertOrderItems(String file) throws FileNotFoundException {
        var sc = new Scanner(new File(dir + file));
        var table = new OrderItemsTable()
    }
}