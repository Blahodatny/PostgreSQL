package database.operators.tables;

import database.RetailService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomersTable extends RetailService {
    public PreparedStatement insert(String phone, String firstName, String lastName, String street, String city) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO CUSTOMERS (Phone, FirstName, LastName, Street, City) VALUES (?, ?, ?, ?, ?)"
            );
            statement.setString(1, phone);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, street);
            statement.setString(5, city);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }
}