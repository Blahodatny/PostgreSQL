package database.operators;

import database.RetailService;

public class Initialization extends RetailService {
    public Initialization(String db_name, String user, String password) {
        super(db_name, user, password);
    }

    public void createTables() {
        try {
            var statement = connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE CUSTOMERS (\n" +
                            "  Phone CHAR(20) NOT NULL,\n" +
                            "  FirstName CHAR(20) NOT NULL,\n" +
                            "  LastName CHAR(20) NOT NULL,\n" +
                            "  Street TEXT,\n" +
                            "  City TEXT NOT NULL\n" +
                            ");\n" +
                            "\n" +
                            "CREATE UNIQUE INDEX CUSTOMERS_PHONE_UINDEX ON CUSTOMERS (Phone);" +
                            "\n" +
                            "ALTER TABLE CUSTOMERS ADD CONSTRAINT CUSTOMERS_PK PRIMARY KEY (Phone);" +
                            "\n" +
                            "CREATE TABLE ORDERS (\n" +
                            "  Order_Number SERIAL NOT NULL,\n" +
                            "  Phone CHAR(20) NOT NULL,\n" +
                            "  ToStreet TEXT NOT NULL,\n" +
                            "  ToCity TEXT NOT NULL,\n" +
                            "  ShipDate DATE NOT NULL,\n" +
                            "  PRIMARY KEY (Order_Number),\n" +
                            "  CONSTRAINT FK FOREIGN KEY (Phone) REFERENCES CUSTOMERS (Phone)\n" +
                            ");\n" +
                            "\n" +
                            "CREATE TABLE PRODUCTS (\n" +
                            "  Product_ID SERIAL NOT NULL,\n" +
                            "  ProductType TEXT NOT NULL,\n" +
                            "  isNew BOOLEAN NOT NULL,\n" +
                            "  PRIMARY KEY (Product_ID)\n" +
                            ");\n" +
                            "\n" +
                            "CREATE TABLE ORDER_ITEMS (\n" +
                            "  Item_ID SERIAL NOT NULL,\n" +
                            "  Quantity INT NOT NULL,\n" +
                            "  Order_Number INT NOT NULL ,\n" +
                            "  Product_ID INT NOT NULL ,\n" +
                            "  PRIMARY KEY (Item_ID)\n" +
                            ");\n" +
                            "\n" +
                            "CREATE INDEX FK ON ORDER_ITEMS (Order_Number, Product_ID);"
            );
            statement.close();
            connection.close();
        } catch (Exception e) {
            printError(e);
        }
        System.out.println("Tables created successfully!!!");
    }
}