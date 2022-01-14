/**
 *
 * @author tommipoiko
 */

public class Mean {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        var list = new String[]{"1", "2", "3", "4", "5"};
        double sum = 0;
        int counter = 0;
        for (var i : list) {
            double d = Double.parseDouble(i);
            sum += d;
            ++counter;
        }
        double mean = sum / counter;
        System.out.println("Mean: " + mean);
    }
}
