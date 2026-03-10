import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class WordFrequencyAnalyzer {

    public static void main(String[] args) {

        Path filePath = Paths.get("hello.txt");

        TextTools tools = new TextTools();

        String fileContent = tools.readFile(filePath);

        if (fileContent == null) {
            System.out.println("Failed to read file.");
            return;
        }

        String[] words = tools.cleanText(fileContent);

        Map<String, Integer> frequency = tools.countWords(words);

        System.out.println("Word Frequencies:");
        System.out.println(frequency);
    }
}

class TextTools {

    // Reads the entire file and returns the content as a string
    public String readFile(Path filePath) {
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Cleans the text: lowercase + remove punctuation
    public String[] cleanText(String fileContent) {

        String cleaned = fileContent.toLowerCase();
        cleaned = cleaned.replaceAll("[^a-zA-Z ]", "");

        return cleaned.split(" ");
    }

    // Counts frequency of each word
    public Map<String, Integer> countWords(String[] words) {

        Map<String, Integer> frequency = new HashMap<>();

        for (String word : words) {

            if (word.isEmpty()) {
                continue;
            }

            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        return frequency;
    }
}