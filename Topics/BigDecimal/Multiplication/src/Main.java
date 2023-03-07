import java.math.BigDecimal;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        BigDecimal first = scanner.nextBigDecimal();
        BigDecimal second = scanner.nextBigDecimal();
        System.out.println(first.multiply(second));
    }
}