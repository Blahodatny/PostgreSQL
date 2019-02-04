package database.operators.tables;

import database.RetailService;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.nio.file.Files.readAllBytes;

public class ProductsTable extends RetailService {
    final private static String SQL = "./src/main/java/database/sql/products/getRow.sql";

    public PreparedStatement insert(String productID, String productType, boolean isNew) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO products (Product_ID, ProductType, isNew)\n" +
                            "VALUES (?, ?, ?)"
            );
            statement.setString(1, productID);
            statement.setString(2, productType);
            statement.setBoolean(3, isNew);
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }

    public String getRow(int row) {
        String productID = null;
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
            productID = res.getString(1);
            close(res, statement);
        } catch (SQLException | IOException e) {
            printError(e);
        }
        return productID;
    }
}