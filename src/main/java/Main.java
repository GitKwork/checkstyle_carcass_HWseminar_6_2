/**
 * 1396. Design Underground System
 * An underground railway system is keeping track of customer travel times between different stations.
 * They are using this data to calculate the average time it takes to travel from one station to another.
 */

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        boolean isFormatted = false;
        String request = "пожалуйста форматируйте код";
//        System.out.println(getAverageTime(String AA, String BB));
    }

    class CheckInInfo {
        private String stationName;
        private int t;

        CheckInInfo(String stationName, int t) {
            this.stationName = stationName;
            this.t = t;
        }
    }

    class CheckOutInfo {
        private int total;
        private int count;

        CheckOutInfo(int total, int count) {
            this.total = total;
            this.count = count;
        }
    }

    private final Map<Integer, CheckInInfo> checkInMap = new HashMap<>();
    private final Map<String, CheckOutInfo> checkOutMap = new HashMap<>();

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInInfo(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckInInfo checkInInfo = checkInMap.get(id);
        String checkOutKey = String.format("%s-%s", checkInInfo.stationName, stationName);
        if (!checkOutMap.containsKey(checkOutKey)) {
            checkOutMap.put(checkOutKey, new CheckOutInfo(t - checkInInfo.t, 1));
        } else {
            checkOutMap.get(checkOutKey).total += t - checkInInfo.t;
            checkOutMap.get(checkOutKey).count += 1;
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        String checkOutKey = String.format("%s-%s", startStation, endStation);
        CheckOutInfo checkOutInfo = checkOutMap.get(checkOutKey);
        return (double) checkOutInfo.total / checkOutInfo.count;
    }
}
