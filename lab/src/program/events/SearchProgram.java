package program.events;

import database.search.Search;
import interfaces.IInput;
import interfaces.IScanner;

public class SearchProgram implements IScanner, IInput {
    private final Search search = new Search();

    public void run() {
        System.out.println(
                "Please, enter a search you wanna perform\nFor help enter \'4\'"
        );
        while (true) {
            var num = scanner.nextByte();
            switch (num) {
                case 1:
                    search.fts(input.search());
                    break;

                case 2:
                    break;

                case 4:
                    System.out.println(
                            "1 - full text search\n" +
                                    "2 - update customer\n" +
                                    "4 - help\n" +
                                    "any other - exit to main loop"
                    );
                    break;

                default:
                    return;
            }
        }
    }
}