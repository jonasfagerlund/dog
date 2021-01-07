/**
 * @author Jonas Andersson Fagerlund joan1043
 */

import java.util.Scanner;

public class KeyboardInput {

    private static Scanner keyboardInput = new Scanner(System.in);

    public int readInt(String expectedInput) {
        System.out.print(expectedInput + "?> ");
        int userInput = keyboardInput.nextInt();
        keyboardInput.nextLine();
        return userInput;
    }

    public double readDouble(String expectedInput) {
        System.out.print(expectedInput + "?> ");
        double userInput = keyboardInput.nextDouble();
        keyboardInput.nextLine();
        return userInput;
    }

    public String readString(String expectedInput) {
        System.out.print(expectedInput + "?> ");
        String userInput = keyboardInput.nextLine();
        return capitalize(userInput);
    }

    public void readNextLine() {
        keyboardInput.nextLine();
    }

    public String readEnum(String expectedInput) {
        System.out.print(expectedInput + "?> ");
        String userInput = keyboardInput.nextLine();
        return enumFormat(userInput);
    }

    private String enumFormat(String s) {
        s = s.trim();
        s = s.toUpperCase();
        s = s.replaceAll("\\s+", "_");
        return s;
    }

    private String capitalize(String str) {
        if (str == null || str.isBlank()) {
            str = "A. nonym";
            return str;
        } else {
            str = str.trim();
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
    }
}