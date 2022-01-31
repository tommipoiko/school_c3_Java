import java.util.TreeSet;
import java.util.HashMap;

public class Parameters {

    public static void main(String[] args){
        TreeSet<String> names = new TreeSet<>();
        for (var i : args) {
            names.add(i);
        }
        
        int size = names.size();
        String sizestring = "" + size;
        int row_space = sizestring.length();
        
        int name_space = 0;
        for (var word : names) {
            if (word.length() > name_space) {
                name_space = word.length();
            }
        }
        
        int rowctr = 1;
        HashMap<Integer, String> row_and_name = new HashMap<>();
        for (var name : names) {
            row_and_name.put(rowctr, name);
            rowctr++;
        }
        
        if (row_space == 1) {
            if (!row_and_name.isEmpty()) {
                for (int i = 0; i < name_space + row_space + 7; i++) {
                        System.out.print("#");
                }
            }
            System.out.println();
            for (var combo : row_and_name.entrySet()) {
                System.out.format("#%2d | %s", combo.getKey(), combo.getValue());
                for (int i = 0; i < name_space - combo.getValue().length(); i++) {
                    System.out.print(" ");
                }
                System.out.print(" #\n");
                
                if (combo.getKey() != rowctr - 1) {
                    System.out.print("#");
                    for (int i = 0; i < row_space + 2; i++) {
                        System.out.print("-");
                    }
                    System.out.print("+");
                    for (int i = 0; i < name_space + 2; i++) {
                        System.out.print("-");
                    }
                    System.out.println("#");
                } else {
                    for (int i = 0; i < name_space + row_space + 7; i++) {
                    System.out.print("#");
                    }
                }
            }
        } else if (row_space == 2) {
            if (!row_and_name.isEmpty()) {
                for (int i = 0; i < name_space + row_space + 7; i++) {
                        System.out.print("#");
                }
            }
            System.out.println();
            for (var combo : row_and_name.entrySet()) {
                System.out.format("#%3d | %s", combo.getKey(), combo.getValue());
                for (int i = 0; i < name_space - combo.getValue().length(); i++) {
                    System.out.print(" ");
                }
                System.out.print(" #\n");
                
                if (combo.getKey() != rowctr - 1) {
                    System.out.print("#");
                    for (int i = 0; i < row_space + 2; i++) {
                        System.out.print("-");
                    }
                    System.out.print("+");
                    for (int i = 0; i < name_space + 2; i++) {
                        System.out.print("-");
                    }
                    System.out.println("#");
                } else {
                    for (int i = 0; i < name_space + row_space + 7; i++) {
                    System.out.print("#");
                    }
                }
            }
        } else {
            if (!row_and_name.isEmpty()) {
                for (int i = 0; i < name_space + row_space + 7; i++) {
                        System.out.print("#");
                }
            }
            System.out.println();
            for (var combo : row_and_name.entrySet()) {
                System.out.format("#%4d | %s", combo.getKey(), combo.getValue());
                for (int i = 0; i < name_space - combo.getValue().length(); i++) {
                    System.out.print(" ");
                }
                System.out.print(" #\n");
                
                if (combo.getKey() != rowctr - 1) {
                    System.out.print("#");
                    for (int i = 0; i < row_space + 2; i++) {
                        System.out.print("-");
                    }
                    System.out.print("+");
                    for (int i = 0; i < name_space + 2; i++) {
                        System.out.print("-");
                    }
                    System.out.println("#");
                } else {
                    for (int i = 0; i < name_space + row_space + 7; i++) {
                    System.out.print("#");
                    }
                }
            }
        }
    }
}
