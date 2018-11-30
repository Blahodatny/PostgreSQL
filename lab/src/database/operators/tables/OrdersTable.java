package database.operators.tables;

import database.RetailService;
import database.operators.enums.EOrderAttribute;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdersTable extends RetailService {
    int orderNumber;

    public PreparedStatement insert(String phone, String toStreet, String toCity) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO ORDERS (Phone, ToStreet, ToCity, ShipDate) VALUES (?, ?, ?, CURRENT_TIMESTAMP(0)) RETURNING Order_Number"
            );
            statement.setString(1, phone);
            statement.setString(2, toStreet);
            statement.setString(3, toCity);
            var res = statement.executeQuery();
            res.next();
            orderNumber = res.getInt(1);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }

    public PreparedStatement delete() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM ORDERS WHERE Order_number = ?;\n");
            statement.setInt(1, orderNumber);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }

    public PreparedStatement update(EOrderAttribute attribute, String value) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE ORDERS SET " + attribute.name() + " = ? WHERE Order_Number = ?");
            statement.setString(1, value);
            statement.setInt(2, orderNumber);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }
}