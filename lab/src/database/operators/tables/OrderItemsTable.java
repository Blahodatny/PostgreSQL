package database.operators.tables;

import database.RetailService;
import database.operators.enums.EOrderItemAttribute;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemsTable extends RetailService {
    private static int orderNumber;

    public OrderItemsTable(int orderNumber) {
        OrderItemsTable.orderNumber = orderNumber;
    }

    public PreparedStatement insert(int quantity, int productId) throws SQLException {
        var statement = connection.prepareStatement(
                "INSERT INTO ORDER_ITEMS (Phone, Order_Number, Product_ID) VALUES (?, ?, ?)"
        );
        statement.setInt(1, quantity);
        statement.setInt(2, orderNumber);
        statement.setInt(3, productId);
        return statement;
    }

    public PreparedStatement delete(int productID) throws SQLException {
        var statement = connection.prepareStatement("DELETE FROM ORDER_ITEMS WHERE Order_number = ? AND Product_ID = ?;\n");
        statement.setInt(1, orderNumber);
        statement.setInt(2, productID);
        return statement;
    }

    public PreparedStatement update(EOrderItemAttribute attribute, int value, int productID) throws SQLException {
        var statement = connection.prepareStatement("UPDATE ORDER_ITEMS SET " + attribute.name() + " = ? WHERE Order_number = ? AND Product_ID = ?");
        statement.setInt(1, value);
        statement.setInt(2, orderNumber);
        statement.setInt(3, productID);
        return statement;
    }
}