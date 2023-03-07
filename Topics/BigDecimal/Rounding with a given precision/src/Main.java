import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        BigDecimal bigDecimal = scanner.nextBigDecimal();
        int newScale = scanner.nextInt();
        BigDecimal result = bigDecimal.setScale(newScale, RoundingMode.HALF_DOWN);
        System.out.println(result);
    }   
}