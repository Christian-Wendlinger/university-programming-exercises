package utils;

import java.math.BigInteger;

public class Karatsuba {
    /**
     * Multiply two numbers.
     *
     * @param x first number
     * @param y second number
     * @return x * y
     */
    public static long calculate(long x, long y) {
        return karatsuba(BigInteger.valueOf(x), BigInteger.valueOf(y)).longValue();
    }

    /**
     * Karatsuba algorithm to multiplay x and y.
     *
     * @param x first number
     * @param y second number
     * @return x * y
     */
    private static BigInteger karatsuba(BigInteger x, BigInteger y) {
        // cutoff to brute force
        int N = Math.max(x.bitLength(), y.bitLength());

        // cut at single digit value
        if (N <= 2) {
            Strassen.addOperation();
            return x.multiply(y);
        }

        // number of bits divided by 2, rounded up
        N = (N / 2) + (N % 2);

        // x = a + 2^N b,   y = c + 2^N d
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));

        // compute sub-expressions
        BigInteger ac = karatsuba(a, c);
        BigInteger bd = karatsuba(b, d);
        BigInteger abcd = karatsuba(a.add(b), c.add(d));

        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2 * N));
    }
}