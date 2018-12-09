import database.operators.Initialization;
import program.Program;

class Main {
    public static void main(String[] args) {
        var service = new Initialization("Retail_Service", "postgres", "Dima4532");
        service.createTables();
        new Program().run(service);
        service.closeConnection();
    }
}