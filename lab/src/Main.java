import database.operators.Initialization;
import database.operators.tables.CustomersTable;
import database.operators.tables.OrdersTable;

import java.sql.SQLException;

class Main {
    public static void main(String[] args) {
        var service = new Initialization("Retail_Service", "postgres", "Dima4532");
        service.createTables();
        service.operate(() ->
        {
            try {
                return new CustomersTable("+380-56-456-34-42").insert("Valera", "Tovol", "Marka st.", "Kyiv");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });

        service.operate(() ->
        {
            try {
                return new OrdersTable().insert(
                        "+380-56-456-34-42",
                        "Malina st.",
                        "Moscow"
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });

//        service.operate(() ->
//        {
//            try {
//                return new CustomersTable().delete();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return null;
//        });
        service.closeConnection();
    }
}
