import java.util.*;

public class AdvancedStringAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== ADVANCED STRING ANALYZER ===");

        // Ask user for two strings
        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();

        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();

        // Perform all comparisons
        performAllComparisons(str1, str2);

        // Performance and memory analysis
        analyzeMemoryUsage(str1, str2);

        // Optimized processing using StringBuilder
        String[] exampleInputs = {"This", "is", "a", "test", "string."};
        System.out.println("\nOptimized Processing Output:");
        System.out.println(optimizedStringProcessing(exampleInputs));

        // Demonstrate intern()
        demonstrateStringIntern();

        scanner.close();
    }

    // 1. Perform all comparison types
    public static void performAllComparisons(String str1, String str2) {
        System.out.println("\n--- COMPARISON RESULTS ---");

        // 1. Reference equality
        System.out.println("Reference Equality (==): " + (str1 == str2));

        // 2. Content equality
        System.out.println("Content Equality (.equals): " + str1.equals(str2));

        // 3. Case-insensitive equality
        System.out.println("Case-insensitive Equality (.equalsIgnoreCase): " + str1.equalsIgnoreCase(str2));

        // 4. Lexicographic comparison
        System.out.println("Lexicographic Comparison (.compareTo): " + str1.compareTo(str2));

        // 5. Case-insensitive lexicographic
        System.out.println("Case-insensitive Lexicographic (.compareToIgnoreCase): " + str1.compareToIgnoreCase(str2));

        // 6. Similarity percentage
        double similarity = calculateSimilarity(str1, str2);
        System.out.printf("Similarity Percentage: %.2f%%\n", similarity);
    }

    // 2. Calculate string similarity percentage using Levenshtein distance
    public static double calculateSimilarity(String str1, String str2) {
        int distance = levenshteinDistance(str1, str2);
        int maxLength = Math.max(str1.length(), str2.length());
        if (maxLength == 0) return 100.0;
        return ((1 - (double) distance / maxLength) * 100);
    }

    // Levenshtein Distance Algorithm
    private static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++)
            dp[i][0] = i;
        for (int j = 0; j <= s2.length(); j++)
            dp[0][j] = j;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;

                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1,      // deletion
                                 dp[i][j - 1] + 1),     // insertion
                                 dp[i - 1][j - 1] + cost); // substitution
            }
        }
        return dp[s1.length()][s2.length()];
    }

    // 3. Analyze memory usage of strings
    public static void analyzeMemoryUsage(String... strings) {
        System.out.println("\n--- MEMORY USAGE ESTIMATE ---");
        for (String str : strings) {
            // Estimate: 40 bytes base + 2 bytes per char
            int estimatedBytes = 40 + str.length() * 2;
            System.out.println("'" + str + "' => Approx. " + estimatedBytes + " bytes");
        }
    }

    // 4. Use StringBuilder for efficient concatenation
    public static String optimizedStringProcessing(String[] inputs) {
        StringBuilder builder = new StringBuilder();
        for (String s : inputs) {
            builder.append(s).append(" ");
        }
        return builder.toString().trim();
    }

    // 5. Demonstrate intern() behavior
    public static void demonstrateStringIntern() {
        System.out.println("\n--- INTERN() DEMONSTRATION ---");

        String a = new String("hello");
        String b = new String("hello");

        System.out.println("Before intern(): a == b? " + (a == b));

        String aInterned = a.intern();
        String bInterned = b.intern();

        System.out.println("After intern(): aInterned == bInterned? " + (aInterned == bInterned));
    }
}
