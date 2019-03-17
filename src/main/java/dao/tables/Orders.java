package dao.tables;

import dao.RetailService;
import dao.attributes.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Orders extends RetailService {
    int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void insert(String phone, String toStreet, String toCity) {
        try {
            connection.setAutoCommit(false);
            var statement = connection.prepareStatement(
                    "INSERT INTO orders (Phone, To_Street, To_City, Ship_Date)\n" +
                            "VALUES (?, ?, ?, CURRENT_TIMESTAMP(0))",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, phone);
            statement.setString(2, toStreet);
            statement.setString(3, toCity);
            statement.executeUpdate();
            var res = statement.getGeneratedKeys();
            res.next();
            orderId = res.getInt(1);
            close(res, statement);
        } catch (SQLException e) {
            printError(e);
        }
    }

    public PreparedStatement delete() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "DELETE FROM ORDERS WHERE Id = ?"
            );
            statement.setInt(1, orderId);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }

    public PreparedStatement update(Order attribute, String value) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE ORDERS\n" +
                            "SET " + attribute.name() + " = ?\n" +
                            "WHERE Id = ?"
            );
            statement.setString(1, value);
            statement.setInt(2, orderId);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }
}