import java.util.*;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user to enter a sentence with mixed formatting
        System.out.print("Enter a sentence with mixed formatting: ");
        String input = scanner.nextLine();

        // 1. trim() - Remove extra spaces from beginning and end
        input = input.trim();
        System.out.println("Trimmed: " + input);

        // 2. replace() - Replace all spaces with underscores
        String replacedSpaces = input.replace(" ", "_");
        System.out.println("Spaces replaced with underscores: " + replacedSpaces);

        // 3. replaceAll() - Remove all digits using regex
        String noDigits = input.replaceAll("\\d", "");
        System.out.println("Removed digits: " + noDigits);

        // 4. split() - Split sentence into words array
        String[] words = input.split("\\s+");
        System.out.println("Split words: " + Arrays.toString(words));

        // 5. join() - Rejoin words with " | " separator
        String rejoined = String.join(" | ", words);
        System.out.println("Rejoined with '|': " + rejoined);

        // Custom processing methods
        String noPunctuation = removePunctuation(input);
        System.out.println("Removed punctuation: " + noPunctuation);

        String capitalized = capitalizeWords(input);
        System.out.println("Capitalized words: " + capitalized);

        String reversedOrder = reverseWordOrder(input);
        System.out.println("Reversed word order: " + reversedOrder);

        System.out.println("Word frequency:");
        countWordFrequency(input);

        scanner.close();
    }

    // Method to remove punctuation
    public static String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", "");
    }

    // Method to capitalize each word
    public static String capitalizeWords(String text) {
        String[] words = text.trim().toLowerCase().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1))
                      .append(" ");
            }
        }
        return result.toString().trim();
    }

    // Method to reverse word order
    public static String reverseWordOrder(String text) {
        String[] words = text.trim().split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    // Method to count word frequency
    public static void countWordFrequency(String text) {
        String cleaned = removePunctuation(text.toLowerCase());
        String[] words = cleaned.split("\\s+");
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
