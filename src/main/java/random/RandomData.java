package random;

import dao.RetailService;
import dao.tables.Customers;
import dao.tables.OrderItems;
import dao.tables.Products;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.Objects.requireNonNull;

class RandomData extends RetailService {
    private Consumer<Supplier<PreparedStatement>> consumer;
    final private Products product = new Products();
    final private byte MAXQUAN = 100;
    final private byte MAXPRODID = 5;

    RandomData(Consumer<Supplier<PreparedStatement>> consumer) {
        this.consumer = consumer;
    }

    private Scanner getFile(String file) throws FileNotFoundException {
        return new Scanner(
                new File(requireNonNull(
                        getClass()
                                .getClassLoader()
                                .getResource("data/" + file)
                ).getFile())
        );
    }

    void insertCustomers(String file) throws FileNotFoundException {
        var scanner = getFile(file);
        var table = new Customers();
        while (scanner.hasNextLine()) {
            table.setPhone(scanner.next());
            consumer.accept(() ->
                    table.insert(
                            scanner.next(),
                            scanner.next(),
                            scanner.next() + " " + scanner.next(),
                            scanner.next()
                    )
            );
        }
    }

    void insertOrders(String file) throws FileNotFoundException {
        var scanner = getFile(file);
        var table = new OrderItems();
        var random = new Random();
        while (scanner.hasNextLine()) {
            table.insert(
                    scanner.next(),
                    scanner.next() + " " + scanner.next(),
                    scanner.next()
            );
            IntStream
                    .range(0, random.nextInt(MAXPRODID) + 1)
                    .forEach(i -> consumer.accept(
                            () -> table.insert(
                                    product.getRow(random.nextInt(MAXPRODID) + 1),
                                    random.nextInt(MAXQUAN) + 1
                            )
                    ));
        }
    }

    void insertProducts(String file) throws FileNotFoundException {
        var scanner = getFile(file);
        while (scanner.hasNextLine())
            consumer.accept(() ->
                    product.insert(scanner.next(), scanner.next(), scanner.nextBoolean())
            );
    }
}