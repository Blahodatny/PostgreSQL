package database.search;

import database.RetailService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Search extends RetailService {
    final private String dir = path + "/src/database/sql/fts.sql";

    // CUSTOMERS.Street && CUSTOMERS.City + ORDERS.ToStreet && ORDERS.ToCity
    public void search(String string) {
    }

    public PreparedStatement fts(String str) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    new String(Files.readAllBytes(Paths.get(dir)))
            );
            statement.setString(1, str);
        } catch (SQLException | IOException e) {
            printError(e);
        }
        return statement;
    }
}