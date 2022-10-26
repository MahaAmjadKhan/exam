package registrar;

/**
 * Write a description of class Enrollment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Registration {
    // instance variables - replace the example below with your own
    private String courseID;
    private int section;
    private int timeSlot;
    private String student;
    private String professor;

    public Registration(String courseID, int section, int timeSlot, String student, String professor) {
        this.courseID = courseID;
        this.section = section;
        this.timeSlot = timeSlot;
        this.student = student;
        this.professor = professor;
    }

    /**
     * Constructor for objects of class Registration
     */


    /**
     * toString method should include all the attributes of the object
     */
    public String toString() {
        return this.student + " is registered for " + this.courseID + " slot " + this.timeSlot + " " + this.professor+"in section "+this.section;
    }


    public String getProfessor() {
        return professor;
    }
    public String getCourseID(){
        return courseID;
    }
    
    public int getSection(){
        return section;
    }
    public int getTimeSlot(){
        return timeSlot;
    }
    public String getStudent(){
        return student;
    }
    
}
