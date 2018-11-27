package database.operators.tables;

import database.RetailService;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdersTable extends RetailService {
    public PreparedStatement insert(String phone, String toStreet, String toCity, Date shipDate) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO ORDERS (Phone, ToStreet, ToCity, ShipDate) VALUES (?, ?, ?, ?)"
            );
            statement.setString(1, phone);
            statement.setString(2, toStreet);
            statement.setString(3, toCity);
            statement.setString(4, shipDate.toString());
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }
}