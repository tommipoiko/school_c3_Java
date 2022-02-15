import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

public class Dates {

    // Private
    
    // Public
    public static class DateDiff {
        
        // Private
        private LocalDate startDate;
        private LocalDate endDate;
        private long diff;
        
        private DateDiff(String start, String end) {
            
            String[] startD = {};
            startD = start.split("-");
            startDate = LocalDate.of(Integer.parseInt(startD[0]),
                                     Integer.parseInt(startD[1]),
                                     Integer.parseInt(startD[2]));
            
            String[] endD = {};
            endD = end.split("-");
            endDate = LocalDate.of(Integer.parseInt(endD[0]),
                                   Integer.parseInt(endD[1]),
                                   Integer.parseInt(endD[2]));
            
            diff = startDate.until(endDate, ChronoUnit.DAYS);
        }
        
        // Public
        public String getStart() {
            
            return startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        
        public String getEnd() {
            
            return endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        
        public int getDiff() {
            
            return (int) diff;
        }
        
        @Override
        public String toString() {
            
            String startDW = startDate.getDayOfWeek().toString();
            startDW = startDW.toLowerCase();
            startDW = startDW.substring(0, 1).toUpperCase() + startDW.substring(1);
            String endDW = endDate.getDayOfWeek().toString();
            endDW = endDW.toLowerCase();
            endDW = endDW.substring(0, 1).toUpperCase() + endDW.substring(1);
            
            String startWeek = startDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            String endWeek = endDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            
            String line = startDW + " " + startWeek + " --> ";
            line += endDW + " " + endWeek + ": " + this.getDiff() + " days";
            
            return line;
        }
    }
    
    public static DateDiff[] dateDiffs(String ...dateStrs) throws DateTimeException {
        
        TreeSet<String> list = new TreeSet<>();
        
        for (String date : dateStrs) {
            try {
                String[] duba = {};
                duba = date.split("-");
                
                if (duba.length == 3) {
                    if (duba[1].length() < 2 || duba[2].length() < 2 || duba[0].length() < 3) {
                        throw new DateTimeException(String.format("The date \"%s\" is illegal!\n", date));
                    } else {
                        LocalDate dubaDate = LocalDate.of(Integer.parseInt(duba[0]),
                                                          Integer.parseInt(duba[1]),
                                                          Integer.parseInt(duba[2]));
                        list.add(dubaDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    }
                } else {
                    duba = date.split("\\.");
                    if (duba[2].length() < 4) {
                        throw new DateTimeException(String.format("The date \"%s\" is illegal!\n", date));
                    } else {
                        LocalDate dubaDate = LocalDate.of(Integer.parseInt(duba[2]),
                                                          Integer.parseInt(duba[1]),
                                                          Integer.parseInt(duba[0]));
                        list.add(dubaDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    }
                }
            } catch(DateTimeException e) {
                System.out.format("The date \"%s\" is illegal!\n", date);
            }
        }
        
        int listSize = list.size();
        String[] dates = new String[listSize];
        dates = list.toArray(dates);
        
        DateDiff[] diffList = new DateDiff[listSize-1];
        
        if (listSize > 1) {
            for (int i = 0; i < listSize-1; i++) {
                DateDiff difference = new DateDiff(dates[i], dates[i+1]);
                diffList[i] = difference;
            }
        } else {
            //
        }
        
        return diffList;
    }
}
