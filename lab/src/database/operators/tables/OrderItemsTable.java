package database.operators.tables;

import database.RetailService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemsTable extends RetailService {
    public PreparedStatement insert(int quantity, int orderNumber, int productId) throws SQLException {
        var statement = connection.prepareStatement(
                "INSERT INTO ORDER_ITEMS (Phone, Order_Number, Product_ID) VALUES (?, ?, ?)"
        );
        statement.setInt(1, quantity);
        statement.setInt(2, orderNumber);
        statement.setInt(3, productId);
        return statement;
    }
}