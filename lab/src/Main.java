import database.operators.Initialization;
import program.CustomerProgram;

class Main {
    public static void main(String[] args) {
        var service = new Initialization("Retail_Service", "postgres", "Dima4532");
        service.createTables();
//        new Randomize(service::operate).randomize();
        new CustomerProgram().run();
        service.closeConnection();
    }
}