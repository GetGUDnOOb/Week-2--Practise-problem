import java.util.*;

public class StringUtilityApp {
    private static StringBuilder output = new StringBuilder();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== STRING UTILITY APPLICATION ===");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Text Analysis");
            System.out.println("2. String Transformation");
            System.out.println("3. ASCII Operations");
            System.out.println("4. Performance Testing");
            System.out.println("5. String Comparison Analysis");
            System.out.println("6. Custom String Algorithms");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter text for analysis: ");
                    performTextAnalysis(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter text to transform: ");
                    String text = scanner.nextLine();
                    System.out.print("Enter operations (comma-separated: trim,upper,lower,replace_a_b): ");
                    String[] ops = scanner.nextLine().split(",");
                    System.out.println("Transformed: " + performTransformations(text, ops));
                    break;
                case 3:
                    System.out.print("Enter text for ASCII operations: ");
                    performASCIIOperations(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Enter iteration count for performance test: ");
                    performPerformanceTest(scanner.nextInt());
                    scanner.nextLine(); // Clear buffer
                    break;
                case 5:
                    System.out.print("Enter first string: ");
                    String s1 = scanner.nextLine();
                    System.out.print("Enter second string: ");
                    String s2 = scanner.nextLine();
                    performComparisonAnalysis(new String[]{s1, s2});
                    break;
                case 6:
                    System.out.print("Enter text for custom analysis: ");
                    performCustomAlgorithms(scanner.nextLine());
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }

            displayResults();
            output.setLength(0); // Clear output for next round
        }
    }

    // 1. Text Analysis
    public static void performTextAnalysis(String text) {
        output.append("--- TEXT ANALYSIS ---\n");
        output.append("Length: ").append(text.length()).append("\n");

        String[] words = text.trim().split("\\s+");
        output.append("Word Count: ").append(words.length).append("\n");

        String[] sentences = text.split("[.!?]");
        output.append("Sentence Count: ").append(sentences.length).append("\n");

        String[] paragraphs = text.split("\n");
        output.append("Paragraph Count: ").append(paragraphs.length).append("\n");

        // Character frequency
        Map<Character, Integer> freq = new TreeMap<>();
        for (char c : text.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }
        }
        output.append("Character Frequency:\n");
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            output.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
    }

    // 2. String Transformations
    public static String performTransformations(String text, String[] operations) {
        StringBuilder sb = new StringBuilder(text);
        for (String op : operations) {
            if (op.equalsIgnoreCase("trim")) sb = new StringBuilder(sb.toString().trim());
            else if (op.equalsIgnoreCase("upper")) sb = new StringBuilder(sb.toString().toUpperCase());
            else if (op.equalsIgnoreCase("lower")) sb = new StringBuilder(sb.toString().toLowerCase());
            else if (op.startsWith("replace_")) {
                String[] parts = op.split("_");
                if (parts.length == 3) {
                    sb = new StringBuilder(sb.toString().replace(parts[1], parts[2]));
                }
            }
        }
        return sb.toString();
    }

    // 3. ASCII Operations
    public static void performASCIIOperations(String text) {
        output.append("--- ASCII OPERATIONS ---\n");
        for (char c : text.toCharArray()) {
            output.append(c).append(": ").append((int) c).append("\n");
        }

        // Caesar cipher with shift = 3
        output.append("Caesar Encrypted (+3): ");
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                output.append((char) ((c - base + 3) % 26 + base));
            } else {
                output.append(c);
            }
        }
        output.append("\n");
    }

    // 4. Performance Testing
    public static void performPerformanceTest(int iterations) {
        output.append("--- PERFORMANCE TESTING ---\n");

        // String
        long start = System.nanoTime();
        String s = "";
        for (int i = 0; i < iterations; i++) {
            s += "a";
        }
        long end = System.nanoTime();
        output.append("String time: ").append(end - start).append(" ns\n");

        // StringBuilder
        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("a");
        }
        end = System.nanoTime();
        output.append("StringBuilder time: ").append(end - start).append(" ns\n");

        // StringBuffer
        start = System.nanoTime();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sf.append("a");
        }
        end = System.nanoTime();
        output.append("StringBuffer time: ").append(end - start).append(" ns\n");
    }

    // 5. String Comparison
    public static void performComparisonAnalysis(String[] strings) {
        String str1 = strings[0];
        String str2 = strings[1];
        output.append("--- STRING COMPARISON ---\n");
        output.append("== : ").append(str1 == str2).append("\n");
        output.append(".equals: ").append(str1.equals(str2)).append("\n");
        output.append(".equalsIgnoreCase: ").append(str1.equalsIgnoreCase(str2)).append("\n");
        output.append("compareTo: ").append(str1.compareTo(str2)).append("\n");
        output.append("compareToIgnoreCase: ").append(str1.compareToIgnoreCase(str2)).append("\n");

        double similarity = calculateSimilarity(str1, str2);
        output.append("Similarity: ").append(String.format("%.2f", similarity)).append("%\n");
    }

    // Levenshtein similarity
    private static double calculateSimilarity(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for (int i = 0; i <= s1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= s2.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = s1.charAt(i-1) == s2.charAt(j-1) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(
                        dp[i-1][j] + 1,
                        dp[i][j-1] + 1),
                        dp[i-1][j-1] + cost);
            }
        }

        int maxLen = Math.max(s1.length(), s2.length());
        return maxLen == 0 ? 100 : (1 - (double) dp[s1.length()][s2.length()] / maxLen) * 100;
    }

    // 6. Custom Algorithms
    public static void performCustomAlgorithms(String text) {
        output.append("--- CUSTOM ALGORITHMS ---\n");

        // Palindrome check
        String cleaned = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        output.append("Is palindrome: ").append(cleaned.equals(reversed)).append("\n");

        // Anagram check (simple test for two words)
        String[] parts = text.split("\\s+");
        if (parts.length == 2) {
            char[] a1 = parts[0].toLowerCase().toCharArray();
            char[] a2 = parts[1].toLowerCase().toCharArray();
            Arrays.sort(a1);
            Arrays.sort(a2);
            output.append("Is anagram: ").append(Arrays.equals(a1, a2)).append("\n");
        } else {
            output.append("Anagram check skipped (provide exactly two words)\n");
        }

        // Pattern match
        if (text.matches(".*\\d{3}.*")) {
            output.append("Contains a 3-digit number.\n");
        } else {
            output.append("No 3-digit pattern found.\n");
        }
    }

    // Display final result
    public static void displayResults() {
        System.out.println("\n--- RESULT ---");
        System.out.println(output);
    }
}

