import database.operators.Initialization;
import database.operators.tables.CustomersTable;
import database.operators.tables.OrdersTable;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

class Main {
    public static void main(String[] args) {
        var service = new Initialization("Retail_Service", "postgres", "Dima4532");
        service.createTables();
//        service.operate(() ->
//        {
//            try {
//                return new CustomersTable().insert("+380-56-456-34-42", "Valera", "Tovol", "Marka st.", "Kyiv");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return null;
//        });
        service.operate(() ->
        {
            try {
                return new OrdersTable().insert(
                        "+380-56-456-34-42",
                        "Malina st.",
                        "Moscow",
                        new Date(Calendar.getInstance().getTimeInMillis()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });

        service.operate(() ->
        {
            try {
                return new CustomersTable().delete("+380-56-456-34-42");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
        service.closeConnection();
    }
}
