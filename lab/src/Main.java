import database.RetailService;
import database.operators.tables.CustomersTable;

class Main {
    public static void main(String[] args) {
        var service = new RetailService("Retail_Service", "postgres", "Dima4532");
//        var service = new Initialization("Retail_Service", "postgres", "Dima4532");
//        service.createTables();
        service.insert(() ->
                new CustomersTable().insert("+380-56-456-34-42", "Valera", "Tovol", "Marka st.", "Kyiv"));
        service.closeConnection();
    }
}
