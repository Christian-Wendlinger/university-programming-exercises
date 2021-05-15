import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 * @author Meriton Djemaili, Christian Wendlinger
 *
 * @info für Firas:
 * - das Programm toleriert keine Abweichungen bei dem Format der Eingabe, daher hier eine Beispieleingabe, dessen Länge
 *   und Werte beliebig angepasst werden können:
 */
/*
n = 4, k = 3
Gruppe 1:
id = 0, score = 1
id = 1, score = 5
id = 2, score = 10
id = 3, score = 2

Gruppe 2:
id = 4, score = 7
id = 5, score = 8
id = 6, score = 4
id = 7, score = 9

Gruppe 3:
id = 8, score = 6
id = 9, score = 3
id = 10, score = 12
id = 11, score = 11


 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //get n,k from input
        String[] firstLine = scan.nextLine().split(", ");
        int n = Integer.parseInt(firstLine[0].split("= ")[1]);
        int k = Integer.parseInt(firstLine[1].split("= ")[1]);

        //read Groups
        List<List<int[]>> groups = new LinkedList<>();

        //loop over number of groups
        for (int i = 0; i < k; i++) {

            //skip group name
            scan.nextLine();

            List<int[]> grp = new LinkedList<>();

            //loop over number of elements
            for (int j = 0; j < n; j++) {
                String[] info = scan.nextLine().split(", ");
                int[] values = new int[2];

                //parse id = [0], score = [1]
                values[0] = Integer.parseInt(info[0].split("= ")[1]);
                values[1] = Integer.parseInt(info[1].split("= ")[1]);

                grp.add(values);
            }
            groups.add(grp);
            //skip empty line
            scan.nextLine();
        }

        //sort each group (Bubble-Sort) - O(n²)
        for (List<int[]> grp : groups) {
            groupSort(grp);
        }

        //print each Group
        printGroups(groups, 0);

        //get combined sorted group ("Merge"-Sort) - O(n)
        sortCombinedGroups(groups);
        printGroups(groups, 1);
    }


    private static void groupSort(List<int[]> grp) {
        //iterate over all numbers
        for (int i = 0; i < grp.size(); i++) {
            boolean sorted = true;

            //iterate over all possible changes
            for (int j = 1; j < grp.size() - i; j++) {
                int[] v1 = grp.get(j);
                int[] v2 = grp.get(j - 1);

                //switch numbers
                if (v1[1] < v2[1]) {
                    int[] tmp = v1;
                    grp.set(j, v2);
                    grp.set(j - 1, v1);
                    sorted = false;
                }
            }

            if (sorted) break;
        }
    }

    //create output
    private static void printGroups(List<List<int[]>> groups, int m) {

        //"group" - mode
        if (m == 0) {
            int c = 1;

            //iterate over all groups
            for (List<int[]> group : groups) {

                //print group number
                System.out.println("\nGruppe " + c + ":");

                //print values backwards
                printGroups(group);
                c++;
            }
        }
        //combined mode => groups.size() == 1
        else {
            System.out.println("\nGesamt:");
            printGroups(groups.get(0));
        }
    }

    private static void printGroups(List<int[]> group) {
        for (int i = 0; i < group.size(); i++) {
            System.out.println("id = " + group.get(group.size() - 1 - i)[0] + ", score = " + group.get(group.size() - 1 - i)[1]);
        }
    }


    private static void sortCombinedGroups(List<List<int[]>> groups) {
        if (!(groups.size() == 1)) {

            //get first 2 groups and remove them from the list
            List<int[]> grp1 = groups.get(0);
            groups.remove(0);
            List<int[]> grp2 = groups.get(0);
            groups.remove(0);

            //combine groups
            List<int[]> cmb = new LinkedList<>();

            //continue until both lists are empty
            while (!(grp1.isEmpty() && grp2.isEmpty())) {
                boolean emp1 = grp1.isEmpty();
                boolean emp2 = grp2.isEmpty();

                //both lists have elements left
                if (!emp1 && !emp2) {
                    int[] tmp1 = grp1.get(0);
                    int[] tmp2 = grp2.get(0);

                    //add the smaller number to sorted list
                    if (tmp1[1] > tmp2[1]) {
                        cmb.add(tmp2);
                        grp2.remove(0);
                    } else {
                        cmb.add(tmp1);
                        grp1.remove(0);
                    }
                }
                //only list2 has elements left
                else if (emp1) {
                    cmb.add(grp2.get(0));
                    grp2.remove(0);
                }
                //only list1 has elements left
                else {
                    cmb.add(grp1.get(0));
                    grp1.remove(0);
                }
            }

            //add combined group to groups
            groups.add(cmb);

            //recursive call
            sortCombinedGroups(groups);
        }
    }
}
