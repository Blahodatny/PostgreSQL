import database.RetailService;
import program.Program;

class Main {
    public static void main(String[] args) {
        var service = new RetailService("Retail_Service", "postgres", "Dima4532");
        new Program().run(service);
        service.closeConnection();
    }
}