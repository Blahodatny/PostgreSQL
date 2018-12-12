package database.search;

import database.RetailService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Search extends RetailService {
    // CUSTOMERS.Street && CUSTOMERS.City + ORDERS.ToStreet && ORDERS.ToCity
    public void search(String string) {
    }

    public PreparedStatement fts(String str) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
""
            );
            statement.setString(1, str);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }
}