package database.operators.tables;

import database.operators.enums.EOrderItemAttribute;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemsTable extends OrdersTable {
    public PreparedStatement insert(String productID, int quantity) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO order_items (Order_Number, Product_ID, Quantity)\n" +
                            "VALUES (?, ?, ?)"
            );
            statement.setInt(1, orderNumber);
            statement.setString(2, productID);
            statement.setInt(3, quantity);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }

    public PreparedStatement delete(String productID) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "DELETE\n" +
                            "FROM order_items\n" +
                            "WHERE Order_number = ?\n" +
                            "AND Product_ID = ?"
            );
            statement.setInt(1, orderNumber);
            statement.setString(2, productID);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }

    public PreparedStatement update(EOrderItemAttribute attribute, int value, String productID) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE order_items\n" +
                            "SET " + attribute.name() + " = ?\n" +
                            "WHERE Order_number = ?\n" +
                            "AND Product_ID = ?"
            );
            statement.setInt(1, value);
            statement.setInt(2, orderNumber);
            statement.setString(3, productID);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }
}