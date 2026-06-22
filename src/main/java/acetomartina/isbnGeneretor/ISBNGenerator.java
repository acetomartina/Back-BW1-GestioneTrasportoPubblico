package acetomartina.isbnGeneretor;

import java.util.Random;

public class ISBNGenerator {
    public static String generateISBN13() {
        Random random = new Random();
        StringBuilder isbn = new StringBuilder("978"); // prefisso Bookland standard
        for (int i = 0; i < 9; i++) {
            isbn.append(random.nextInt(10));
        }
        int checkDigit = calculateCheckDigit(isbn.toString());
        isbn.append(checkDigit);
        return isbn.toString();
    }

    private static int calculateCheckDigit(String isbn12) {
        int sum = 0;
        for (int i = 0; i < isbn12.length(); i++) {
            int digit = Character.getNumericValue(isbn12.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int remainder = sum % 10;
        return (remainder == 0) ? 0 : 10 - remainder;
    }
}

