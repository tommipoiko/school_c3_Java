import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.TreeMap;

public class Currencies {

    public static void main(String args[]) throws IOException {
        
        TreeMap<String, Double> exchange = new TreeMap<>();
        
        BufferedReader command = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            
            System.out.print("Enter command: ");
            String cmd = command.readLine();
            System.out.format("%s\n", cmd);
            
            String[] cmd_parts = cmd.split("\\s+");
            
            if ("quit".equalsIgnoreCase(cmd_parts[0])) {
                System.out.println("Quit-command received, exiting...");
                break;
                
            } else if ("rate".equalsIgnoreCase(cmd_parts[0])) {
                if (cmd_parts.length != 3) {
                    System.out.println("Unknown or illegal command!");
                    continue;
                } else if (cmd_parts[1].length() != 3) {
                    System.out.println("Unknown or illegal command!");
                    continue;
                }
                
                exchange.put(cmd_parts[1].toUpperCase(), Double.parseDouble(cmd_parts[2]));
                System.out.format("Stored the rate 1 EUR = %.3f %s\n", Double.parseDouble(cmd_parts[2]), cmd_parts[1].toUpperCase());
                
            } else if ("convert".equalsIgnoreCase(cmd_parts[0])) {
                if (cmd_parts.length != 3) {
                    System.out.println("Unknown or illegal command!");
                    continue;
                }
                
                if (exchange.containsKey(cmd_parts[2].toUpperCase())) {
                    double changed = Double.parseDouble(cmd_parts[1])/exchange.get(cmd_parts[2].toUpperCase());
                    System.out.format("%.3f %s =", Double.parseDouble(cmd_parts[1]), cmd_parts[2].toUpperCase());
                    System.out.format(" %.3f EUR\n", changed);
                } else {
                    System.out.format("No rate for %s has been stored!\n", cmd_parts[2].toUpperCase());
                }
                
            } else if ("rates".equalsIgnoreCase(cmd_parts[0])) {
                System.out.println("Stored euro rates:");
                for (var currency : exchange.keySet()) {
                    System.out.format("  %s %.3f\n", currency, exchange.get(currency));
                }
                
            } else {
                System.out.println("Unknown or illegal command!");
            }
        }
    }
}
