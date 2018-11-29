package database.operators.tables;

import database.RetailService;
import database.operators.enums.ECustomerAttribute;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomersTable extends RetailService {
    private static String phone;

    public CustomersTable(String phone) {
        CustomersTable.phone = phone;
    }

    public PreparedStatement insert(String firstName, String lastName, String street, String city) throws SQLException {
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

    public PreparedStatement delete() throws SQLException {
        var statement = connection.prepareStatement("DELETE FROM CUSTOMERS WHERE Phone = ?;\n");
        statement.setString(1, phone);
        return statement;
    }

    public PreparedStatement update(ECustomerAttribute attribute, String value) throws SQLException {
        var statement = connection.prepareStatement("UPDATE CUSTOMERS SET " + attribute.name() + " = ? WHERE Phone = ?");
        statement.setString(1, value);
        statement.setString(2, phone);
        return statement;
    }
}