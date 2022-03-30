package fi.tuni.prog3.junitattainment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AttainmentTest {

    public AttainmentTest() {
    }

    @Test
    public void testConstructor() {
        System.out.println("Constructor");
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                ()-> new Attainment("data.db.100", null, 7),
                "This was wrong"
        );
    }

    @Test
    public void testGetCourseCode() {
        System.out.println("getCourseCode");
        Attainment instance = new Attainment("data.db.100", "50093232", 5);
        String expResult = "data.db.100";
        String result = instance.getCourseCode();
        assertEquals(expResult, result, "This was wrong");
    }

    @Test
    public void testGetStudentNumber() {
        System.out.println("getStudentNumber");
        Attainment instance = new Attainment("data.db.100", "50093232", 5);
        String expResult = "50093232";
        String result = instance.getStudentNumber();
        assertEquals(expResult, result, "This was wrong");
    }

    @Test
    public void testGetGrade() {
        System.out.println("getGrade");
        Attainment instance = new Attainment("data.db.100", "50093232", 5);
        int expResult = 5;
        int result = instance.getGrade();
        assertEquals(expResult, result, "This was wrong");
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        Attainment instance = new Attainment("data.db.100", "50093232", 5);
        String expResult = "data.db.100 50093232 5";
        String result = instance.toString();
        assertEquals(expResult, result, "This was wrong");
    }

    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Attainment att = new Attainment("bbb", "50093232", 5);
        Attainment instance = new Attainment("aaa", "50093232", 5);
        int expResult = -1;
        int result = instance.compareTo(att);
        assertEquals(expResult, result, "This was wrong");
    }
}
