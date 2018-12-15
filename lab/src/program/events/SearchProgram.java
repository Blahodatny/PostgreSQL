package program.events;

import database.search.Search;

public class SearchProgram implements interfaces.IScanner, interfaces.IInput {
    private final Search search = new Search();

    public void run() {
        System.out.println(
                "Please, enter a search you wanna perform\nFor help enter \'4\'"
        );
        while (true) {
            var num = scanner.nextByte();
            switch (num) {
                case 1:
                    System.out.println(search.trigramSearch(input.search()));
                    break;

                case 2:
                    search.likeSearch(input.search())
                            .stream().map(java.util.Arrays::toString).forEach(System.out::println);
                    break;

                case 3:
                    break;

                case 4:
                    System.out.println(
                            "1 - trigram search\n" +
                                    "2 - LIKE search\n" +
                                    "4 - help\n" +
                                    "any other - exit to main loop"
                    );
                    break;

                case 5:
                    break;

                default:
                    return;
            }
        }
    }
}