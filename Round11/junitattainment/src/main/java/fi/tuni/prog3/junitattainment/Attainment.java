package fi.tuni.prog3.junitattainment;

import java.util.Comparator;

public class Attainment implements Comparable<Attainment> {
    
    // Private
    private final String courseCode;
    private final String studentNumber;
    private final int grade;
    
    // Public
    public Attainment(String courseCode, String studentNumber, int grade) 
                                            throws IllegalArgumentException {
        if (grade < 0 || grade > 5) {
            throw new IllegalArgumentException();
        } else if (courseCode == null || studentNumber == null) {
            throw new IllegalArgumentException();
        } else {
            this.courseCode = courseCode;
            this.studentNumber = studentNumber;
            this.grade = grade;
        }
    }
    
    public String getCourseCode() {
        return this.courseCode;
    }
    
    public String getStudentNumber() {
        return this.studentNumber;
    }
    
    public int getGrade() {
        return this.grade;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s %d", courseCode, studentNumber, grade);
    }
    
    @Override
    public int compareTo(Attainment att) {
        int cmp = this.studentNumber.compareTo(att.studentNumber);
        if (cmp == 0) {
            cmp = this.courseCode.compareTo(att.courseCode);
        }
        return cmp;
    }
    
    public static final Comparator<Attainment> CODE_STUDENT_CMP = (Attainment a, Attainment b) -> {
        int cmp = a.courseCode.compareTo(b.courseCode);
        if (cmp == 0) {
            cmp = a.studentNumber.compareTo(b.studentNumber);
        }
        return cmp;
    };
    
    public static final Comparator<Attainment> CODE_GRADE_CMP = (Attainment a, Attainment b) -> {
        int cmp = a.courseCode.compareTo(b.courseCode);
        if (cmp == 0) {
            cmp = Integer.compare(a.grade, b.grade) * -1;
        }
        return cmp;
    };
}
