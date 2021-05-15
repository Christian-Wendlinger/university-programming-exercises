package utils;

public class Strassen {

    // count internal operations
    private static int operations = 0;

    // getter
    public static int getOperations() {
        return operations;
    }

    // add internal operation
    public static void addOperation() {
        operations++;
    }

    /**
     * Reset operation counter and multiply two matrices (Strassen algorithm).
     *
     * @param A first matrix
     * @param B second matrix
     * @return A * B (matrix multiplication)
     */
    public static long[][] calculate(long[][] A, long[][] B) {
        reset();
        return multiply(A, B);
    }

    /**
     * Internal function to multiply two matrices (Strassen).
     *
     * @param A first matrix
     * @param B second matrix
     * @return A * B (matrix)
     */
    private static long[][] multiply(long[][] A, long[][] B) {
        // initialize variables
        int n = A.length;
        long[][] result = new long[n][n];

        // if row count is odd, fill with zeroes
        if ((n % 2 != 0) && (n != 1)) {
            long[][] a1, b1, c1;
            int n1 = n + 1;
            a1 = new long[n1][n1];
            b1 = new long[n1][n1];

            // copy matrix
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    a1[i][j] = A[i][j];
                    b1[i][j] = B[i][j];
                }

            // use the algorithm on new matrix
            c1 = multiply(a1, b1);
            for (int i = 0; i < n; i++)
                System.arraycopy(c1[i], 0, result[i], 0, n);

            // return calculated matrix without the additional zeroes
            return result;
        }

        // multiply numbers with karatsuba algorithm
        if (n == 1) {
            result[0][0] = Karatsuba.calculate(A[0][0], B[0][0]);
        } else {

            // create new sub-matrices for A
            long[][] A11 = new long[n / 2][n / 2];
            long[][] A12 = new long[n / 2][n / 2];
            long[][] A21 = new long[n / 2][n / 2];
            long[][] A22 = new long[n / 2][n / 2];

            // create new sub-matrices for B
            long[][] B11 = new long[n / 2][n / 2];
            long[][] B12 = new long[n / 2][n / 2];
            long[][] B21 = new long[n / 2][n / 2];
            long[][] B22 = new long[n / 2][n / 2];

            // fill the sub-matrices
            divide(A, A11, 0, 0);
            divide(A, A12, 0, n / 2);
            divide(A, A21, n / 2, 0);
            divide(A, A22, n / 2, n / 2);

            divide(B, B11, 0, 0);
            divide(B, B12, 0, n / 2);
            divide(B, B21, n / 2, 0);
            divide(B, B22, n / 2, n / 2);

            // formulas
            long[][] P1 = multiply(add(A11, A22), add(B11, B22));
            long[][] P2 = multiply(add(A21, A22), B11);
            long[][] P3 = multiply(A11, subtract(B12, B22));
            long[][] P4 = multiply(A22, subtract(B21, B11));
            long[][] P5 = multiply(add(A11, A12), B22);
            long[][] P6 = multiply(subtract(A21, A11), add(B11, B12));
            long[][] P7 = multiply(subtract(A12, A22), add(B21, B22));

            long[][] C11 = add(subtract(add(P1, P4), P5), P7);
            long[][] C12 = add(P3, P5);
            long[][] C21 = add(P2, P4);
            long[][] C22 = add(subtract(add(P1, P3), P2), P6);

            // fill the the large matrix with the sub-matrices values
            insert(C11, result, 0, 0);
            insert(C12, result, 0, n / 2);
            insert(C21, result, n / 2, 0);
            insert(C22, result, n / 2, n / 2);
        }
        return result;
    }

    /**
     * Add two matrices by adding each entity.
     *
     * @param A first matrix
     * @param B second matrix
     * @return A + B (matrix)
     */
    private static long[][] add(long[][] A, long[][] B) {
        int n = A.length;

        long[][] result = new long[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
                operations++;
            }
        return result;
    }

    /**
     * Subtract two matrices by subtracting each entity.
     *
     * @param A first matrix
     * @param B second matrix
     * @return A - B (matrix)
     */
    private static long[][] subtract(long[][] A, long[][] B) {
        int n = A.length;

        long[][] result = new long[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
                operations++;
            }
        return result;
    }

    /**
     * Divide a matrix into a smaller sub-matrix.
     *
     * @param parent larger parent matrix
     * @param child  smaller child matrix
     * @param iB     starting row
     * @param jB     starting column
     */
    private static void divide(long[][] parent, long[][] child, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < child.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < child.length; j1++, j2++) {
                child[i1][j1] = parent[i2][j2];
            }
    }

    /**
     * Insert values of a smaller matrix into a larger matrix.
     *
     * @param child  smaller matrix
     * @param parent larger matrix
     * @param iB     starting row
     * @param jB     starting column
     */
    private static void insert(long[][] child, long[][] parent, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < child.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < child.length; j1++, j2++) {
                parent[i2][j2] = child[i1][j1];
            }
    }

    /**
     * Reset the operation counter;
     */
    private static void reset() {
        operations = 0;
    }
}
