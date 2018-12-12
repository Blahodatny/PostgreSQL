package database.search;

import database.RetailService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Search extends RetailService {
    final private String dir = path + "/src/database/sql/fts.sql";

    // CUSTOMERS.Street && CUSTOMERS.City + ORDERS.ToStreet && ORDERS.ToCity
    public void search(String string) {
    }

    public void fts(String str) {
        try {
            connection.setAutoCommit(false);
            var statement = connection.prepareStatement(
                    new String(Files.readAllBytes(Paths.get(dir)))
            );
            statement.setString(1, str);
            statement.executeQuery();
            var res = statement.executeQuery();
            System.out.println(res);
            res.close();
            statement.close();
            connection.commit();
        } catch (SQLException | IOException e) {
            printError(e);
        }
    }
}