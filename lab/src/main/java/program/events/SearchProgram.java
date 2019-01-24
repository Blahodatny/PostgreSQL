package program.events;

import database.search.Search;
import database.search.LikeSearch;
import interfaces.DataInput;
import interfaces.DataScanner;

import java.util.Arrays;

public class SearchProgram implements DataScanner, DataInput {
    public void run() {
        System.out.println(
                "Please, enter a search you wanna perform\nFor help enter \'4\'"
        );
        while (true) {
            var num = scanner.nextByte();
            switch (num) {
                case 1:
                    System.out.println(new LikeSearch().search(input.search().get(0)));
                    break;

                case 2:
                case 3:
                case 5:
                    new Search().search(num, input.search())
                            .stream().map(Arrays::toString).forEach(System.out::println);
                    break;

                case 4:
                    System.out.println(
                            "1 - LIKE search\n" +
                                    "2 - trigram search\n" +
                                    "3 - mandatory entry\n" +
                                    "4 - help\n" +
                                    "5 - non-entry\n" +
                                    "any other - exit to main loop"
                    );
                    break;

                default:
                    return;
            }
        }
    }
}