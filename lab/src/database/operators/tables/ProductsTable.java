package database.operators.tables;

import database.RetailService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductsTable extends RetailService {
    public PreparedStatement insert(String productID, String productType, boolean isNew) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO PRODUCTS (Product_ID, ProductType, isNew) VALUES (?, ?, ?)");
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
                    "SELECT Product_ID FROM (" +
                            "SELECT Product_ID, ROW_NUMBER () OVER (ORDER BY Product_ID) FROM PRODUCTS" +
                            ") X WHERE ROW_NUMBER = ?");
            statement.setInt(1, row);
            var res = statement.executeQuery();
            res.next();
            productID = res.getString(1);
            res.close();
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            printError(e);
        }
        return productID;
    }
}