package database.operators.tables;

import database.RetailService;
import database.operators.enums.ECustomerAttribute;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomersTable extends RetailService {
    private String phone;

    public CustomersTable(String phone) {
        this.phone = phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PreparedStatement insert(String firstName, String lastName, String street, String city) {
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

    public PreparedStatement delete() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM CUSTOMERS WHERE Phone = ?;\n");
            statement.setString(1, phone);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }

    public PreparedStatement update(ECustomerAttribute attribute, String value) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE CUSTOMERS SET " + attribute.name() + " = ? WHERE Phone = ?"
            );
            statement.setString(1, value);
            statement.setString(2, phone);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }
}