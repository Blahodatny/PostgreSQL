package database.random;

import database.operators.tables.CustomersTable;
import database.operators.tables.OrderItemsTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class RandomData {
    private Consumer<Supplier<PreparedStatement>> consumer;
    final private String dir = System.getProperty("user.dir") + "/data/";
    final private byte MAXQUAN = 100;
    final private byte MAXPRODID = 5;

    public RandomData(Consumer<Supplier<PreparedStatement>> consumer) {
        this.consumer = consumer;
    }

    private void insertCustomers(String file) throws FileNotFoundException {
        var scanner = new Scanner(new File(dir + file));
        var table = new CustomersTable(scanner.next());
        while (scanner.hasNextLine()) {
            consumer.accept(() ->
                    table.insert(scanner.next(), scanner.next(), scanner.next() + scanner.next(), scanner.next())
            );
            table.setPhone(scanner.next());
        }
    }

    private void insertOrders(String file) throws FileNotFoundException {
        var scanner = new Scanner(new File(dir + file));
        var table = new OrderItemsTable();
        var random = new Random();
        while (scanner.hasNextLine()) {
            consumer.accept(() -> table.insert(scanner.next(), scanner.next() + scanner.next(), scanner.next()));
            IntStream.range(0, 5).forEach(i -> consumer.accept(
                    () -> table.insert(random.nextInt(MAXQUAN) + 1, random.nextInt(MAXPRODID) + 1)
            ));
        }
    }

    private void insertProducts(String file) throws FileNotFoundException {
        var scanner = new Scanner(new File(dir + file));

    }
}