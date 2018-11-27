import database.RetailService;

class Main {
    public static void main(String[] args) {
        var service = new RetailService("Retail_Service", "postgres", "Dima4532");
        service.createTables();
    }
}
