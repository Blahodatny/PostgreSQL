package dao.tables;

import dao.RetailService;
import dao.attributes.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Customers extends RetailService {
    private String phone;

    public Customers() {
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PreparedStatement insert(
            String firstName, String lastName, String street, String city) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO CUSTOMERS " +
                            "(Phone, First_Name, Last_Name, Street, City)\n" +
                            "VALUES (?, ?, ?, ?, ?)"
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
            statement = connection.prepareStatement(
                    "DELETE FROM CUSTOMERS WHERE Phone = ?"
            );
            statement.setString(1, phone);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }

    public PreparedStatement update(Customer attribute, String value) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE CUSTOMERS\n" +
                            "SET " + attribute.name() + " = ?\n" +
                            "WHERE Phone = ?"
            );
            statement.setString(1, value);
            statement.setString(2, phone);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }
}