package database.operators.tables;

import database.RetailService;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdersTable extends RetailService {
    public PreparedStatement insert(String phone, String toStreet, String toCity, Date shipDate) throws SQLException {
        var statement = connection.prepareStatement(
                "INSERT INTO ORDERS (Phone, ToStreet, ToCity, ShipDate) VALUES (?, ?, ?, ?)"
        );
        statement.setString(1, phone);
        statement.setString(2, toStreet);
        statement.setString(3, toCity);
        statement.setObject(4, shipDate);
        return statement;
    }
}