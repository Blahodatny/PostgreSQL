package database.random;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Randomize extends RandomData {

    public Randomize(Consumer<Supplier<PreparedStatement>> consumer) {
        super(consumer);
    }

    public void randomize() {
        try {
            insertCustomers("Customers.txt");
            insertOrders("Orders.txt");
            insertProducts("Products.txt");
        } catch (FileNotFoundException e) {
            printError(e);
        }
    }
}