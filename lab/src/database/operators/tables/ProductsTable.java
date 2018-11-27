package database.operators.tables;

import database.RetailService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductsTable extends RetailService {
    public PreparedStatement insert(String productType, boolean isNew) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO PRODUCTS (ProductType, isNew) VALUES (?, ?)"
            );
            statement.setString(1, productType);
            statement.setString(2, String.valueOf(isNew));
        } catch (SQLException e) {
            printError(e);
        }
        return statement;
    }
}
