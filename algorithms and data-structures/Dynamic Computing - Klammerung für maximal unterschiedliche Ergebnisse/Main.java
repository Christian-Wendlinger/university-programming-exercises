/**
 * @author: Meriton Djemaili, Christian Wendlinger
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //read input
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        String[] numbers = input.split("[*+]");
        String[] operators = input.replaceFirst("\\d", "").split("\\d");

        //find Minimum value
        String[][] table = new String[numbers.length][numbers.length];

        //initialize diagonal
        for (int i = 0; i < table.length; i++) {
            table[i][i] = numbers[i];
        }

        //initialize operators
        for (int i = 0; i < table.length - 1; i++) {
            table[i + 1][i] = operators[i];
        }

        //fill the rest of the table
        for (int i = 0; i < table.length - 1; i++) {
            for (int j = 0; j < table.length - 1 - i; j++) {

                //get all relevant numbers and operators
                List<String> operatorList = new LinkedList<>();

                List<String> numbersLeft = new LinkedList<>();
                for (int k = 0; k < table.length; k++) {
                    if (j + i - k < 0) {
                        break;
                    } else if (table[j][j + i - k].matches("[*+]")) {
                        break;
                    } else {
                        numbersLeft.add(table[j][j + i - k]);

                        //find operator for number
                        for (int l = 1; l < table.length; l++) {
                            if (table[j + l][j + i - k].matches("[*+]")) {
                                operatorList.add(table[j + l][j + i - k]);
                                break;
                            }
                        }
                    }
                }

                List<String> numbersRight = new LinkedList<>();
                for (int k = 0; k < table.length; k++) {
                    if (j + 1 + k > table.length - 1) {
                        break;
                    } else if (table[j + 1 + k][j + i + 1].matches("[*+]")) {
                        break;
                    } else {
                        numbersRight.add(table[j + 1 + k][j + i + 1]);
                    }
                }

                //find min value
                table[j][j + i + 1] = calculateMinimun(operatorList, numbersLeft, numbersRight);
            }
        }

        //find maximum value
        String[][] table2 = new String[numbers.length][numbers.length];

        //initialize diagonal
        for (int i = 0; i < table2.length; i++) {
            table2[i][i] = numbers[i];
        }

        //initialize operators
        for (int i = 0; i < table2.length - 1; i++) {
            table2[i + 1][i] = operators[i];
        }

        //fill the rest of the table
        for (int i = 0; i < table2.length - 1; i++) {
            for (int j = 0; j < table2.length - 1 - i; j++) {

                //get all relevant numbers and operators
                List<String> operatorList = new LinkedList<>();

                List<String> numbersLeft = new LinkedList<>();
                for (int k = 0; k < table2.length; k++) {
                    if (j + i - k < 0) {
                        break;
                    } else if (table2[j][j + i - k].matches("[*+]")) {
                        break;
                    } else {
                        numbersLeft.add(table2[j][j + i - k]);

                        //find operator for number
                        for (int l = 1; l < table2.length; l++) {
                            if (table2[j + l][j + i - k].matches("[*+]")) {
                                operatorList.add(table2[j + l][j + i - k]);
                                break;
                            }
                        }
                    }
                }

                List<String> numbersRight = new LinkedList<>();
                for (int k = 0; k < table2.length; k++) {
                    if (j + 1 + k > table2.length - 1) {
                        break;
                    } else if (table2[j + 1 + k][j + i + 1].matches("[*+]")) {
                        break;
                    } else {
                        numbersRight.add(table2[j + 1 + k][j + i + 1]);
                    }
                }

                //find min value
                table2[j][j + i + 1] = calculateMaximum(operatorList, numbersLeft, numbersRight);
            }
        }
        System.out.println(table2[0][table2.length - 1] + ", " + table[0][table.length - 1]);
    }

    //find minimum through calculating possibilities
    private static String calculateMinimun(List<String> operatorList, List<String> numbersLeft, List<String> numbersRight) {
        int min = Integer.MAX_VALUE; // "infinity"

        //should not happen - lists must have same size
        if (numbersLeft.size() != numbersRight.size()) {
            throw new IllegalStateException();
        }

        //find minimum
        for (int i = 0; i < numbersLeft.size(); i++) {
            int tmp;

            if (operatorList.get(i).equals("+")) {
                tmp = Integer.parseInt(numbersLeft.get(i)) + Integer.parseInt(numbersRight.get(numbersRight.size() - 1 - i));
            } else if (operatorList.get(i).equals("*")) {
                tmp = Integer.parseInt(numbersLeft.get(i)) * Integer.parseInt(numbersRight.get(numbersRight.size() - 1 - i));
            }
            //should not happen - illegal operator
            else {
                throw new IllegalStateException();
            }

            if (tmp < min) {
                min = tmp;
            }
        }

        return Integer.toString(min);
    }

    //find maximum through calculating possibilities
    private static String calculateMaximum(List<String> operatorList, List<String> numbersLeft, List<String> numbersRight) {
        int max = Integer.MIN_VALUE; // "infinity"

        //should not happen - lists must have same size
        if (numbersLeft.size() != numbersRight.size()) {
            throw new IllegalStateException();
        }

        //find minimum
        for (int i = 0; i < numbersLeft.size(); i++) {
            int tmp;

            if (operatorList.get(i).equals("+")) {
                tmp = Integer.parseInt(numbersLeft.get(i)) + Integer.parseInt(numbersRight.get(numbersRight.size() - 1 - i));
            } else if (operatorList.get(i).equals("*")) {
                tmp = Integer.parseInt(numbersLeft.get(i)) * Integer.parseInt(numbersRight.get(numbersRight.size() - 1 - i));
            }
            //should not happen - illegal operator
            else {
                throw new IllegalStateException();
            }

            if (tmp > max) {
                max = tmp;
            }
        }

        return Integer.toString(max);
    }
}
