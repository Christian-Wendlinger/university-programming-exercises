/*
5
3,2,4,1,7

8
1,2,3,4,5,6,7,8

6
4,7,12,8,3,9
 */

/**
 * @author Meriton Dzemaili, Christian Wendlinger
 */

import utils.FFT;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // read coefficients
        int n = Integer.parseInt(scan.nextLine());
        String[] values = scan.nextLine().split(",");

        double[] real = new double[n];
        double[] img = new double[n];

        // insert values
        for (int i = 0; i < n; i++) {
            real[i] = Double.parseDouble(values[i]);
        }

        // transform
        FFT.transform(real, img);

        // print values
        print(real, img);
    }

    private static void print(double[] real, double[] img) {
        for (int i = 0; i < real.length - 1; i++) {
            System.out.print(processRealNumber(real[i]) + processImaginaryNumber(img[i]) + ",");
        }
        System.out.print(processRealNumber(real[real.length - 1]) + processImaginaryNumber(img[img.length - 1]));
    }

    // format the real part
    private static String processRealNumber(double number) {
        if (number % 1 == 0) {
            return String.valueOf((int) number);
        }
        // not an integer
        else {
            String[] numberParts = String.valueOf(number).split("\\.");
            // more than 3 values after comma
            if (numberParts[1].length() > 3) {
                numberParts[1] = numberParts[1].substring(0, 3);
            }
            return String.join(".", numberParts);
        }
    }

    //format the imaginary part
    private static String processImaginaryNumber(double number) {
        if (number % 1 == 0) {
            String sign = number >= 0 ? "+" : "";
            return sign + (int) number + "i";
        }
        // not an integer
        else {
            String[] numberParts = String.valueOf(number).split("\\.");
            // more than 3 values after comma
            if (numberParts[1].length() > 3) {
                numberParts[1] = numberParts[1].substring(0, 3);
            }
            String sign = number >= 0 ? "+" : "";

            return sign + String.join(".", numberParts) + "i";
        }
    }
}
