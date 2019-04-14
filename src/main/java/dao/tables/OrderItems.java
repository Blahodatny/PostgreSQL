package dao.tables;

import dao.attributes.OrderItem;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItems extends Orders {
    public PreparedStatement insert(String productId, int quantity) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO ORDER_ITEMS " +
                            "(Order_Id, Product_Id, Quantity)\n" +
                            "VALUES (?, ?, ?)");
            statement.setInt(1, orderId);
            statement.setString(2, productId);
            statement.setInt(3, quantity);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }

    public PreparedStatement delete(String productId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "DELETE\n" +
                            "FROM ORDER_ITEMS\n" +
                            "WHERE Order_Id = ?\n" +
                            "AND Product_Id = ?");
            statement.setInt(1, orderId);
            statement.setString(2, productId);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }

    public PreparedStatement update(OrderItem attribute, int value,
            String productId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE ORDER_ITEMS\n" +
                            "SET " + attribute.name() + " = ?\n" +
                            "WHERE Order_Id = ?\n" +
                            "AND Product_Id = ?");
            statement.setInt(1, value);
            statement.setInt(2, orderId);
            statement.setString(3, productId);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }
}