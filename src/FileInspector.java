import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileInspector {
    public static void main(String[] args) {
        Path src = Paths.get(System.getProperty("user.dir"), "src");
        JFileChooser chooser = new JFileChooser(Files.isDirectory(src) ? src.toFile() : Paths.get(".").toFile());
        chooser.setDialogTitle("Choose a text file to inspect");
        chooser.setFileFilter(new FileNameExtensionFilter("Text and CSV files", "txt", "csv"));
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected.");
            return;
        }
        Path file = chooser.getSelectedFile().toPath();
        int lines = 0, words = 0;
        long characters = 0;
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                lines++;
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) words += trimmed.split("\\s+").length;
                characters += line.length(); // Excludes newline separators, as instructed.
            }
            System.out.println("\n--- File Summary ---");
            System.out.println("File name: " + file.getFileName());
            System.out.println("Lines: " + lines);
            System.out.println("Words: " + words);
            System.out.println("Characters: " + characters);
        } catch (IOException ex) {
            System.err.println("Unable to read file: " + ex.getMessage());
        }
    }
}
