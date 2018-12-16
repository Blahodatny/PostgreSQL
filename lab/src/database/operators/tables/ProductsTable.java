package database.operators.tables;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductsTable extends database.RetailService {
    final private String sql = path + "/src/database/sql/products/getRow.sql";

    public PreparedStatement insert(String productID, String productType, boolean isNew) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO PRODUCTS (Product_ID, ProductType, isNew)\n" +
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
                            java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(sql))
                    )
            );
            statement.setInt(1, row);
            var res = statement.executeQuery();
            res.next();
            productID = res.getString(1);
            close(res, statement);
        } catch (SQLException | java.io.IOException e) {
            printError(e);
        }
        return productID;
    }
}