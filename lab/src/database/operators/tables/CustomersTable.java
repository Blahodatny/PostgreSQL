package database.operators.tables;

import database.RetailService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomersTable extends RetailService {
    public PreparedStatement insert(String phone, String firstName, String lastName, String street, String city) throws SQLException {
        var statement = connection.prepareStatement(
                "INSERT INTO CUSTOMERS (Phone, FirstName, LastName, Street, City) VALUES (?, ?, ?, ?, ?)"
        );
        statement.setString(1, phone);
        statement.setString(2, firstName);
        statement.setString(3, lastName);
        statement.setString(4, street);
        statement.setString(5, city);
        return statement;
    }

    public PreparedStatement delete(String phone) throws SQLException {
        var statement = connection.prepareStatement("DELETE FROM CUSTOMERS WHERE Phone = ?;\n");
        statement.setString(1, phone);
        return statement;
    }
}