package database.operators.tables;

import database.RetailService;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdersTable extends RetailService {
    public PreparedStatement insert(String phone, String toStreet, String toCity) throws SQLException {
        var statement = connection.prepareStatement(
                "INSERT INTO ORDERS (Phone, ToStreet, ToCity, ShipDate) VALUES (?, ?, ?, CURRENT_TIMESTAMP(0))"
        );
        statement.setString(1, phone);
        statement.setString(2, toStreet);
        statement.setString(3, toCity);
        return statement;
    }

    public PreparedStatement delete(int order_number) throws SQLException {
        var statement = connection.prepareStatement("DELETE FROM ORDERS WHERE Order_number = ?;\n");
        statement.setInt(1, order_number);
        return statement;
    }
}