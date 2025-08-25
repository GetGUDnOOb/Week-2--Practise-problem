import java.util.*;

public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user to enter a string
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Process each character
        System.out.println("\n--- Character Analysis ---");
        for (char ch : input.toCharArray()) {
            int ascii = (int) ch;
            String type = classifyCharacter(ch);
            System.out.println("Character: '" + ch + "' | ASCII: " + ascii + " | Type: " + type);

            if (Character.isLetter(ch)) {
                char opposite = toggleCase(ch);
                System.out.println(" - Opposite Case: '" + opposite + "' | ASCII: " + (int) opposite);
                System.out.println(" - ASCII Difference: " + Math.abs(ascii - (int) opposite));
            }
        }

        // Display ASCII Table
        System.out.println("\n--- ASCII Table (32 to 126) ---");
        displayASCIITable(32, 126);

        // String to ASCII array
        System.out.println("\n--- String to ASCII Array ---");
        int[] asciiArray = stringToASCII(input);
        System.out.println(Arrays.toString(asciiArray));

        // ASCII array back to String
        System.out.println("\n--- ASCII Array to String ---");
        System.out.println(asciiToString(asciiArray));

        // Caesar Cipher
        System.out.print("\nEnter shift for Caesar cipher: ");
        int shift = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String encrypted = caesarCipher(input, shift);
        System.out.println("Encrypted (Caesar): " + encrypted);

        // ASCII Art (Bonus)
        System.out.println("\n--- ASCII Art ---");
        for (char ch : input.toCharArray()) {
            System.out.print((int) ch + " ");
        }
        System.out.println("\n");

        scanner.close();
    }

    // Method to classify character type
    public static String classifyCharacter(char ch) {
        if (Character.isUpperCase(ch)) return "Uppercase Letter";
        if (Character.isLowerCase(ch)) return "Lowercase Letter";
        if (Character.isDigit(ch)) return "Digit";
        return "Special Character";
    }

    // Method to toggle case using ASCII manipulation
    public static char toggleCase(char ch) {
        if (Character.isUpperCase(ch)) return (char)(ch + 32); // A->a
        if (Character.isLowerCase(ch)) return (char)(ch - 32); // a->A
        return ch; // non-letter stays the same
    }

    // Caesar cipher
    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char shifted = (char) ((ch - base + shift + 26) % 26 + base);
                result.append(shifted);
            } else {
                result.append(ch); // Leave non-letters unchanged
            }
        }
        return result.toString();
    }

    // Display ASCII table
    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.printf("%3d: %c\t", i, (char) i);
            if ((i - start + 1) % 8 == 0) System.out.println(); // Break every 8 columns
        }
        System.out.println();
    }

    // Convert string to ASCII array
    public static int[] stringToASCII(String text) {
        int[] ascii = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            ascii[i] = (int) text.charAt(i);
        }
        return ascii;
    }

    // Convert ASCII array to string
    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for (int code : asciiValues) {
            sb.append((char) code);
        }
        return sb.toString();
    }
}
