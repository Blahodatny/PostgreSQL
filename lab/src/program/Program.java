package program;

import java.util.Scanner;

public class Program {
    private final Scanner scanner = new Scanner(System.in);

    public void run(database.RetailService service) {
        System.out.println(
                "Please, enter an action you want to perform\nFor help enter \'4\'"
        );
        while (true) {
            var num = scanner.nextByte();
            switch (num) {
                case 1:
                    new program.events.CustomerProgram().run();
                    break;

                case 2:
                    new program.events.OrderProgram().run();
                    break;

                case 3:
                    new random.Randomize(service::operate).randomize();
                    break;

                case 4:
                    System.out.println(
                            "1 - work with customer\n" +
                                    "2 - work with order\n" +
                                    "3 - randomize data\n" +
                                    "4 - help\n" +
                                    "5 - search\n" +
                                    "any other - exit"
                    );
                    break;

                case 5:
                    new program.events.SearchProgram().run();
                    break;

                default:
                    return;
            }
        }
    }
}