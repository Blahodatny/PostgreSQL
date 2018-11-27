package database.operators.tables;

import database.RetailService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemsTable extends RetailService {
    public PreparedStatement insert(int quantity, int orderNumber, int productId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO ORDER_ITEMS (Phone, Order_Number, Product_ID) VALUES (?, ?, ?)"
            );
            statement.setString(1, String.valueOf(quantity));
            statement.setString(2, String.valueOf(orderNumber));
            statement.setString(3, String.valueOf(productId));
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }
}