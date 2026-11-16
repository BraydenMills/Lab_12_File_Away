import java.util.Scanner;

/** Small self-contained SafeInput helper for this project. */
public class SafeInput {
    public static String getNonZeroLenString(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String value = in.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("Please enter a value.");
        }
    }
    public static int getRangedInt(Scanner in, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt + " (" + min + "-" + max + "): ");
            String input = in.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) { }
            System.out.println("Enter a valid whole number in range.");
        }
    }
    public static boolean getYNConfirm(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt + " (Y/N): ");
            String input = in.nextLine().trim();
            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes")) return true;
            if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("No")) return false;
            System.out.println("Please enter Y or N.");
        }
    }
}
