package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RetailService {
    private Connection connection;

    public RetailService(String db_name, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/" + db_name, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully!!!");
    }

    public void createTables() {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE CUSTOMER (\n" +
                            "  Phone CHAR(20),\n" +
                            "  FirstName CHAR(20),\n" +
                            "  LastName CHAR(20),\n" +
                            "  Street TEXT,\n" +
                            "  City TEXT,\n" +
                            "  PRIMARY KEY (Phone)\n" +
                            ");\n" +
                            "\n" +
                            "CREATE TABLE ORDER (\n" +
                            "  Order_number KEY,\n" +
                            "  Phone CHAR(20),\n" +
                            "  To_street TEXT,\n" +
                            "  To_city TEXT,\n" +
                            "  Ship_date DATETIME,\n" +
                            "  Product_ID KEY,\n" +
                            "  PRIMARY KEY (Order_number),\n" +
                            "  KEY FK (Phone, Product_ID)\n" +
                            ");\n" +
                            "\n" +
                            "CREATE TABLE PRODUCT (\n" +
                            "  Product_ID KEY,\n" +
                            "  Quantity INT,\n" +
                            "  isNew BOOLEAN,\n" +
                            "  PRIMARY KEY (Product_ID)\n" +
                            ");"
            );
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully!!!");
    }
}