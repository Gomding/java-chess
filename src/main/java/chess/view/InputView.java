package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String COMMAND_DELIMITER = " ";

    private InputView() {
    }

    private final static Scanner SCANNER = new Scanner(System.in);

    public static List<String> getUserCommand() {
        return Arrays.asList(SCANNER.nextLine().split(COMMAND_DELIMITER));
    }
}
