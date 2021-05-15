/**
 * @author Meriton Djemaili, Chiristian Wendlinger
 */

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //read input
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        //parse input into array
        String[] numbersAsStrings = input.split(",");
        int[] numbers = new int[numbersAsStrings.length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numbersAsStrings[i]);
        }

        //Build Heaps
        //minHeap for Sellers
        PriorityQueue<Integer> sellHeap = new PriorityQueue<>();

        //maxHeap for Buyers
        PriorityQueue<Integer> buyHeap = new PriorityQueue<>((Integer i1, Integer i2) -> (-Integer.compare(i1, i2)));

        int counter = 1;

        //add numbers to heaps
        for (int number : numbers) {
            if (number < 0) {
                sellHeap.add(Math.abs(number));
            } else {
                buyHeap.add(number);
            }

            //trade takes place -> print info
            if (buyHeap.peek() != null && sellHeap.peek() != null) {
                if (buyHeap.peek() >= sellHeap.peek()) {
                    System.out.println("Handel in Schritt " + counter + ": (" + buyHeap.poll() + ",-" + sellHeap.poll() + ")");
                }
            }

            //output
            counter++;
        }
    }
}
