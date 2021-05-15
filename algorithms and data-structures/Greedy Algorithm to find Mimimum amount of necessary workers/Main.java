/*
7
09:00 18:00
18:01 00:00
00:01 08:59
08:00 14:00
14:00 20:00
19:55 02:00
02:00 08:00

=> 4

5
10:00 16:00
16:00 22:00
22:00 04:00
15:00 05:00
04:00 10:00

=> 3 passt
 */

/**
 * @author Meriton Djemaili, Christian Wendlinger
 */


import java.time.LocalTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //get and parse input
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());

        LinkedList<Application> applications = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String[] inputParts = scan.nextLine().split("\\s");
            applications.add(new Application(inputParts[0], inputParts[1]));
        }

        scan.close();
        System.out.println(minApplications(applications));
    }

    /**
     * @param applications
     * @return smallest number of applications to satisfy the job
     */
    private static int minApplications(List<Application> applications) {

        LinkedList<LinkedList<Application>> possibilities = new LinkedList<>();

        for (int i = 0; i < applications.size(); i++) {
            //clone list
            LinkedList<Application> applicationsCopy = new LinkedList<>(applications);

            //iterate over all Applications
            Application first = applicationsCopy.get(i);
            applicationsCopy.remove(first);

            //temporary storage
            LinkedList<Application> possibility = new LinkedList<>();
            possibility.add(first);

            //look for number of Applications via greedy algorithm
            int workedHours = first.getEndTime().getHour() >= first.getStartTime().getHour() ? first.getEndTime().getHour() - first.getStartTime().getHour() : 24 - (first.getStartTime().getHour() - first.getEndTime().getHour());
            int workedMinutes = first.getEndTime().getMinute() >= first.getStartTime().getMinute() ? first.getEndTime().getMinute() - first.getStartTime().getMinute() : 60 - (first.getStartTime().getMinute() - first.getEndTime().getMinute());

            if (first.getStartTime().getMinute() > first.getEndTime().getMinute()) {
                workedHours--;
            }

            while (workedHours < 24 && applicationsCopy.size() != 0) {
                Application next = findNext(applicationsCopy, possibility.getLast().getEndTime());
                if (next != null) {
                    workedHours += next.getEndTime().getHour() >= possibility.getLast().getEndTime().getHour() ? next.getEndTime().getHour() - possibility.getLast().getEndTime().getHour() : 24 - (possibility.getLast().getEndTime().getHour() - next.getEndTime().getHour());
                    workedMinutes += next.getEndTime().getMinute() >= possibility.getLast().getEndTime().getMinute() ? next.getEndTime().getMinute() - possibility.getLast().getEndTime().getMinute() : 60 - (possibility.getLast().getEndTime().getMinute() - next.getEndTime().getMinute());

                    if (workedMinutes >= 60) {
                        workedMinutes -= 60;
                        workedHours++;
                    }

                    if (possibility.getLast().getEndTime().getMinute() > next.getEndTime().getMinute()) {
                        workedHours--;
                    }

                    possibility.add(next);
                }
                //bad path found
                else {
                    possibility.add(new Application("00:00", "00:00"));
                    break;
                }
            }

            //add possible path
            if (!possibility.contains(new Application("0:0", "0:0"))) {
                possibilities.add(possibility);
                //uncomment to show possible paths:
                // System.out.println(possibility);
            }
        }

        //sort List, to findest shortest Path
        possibilities.sort(new Comparator<LinkedList<Application>>() {
            @Override
            public int compare(LinkedList<Application> o1, LinkedList<Application> o2) {
                return Integer.compare(o1.size(), o2.size());
            }
        });

        //return length of shortest path
        return possibilities.getFirst().size();
    }

    /**
     * @param possibilites
     * @param endTime
     * @return application with closest start time that is before the last applications end time
     */
    //find the next best suited Application i.e. the next application that starts right when the Last ends or a little sooner
    private static Application findNext(LinkedList<Application> possibilites, LocalTime endTime) {
        Application best = null;
        int diff = Integer.MAX_VALUE;

        for (int i = 0; i < possibilites.size(); i++) {
            Application tmp = possibilites.get(i);
            int tmpDiff = Integer.MAX_VALUE;

            //case 1: start and end time on the same day
            if ((tmp.getStartTime().isBefore(endTime) || tmp.getStartTime().equals(endTime)) && tmp.getEndTime().isAfter(endTime)) {
                tmpDiff = (endTime.getHour() - tmp.getStartTime().getHour()) * 60;
            }
            //case 2: start on the day before and end on same day
            else if ((tmp.getStartTime().isAfter(endTime) || tmp.getStartTime().equals(endTime)) && tmp.getEndTime().isAfter(endTime) && tmp.getStartTime().isAfter(tmp.getEndTime())) {
                tmpDiff = 24 - (tmp.getStartTime().getHour() - endTime.getHour());
            }
            //case 3: start on same Day and end on next day
            else if ((tmp.getStartTime().isBefore(endTime) || tmp.getStartTime().equals(endTime)) && tmp.getEndTime().isBefore(endTime) && tmp.getEndTime().isBefore(tmp.getStartTime())) {
                tmpDiff = (endTime.getHour() - tmp.getStartTime().getHour()) * 60;
            }

            tmpDiff += endTime.getMinute() >= tmp.getStartTime().getMinute() ? endTime.getMinute() - tmp.getStartTime().getMinute() : 60 - (tmp.getStartTime().getMinute() - endTime.getMinute());


            //compare to currently best
            if (tmpDiff >= 0 && tmpDiff < diff) {
                best = tmp;
                diff = tmpDiff;
            }
        }

        return best;
    }
}
