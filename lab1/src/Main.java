import database.RetailService;
import database.operators.Initialization;
import database.operators.tables.CustomersTable;
import database.operators.tables.OrdersTable;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

class Main {
    public static void main(String[] args) {
        var service = new RetailService("Retail_Service", "postgres", "Dima4532");
//        var service = new Initialization("Retail_Service", "postgres", "Dima4532");
//        service.createTables();
//        service.insert(() ->
//                new CustomersTable().insert("+380-56-456-34-42", "Valera", "Tovol", "Marka st.", "Kyiv"));
        service.insert(() ->
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
        service.closeConnection();
    }
}
