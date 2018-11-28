package database.operators.tables;

import database.RetailService;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductsTable extends RetailService {
    public PreparedStatement insert(String productType, boolean isNew) throws SQLException {
        var statement = connection.prepareStatement(
                "INSERT INTO PRODUCTS (ProductType, isNew) VALUES (?, ?)"
        );
        statement.setString(1, productType);
        statement.setBoolean(2, isNew);
        return statement;
    }
}