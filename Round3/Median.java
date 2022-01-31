import java.util.ArrayList;
import java.util.Collections;

public class Median {

    public static void main(String[] args) {
        // var u = new String[]{"2.05", "1.65", "-1.73", "-3.13", "-0.5", "1"};
        // Testausta varten for-loopiin voi laittaa args tilalle u.
        ArrayList<Double> list = new ArrayList<>();
        for (var i : args) {
            double d = Double.parseDouble(i);
            list.add(d);
        }
        if (!list.isEmpty()) {
            Collections.sort(list);
            double median;
            if (list.size() % 2 == 0) {
                median = (list.get(list.size()/2-1)+list.get(list.size()/2))/2;
            } else {
                median = (list.get(list.size()/2-1/2));
            }
            System.out.println("Median: " + median);
        }
    }
}
