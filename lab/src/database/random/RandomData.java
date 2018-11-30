package database.random;

import database.RetailService;
import database.operators.tables.CustomersTable;
import database.operators.tables.OrderItemsTable;
import database.operators.tables.ProductsTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

class RandomData extends RetailService {
    private Consumer<Supplier<PreparedStatement>> consumer;
    final private String dir = System.getProperty("user.dir") + "/data/";
    final private byte MAXQUAN = 100;
    final private byte MAXPRODID = 5;

    RandomData(Consumer<Supplier<PreparedStatement>> consumer) {
        this.consumer = consumer;
    }

    void insertCustomers(String file) throws FileNotFoundException {
        var scanner = new Scanner(new File(dir + file));
        var table = new CustomersTable();
        while (scanner.hasNextLine()) {
            table.setPhone(scanner.next());
            consumer.accept(() ->
                    table.insert(scanner.next(), scanner.next(), scanner.next() + scanner.next(), scanner.next())
            );
        }
    }

    void insertOrders(String file) throws FileNotFoundException {
        var scanner = new Scanner(new File(dir + file));
        var table = new OrderItemsTable();
        var random = new Random();
        while (scanner.hasNextLine()) {
            table.insert(scanner.next(), scanner.next() + " " + scanner.next(), scanner.next());
            IntStream.range(0, random.nextInt(MAXPRODID) + 1).forEach(i -> consumer.accept(
                    () -> table.insert(random.nextInt(MAXPRODID) + 1, random.nextInt(MAXQUAN) + 1)
            ));
        }
    }

    void insertProducts(String file) throws FileNotFoundException {
        var scanner = new Scanner(new File(dir + file));
        var table = new ProductsTable();
        while (scanner.hasNextLine())
            consumer.accept(() -> table.insert(scanner.next(), scanner.nextBoolean()));
    }
}