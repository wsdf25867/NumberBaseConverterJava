package converter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {

    static final String possibleValues = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String convertFromDecimal(String number, String base) {
        BigInteger[] pair;
        BigInteger numberBig = new BigInteger(number);

        String index;
        StringBuilder output = new StringBuilder();
        do {
            pair = numberBig.divideAndRemainder(BigInteger.valueOf(Integer.parseInt(base)));
            index = pair[1].toString();
            output.append(possibleValues.charAt(Integer.parseInt(index)));
            numberBig = numberBig.divide(BigInteger.valueOf(Integer.parseInt(base)));
        } while (numberBig.compareTo(BigInteger.valueOf(Integer.parseInt(base))) >= 0);
        output.reverse();
       if (!numberBig.equals(0)) {
            output.insert(0, possibleValues.charAt(Integer.parseInt(numberBig.toString())));
        }
        return output.toString().replaceFirst("0*", "");
    }

    private static String convertToDecimal(String number, String base) {

        double result = 0;
        BigDecimal resultBig = new BigDecimal("0");
        for (int i = 0, j = number.length() - 1; i < number.length(); i++, j--) {
            result = possibleValues.indexOf(Character.toString(number.charAt(j)).toUpperCase()) *
                    Math.pow(Integer.parseInt(base),i);
            resultBig = resultBig.add(BigDecimal.valueOf((long) result));
        }
        return resultBig.toString();
    }
    private static String convertFractionalFromDecimal(String number, String base) {
        if (number.equals("0") || number.equals("00000")) {
            return "00000";
        } else {
            String[] pair;
            BigDecimal numberBig = new BigDecimal(number);
            String index;
            StringBuilder output = new StringBuilder();

            do {
                numberBig = numberBig.multiply(BigDecimal.valueOf(Integer.parseInt(base)))
                        .setScale(6, RoundingMode.HALF_DOWN);
                pair = numberBig.toString().split("\\.");
                index = pair[0];
                output.append(possibleValues.charAt(Integer.parseInt(index)));
                numberBig = numberBig.subtract(BigDecimal.valueOf(Long.parseLong(pair[0])));
            } while (numberBig.compareTo(BigDecimal.ZERO) != 0 && output.toString().length() < 5);

            if (output.length() < 5) {
                for (int i = output.length(); i < 5; i++) {
                    output.append("0");
                }
            }

            return output.toString();
        }
    }
    private static String convertFractionalToDecimal(String number, String base) {

        if (number.equals("0")) {
            return "00000";
        } else {
            double result = 0;

            BigDecimal resultBig = new BigDecimal("0");
            for (int i = -1, j = 0; abs(i) <= number.length(); i--, j++) {
                result = possibleValues.indexOf(Character.toString(number.charAt(j)).toUpperCase()) *
                        Math.pow(Integer.parseInt(base), i);
                resultBig = resultBig.add(BigDecimal.valueOf(result)).setScale(6, RoundingMode.HALF_DOWN);
            }
            return resultBig.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = new String();
        String sourceBase = new String();
        String targetBase = new String();
        String[] numbers = null;
        String[] number = null;
        String integerPart = new String();
        String fractionalPart = new String();
        String splitter = new String();

        for (;;) {

            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
            command = scanner.nextLine();
            if (command.equals("/exit")) {
                return;
            } else {

                numbers = command.split(" ");
                sourceBase = numbers[0];
                targetBase = numbers[1];

                for (;;) {

                    System.out.printf("Enter number in base %s to convert to base %s " +
                            "(To go back type /back) ", sourceBase, targetBase);

                    command = scanner.nextLine();
                    if (command.equals("/back")) {
                        System.out.println();
                        break;
                    } else {
                        if (command.indexOf(".") > 0) {
                            number = command.split("\\.");
                            integerPart = number[0];
                            fractionalPart = number[1];
                            if (integerPart.equals("0")) {
                                splitter = "0.";
                            } else {
                                splitter = ".";
                            }
                            System.out.print("Conversion result: ");
                            System.out.println(
                                    convertFromDecimal(
                                    convertToDecimal(integerPart, sourceBase), targetBase) + splitter +
                                    convertFractionalFromDecimal(
                                    convertFractionalToDecimal(fractionalPart, sourceBase), targetBase));
                            System.out.println();
                        } else {
                            System.out.print("Conversion result: ");
                            System.out.println(convertFromDecimal(convertToDecimal(command, sourceBase), targetBase));
                            System.out.println();
                        }

                    }
                }

            }

        }

    }
}