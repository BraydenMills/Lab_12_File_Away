import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {
        ArrayList<String> records = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        do {
            String first = SafeInput.getNonZeroLenString(in, "First name");
            String last = SafeInput.getNonZeroLenString(in, "Last name");
            int id = SafeInput.getRangedInt(in, "ID number (1-999999)", 1, 999999);
            String email = SafeInput.getNonZeroLenString(in, "Email");
            int birthYear = SafeInput.getRangedInt(in, "Year of birth", 1000, 9999);
            records.add(String.join(",", csv(first), csv(last), String.format("%06d", id), csv(email), String.valueOf(birthYear)));
        } while (SafeInput.getYNConfirm(in, "Add another record?"));

        String name = SafeInput.getNonZeroLenString(in, "Output filename (without .csv)");
        name = name.replaceAll("(?i)\\.csv$", "");
        if (!name.matches("[A-Za-z0-9_-]+")) {
            System.err.println("Filename may contain only letters, numbers, underscores, and hyphens.");
            return;
        }
        Path src = Paths.get(System.getProperty("user.dir"), "src");
        try {
            Files.createDirectories(src);
            Path output = src.resolve(name + ".csv");
            Files.write(output, records, StandardCharsets.UTF_8);
            System.out.println("Saved " + records.size() + " records to " + output.toAbsolutePath());
        } catch (IOException ex) {
            System.err.println("Unable to save CSV: " + ex.getMessage());
        }
    }
    private static String csv(String text) {
        if (text.contains(",") || text.contains("\"") || text.contains("\n"))
            return "\"" + text.replace("\"", "\"\"") + "\"";
        return text;
    }
}
