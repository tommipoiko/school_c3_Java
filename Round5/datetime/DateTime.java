// import here

public class DateTime extends Date {
    
    // Private
    private int hour_;
    private int minute_;
    private int second_;
    
    // Public
    public DateTime(int year, int month, int day, int hour, int minute, int second) throws DateException {
        super(year, month, day);
        if (hour >= 0 && hour < 24) {
            if (minute >= 0 && minute < 60) {
                if (second >= 0 && second < 60) {
                    
                    hour_ = hour;
                    minute_ = minute;
                    second_ = second;
                } else {
                    throw new DateException(String.format("Illegal time %d:%d:%d", hour, minute, second));
                }
            } else {
                throw new DateException(String.format("Illegal time %d:%d:%d", hour, minute, second));
            }
        } else {
            throw new DateException(String.format("Illegal time %d:%d:%d", hour, minute, second));
        }
    }
    
    public int getHour() {
        
        return this.hour_;
    }
    
    public int getMinute() {
        
        return this.minute_;
    }
    
    public int getSecond() {
        
        return this.second_;
    }
    
    @Override
    public String toString() {
        
        String fullDate = super.toString() + " ";
        
        if (this.hour_ < 10) {
            fullDate += "0" + this.hour_;
        } else {
            fullDate += this.hour_;
        }
        
        fullDate += ":";
        
        if (this.minute_ < 10) {
            fullDate += "0" + this.minute_;
        } else {
            fullDate += this.minute_;
        }
        
        fullDate += ":";
        
        if (this.second_ < 10) {
            fullDate += "0" + this.second_;
        } else {
            fullDate += this.second_;
        }
        
        return fullDate;
    }
}
