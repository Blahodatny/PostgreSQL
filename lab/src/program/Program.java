package program;

import database.RetailService;
import program.events.CustomerProgram;
import program.events.OrderProgram;
import program.events.SearchProgram;
import random.Randomize;

import java.util.Scanner;

public class Program {
    private final Scanner scanner = new Scanner(System.in);

    public void run(RetailService service) {
        System.out.println(
                "Please, enter an action you want to perform\nFor help enter \'4\'"
        );
        while (true) {
            var num = scanner.nextByte();
            switch (num) {
                case 1:
                    new CustomerProgram().run();
                    break;

                case 2:
                    new OrderProgram().run();
                    break;

                case 3:
                    new Randomize(service::operate).randomize();
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
                    new SearchProgram().run();
                    break;

                default:
                    return;
            }
        }
    }
}