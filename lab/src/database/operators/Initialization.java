package database.operators;

import database.RetailService;

public class Initialization extends RetailService {
    public Initialization(String db_name, String user, String password) {
        super(db_name, user, password);
    }

    public void createTables() {
        try {
            var statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS CUSTOMERS (\n" +
                    "  Phone VARCHAR (20) NOT NULL,\n" +
                    "  FirstName VARCHAR (20) NOT NULL,\n" +
                    "  LastName VARCHAR (20) NOT NULL,\n" +
                    "  Street TEXT,\n" +
                    "  City TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (Phone)\n" +
                    ");\n" +
                    "\n" +
                    "CREATE UNIQUE INDEX IF NOT EXISTS CUSTOMERS_PHONE_UINDEX ON CUSTOMERS (Phone);" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS ORDERS (\n" +
                    "  Order_Number SERIAL NOT NULL,\n" +
                    "  Phone VARCHAR (20) NOT NULL,\n" +
                    "  ToStreet TEXT NOT NULL,\n" +
                    "  ToCity TEXT NOT NULL,\n" +
                    "  ShipDate TIMESTAMP NOT NULL,\n" +
                    "  PRIMARY KEY (Order_Number),\n" +
                    "  CONSTRAINT FK FOREIGN KEY (Phone) REFERENCES CUSTOMERS (Phone) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS PRODUCTS (\n" +
                    "  Product_ID VARCHAR (20) NOT NULL,\n" +
                    "  ProductType TEXT NOT NULL,\n" +
                    "  isNew BOOLEAN NOT NULL,\n" +
                    "  PRIMARY KEY (Product_ID)\n" +
                    ");\n" +
                    "\n" +
                    "CREATE UNIQUE INDEX IF NOT EXISTS PRODUCTS_PRODUCT_ID_UINDEX ON PRODUCTS (Product_ID);" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS ORDER_ITEMS (\n" +
                    "  Item_ID SERIAL NOT NULL,\n" +
                    "  Quantity INT NOT NULL,\n" +
                    "  Order_Number INT NOT NULL,\n" +
                    "  Product_ID VARCHAR (20) NOT NULL,\n" +
                    "  PRIMARY KEY (Item_ID),\n" +
                    "  CONSTRAINT FK FOREIGN KEY (Order_number) REFERENCES ORDERS (Order_number) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT FK1 FOREIGN KEY (Product_id) REFERENCES PRODUCTS (Product_ID) ON DELETE NO ACTION ON UPDATE CASCADE\n" +
                    ");\n" +
                    "\n"
            );
            statement.close();
        } catch (Exception e) {
            printError(e);
        }
        System.out.println("Tables created successfully!!!");
    }
}