import java.math.BigInteger;

class DoubleFactorial {

    public static BigInteger calcDoubleFactorial(int n) {
        // type your java code here
        if (n < 2) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(n).multiply(calcDoubleFactorial(n - 2));
    }
}