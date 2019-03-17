package dao.tables;

import dao.RetailService;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.nio.file.Files.readAllBytes;

public class Products extends RetailService {
    final private static String SQL = "./src/main/java/dao/sql/products/getRow.sql";

    public PreparedStatement insert(String id, String type, boolean secondHand) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO PRODUCTS (Id, Type, Second_Hand)\n" +
                            "VALUES (?, ?, ?)"
            );
            statement.setString(1, id);
            statement.setString(2, type);
            statement.setBoolean(3, secondHand);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }

    public String getRow(int row) {
        String productId = null;
        try {
            connection.setAutoCommit(false);
            var statement = connection.prepareStatement(
                    new String(
                            readAllBytes(Paths.get(SQL))
                    )
            );
            statement.setInt(1, row);
            var res = statement.executeQuery();
            res.next();
            productId = res.getString(1);
            close(res, statement);
        } catch (SQLException | IOException e) {
            printError(e);
        }
        return productId;
    }
}