package database.operators.tables;

import database.RetailService;
import database.operators.enums.OrderAttribute;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdersTable extends RetailService {
    int orderNumber;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void insert(String phone, String toStreet, String toCity) {
        try {
            connection.setAutoCommit(false);
            var statement = connection.prepareStatement(
                    "INSERT INTO orders (Phone, ToStreet, ToCity, ShipDate)\n" +
                            "VALUES (?, ?, ?, CURRENT_TIMESTAMP(0))",
                    java.sql.Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, phone);
            statement.setString(2, toStreet);
            statement.setString(3, toCity);
            statement.executeUpdate();
            var res = statement.getGeneratedKeys();
            res.next();
            orderNumber = res.getInt(1);
            close(res, statement);
        } catch (SQLException e) {
            printError(e);
        }
    }

    public PreparedStatement delete() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM orders WHERE Order_number = ?");
            statement.setInt(1, orderNumber);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }

    public PreparedStatement update(OrderAttribute attribute, String value) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE orders\n" +
                            "SET " + attribute.name() + " = ?\n" +
                            "WHERE Order_Number = ?"
            );
            statement.setString(1, value);
            statement.setInt(2, orderNumber);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }
}