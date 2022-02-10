// import here

public class Date {

    // Private
    private int day_;
    private int month_;
    private int year_;
    
    // Public
    public Date(int year, int month, int day) throws DateException {
        
        if (testDate(year, month, day)) {
            day_ = day;
            month_ = month;
            year_ = year;
        } else {
            throw new DateException(String.format("Illegal date %d.%d.%d", day, month, year));
        }
    }
    
    public int getYear() {
        
        return this.year_;
    }
    
    public int getMonth() {
        
        return this.month_;
    }
    
    public int getDay() {
        
        return this.day_;
    }
    
    public String toString() {
        
        String fullDate = "";
        
        if (this.day_ < 10) {
            fullDate += "0" + this.day_;
        } else {
            fullDate += this.day_;
        }
        
        fullDate += ".";
        
        if (this.month_ < 10) {
            fullDate += "0" + this.month_;
        } else {
            fullDate += this.month_;
        }
        
        fullDate += ".";
        
        fullDate += this.year_;
        
        return fullDate;
    }
    
    static boolean isLeapYear(int year) {
        
        return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    }

    static private int[][] mDays = {{31, 31}, {28, 29}, {31, 31}, {30, 30}, {31, 31}, {30, 30},
                            {31, 31}, {31, 31}, {30, 30}, {31, 31}, {30, 30}, {31, 31}};

    static int monthDays(int month, int year) {
        int days = -1;
        if(1 <= month && month <= 12) {
            
            days = isLeapYear(year) ? mDays[month-1][1] : mDays[month-1][0];
        }
        return days;
    }

    static boolean isLegalDate(int day, int month, int year) {
        
        return (1 <= day) && (day <= monthDays(month, year));
    }

    static boolean testDate(int year, int month, int day) {
        
        boolean dateOk = false;
        dateOk = isLegalDate(day, month, year);
        
        return dateOk;
    }
}
