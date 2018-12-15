package random;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Randomize extends RandomData {
    public Randomize(Consumer<Supplier<java.sql.PreparedStatement>> consumer) {
        super(consumer);
    }

    public void randomize() {
        try {
            insertCustomers("Customers.txt");
            insertProducts("Products.txt");
            insertOrders("Orders.txt");
        } catch (java.io.FileNotFoundException e) {
            printError(e);
        }
    }
}