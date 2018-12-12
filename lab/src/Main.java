import database.RetailService;
import database.operators.tables.ProductsTable;
import program.Program;

class Main {
    public static void main(String[] args) {
        var service = new RetailService("Retail_Service", "postgres", "Dima4532");
        System.out.println(new ProductsTable().getRow(1));
//        new Program().run(service);
        service.closeConnection();
    }
}