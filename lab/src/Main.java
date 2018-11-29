import database.operators.Initialization;
import database.operators.tables.CustomersTable;
import database.operators.tables.OrdersTable;
import database.random.RandomData;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        var service = new Initialization("Retail_Service", "postgres", "Dima4532");
        service.createTables();
//        RandomData.randomize(service::operate);
//        service.operate(() ->
//        {
//            try {
//                return new CustomersTable("+380-56-456-34-42").insert("Valera", "Tovol", "Marka st.", "Kyiv");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return null;
//        });
//
//        service.operate(() ->
//        {
//            try {
//                return new OrdersTable().insert(
//                        "+380-56-456-34-42",
//                        "Malina st.",
//                        "Moscow"
//                );
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return null;
//        });

//        service.operate(() ->
//        {
//            try {
//                return new CustomersTable().delete();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return null;
//        });
//        service.closeConnection();
    }
}
