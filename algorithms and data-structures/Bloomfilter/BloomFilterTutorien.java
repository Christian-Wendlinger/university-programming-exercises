/*
Anzahl Hashfunktionen: 2
m11 = 19
m12 = 17
m21 = 23
m22 = 17

Anzahl Tutorien: 4
Anzahl Tutorien zu bestehen: 2

Tutorium 1: 5048,8248,6909
Tutorium 2: 5048,6119,8248
Tutorium 3: 5048,1613,6715
Tutorium 4: 5048,8248,6909
 */

/**
 * @author: Meriton Djemaili, Christian Wendlinger
 * @Firas: oben als Kommentar eine Beispieleingabe
 */


import java.util.*;
import java.util.function.ToIntFunction;

public class BloomFilterTutorien {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Integer> m1i = new LinkedList<>();
        List<Integer> mi2 = new LinkedList<>();
        int c = 0;
        //parse input
        //hash functions
        int loopc = 2 * Integer.parseInt(scan.nextLine().split(": ")[1]);
        for (int i = 0; i < loopc; i++) {
            if (c % 2 == 0) {
                m1i.add(Integer.parseInt(scan.nextLine().split("= ")[1]));
            } else {
                mi2.add(Integer.parseInt(scan.nextLine().split("= ")[1]));
            }
            c++;
        }

        byte[] bloomfilter = new byte[mi2.stream().max(Comparator.comparing(Integer::valueOf)).get()];

        //empty line
        scan.nextLine();

        //tutoriuminfo
        int n = Integer.parseInt(scan.nextLine().split(": ")[1]);
        int z = Integer.parseInt(scan.nextLine().split(": ")[1]);

        //empty line
        scan.nextLine();

        List<String> matrnumbers = new LinkedList<>();
        //each tutorium
        for (int i = 0; i < n; i++) {
            String[] matrikelnums = scan.nextLine().split(": ")[1].split(",");

            for (String matrnum : matrikelnums) {
                hash(Integer.parseInt(matrnum), m1i, mi2, bloomfilter);
                matrnumbers.add(matrnum);
            }
        }
        scan.close();

        //output
        System.out.println("\nBestanden:\n" + checkNumbers(matrnumbers, bloomfilter, m1i, mi2, z));
    }

    //hash functions
    private static void hash(int matrnum, List<Integer> m1i, List<Integer> mi2, byte[] bloomfilter) {
        for (int i = 0; i < m1i.size(); i++) {
            bloomfilter[(matrnum % m1i.get(i)) % mi2.get(i)] += 1;
        }
    }

    //check all the students with the filter
    private static String checkNumbers(List<String> matrnumbers, byte[] bloomfilter, List<Integer> m1i, List<Integer> mi2, int z) {
        List<String> passed = new LinkedList<>();

        for (String matrnum : matrnumbers) {
            boolean pass = true;

            //check values through hashing all student ids
            for (int i = 0; i < m1i.size(); i++) {
                if (bloomfilter[(Integer.parseInt(matrnum) % m1i.get(i)) % mi2.get(i)] < z) {
                    pass = false;
                }
            }

            //found a Student that passed (false positive possible)
            if (pass && !passed.contains(matrnum)) {
                passed.add(matrnum);
            }
        }

        return passed.toString();
    }
}
