/*
2
1 2
3 4
5 6
7 8

-> 49

4
680 880 555 360
923 438 301 183
623 983 561 770
120 895 484 171
103 686 455 178
881 859 138 365
717 357 958 680
932 498 437 931

-> 1671

4
9354 5309 2711 4390
6915 6979 7650 5430
2659 5167 7145 1683
9772 3556 5477 7518
7029 1396 5755 9844
3420 5645 8404 2437
1908 1643 8360 2040
1340 1963 3691 1374

-> 2711
 */

/**
 * @author Mertion Dzemaili, Christian Wendlinger
 */

import utils.Strassen;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    /**
     * Multiplay two matrices with strassen algorithm and entitites with karatsuba algorithm
     *
     * @param args
     */
    public static void main(String[] args) {
        //read input
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());

        LinkedList<long[][]> matrices = new LinkedList<>();

        for (int i = 0; i < 2; i++) {
            long[][] matrix = new long[n][n];
            for (int j = 0; j < n; j++) {
                String[] numbers = scan.nextLine().split("\\s");
                for (int k = 0; k < n; k++) {
                    matrix[j][k] = Integer.parseInt(numbers[k]);
                }
            }
            matrices.add(matrix);
        }

        // multiply matrices and
        printMatrix(Strassen.calculate(matrices.pop(), matrices.pop()));
        System.out.println(Strassen.getOperations());
    }

    /**
     * Print a matrix to the console.
     *
     * @param matrix
     */
    private static void printMatrix(long[][] matrix) {
        for (long[] ints : matrix) {
            for (long j : ints) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
