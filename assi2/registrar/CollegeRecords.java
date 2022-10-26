package registrar;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


/**
 * Write a description of class CollegeRegistrar here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CollegeRecords implements CollegeRecordsInterface {
    private ArrayList<Registration> records;
    private ArrayList<String> course;


    /**
     * Constructor for_ objects of class CollegeRegistrar
     */
    public CollegeRecords()
    {
        records = new ArrayList<>();
        course = new ArrayList<>();
    }

    /**
     * Add a valid courseID for_ validity checking. Only courseIDs added are considered valid.
     * Do not permit a duplicate courseID to be added
     * @param courseId the course ID to add
     * @return false if this course is already listed, otherwise false
     */

    public boolean addValidCourseID(String courseId){
        /*var count = 0;
        boolean t = records.stream().anyMatch(x -> x.getCourseID().split(" ")[1].equals(courseId.split(" ")[1] ));
        boolean z =records.stream().anyMatch(x -> x.getCourseID().equals(courseId));
        //System.out.println(t);
        System.out.println(courseId);
        if(courseId == "COMP 1501" && count == 0){count++;
        return true;}
        System.out.println(count);
        if(z == true){return t=false;}else if(z == false && t == false){return t = true;}else if(t==true && z == false){return t = false;}else{return t = true;}*/
        
        //System.out.println(t);
        //records.stream().forEach(r -> this.course.add(r.getCourseID()));
        
        //System.out.println(course);
     
        
        
        //if(t == true){t= false;}else {t= true;};
        if(course.isEmpty() || (course.stream().filter(r->r.equals(courseId)).findAny().isPresent()!=true)){
            course.add(courseId);
            return true;
        }
        else{
            return false;
        }
        
    }


    /**
     * add the registration to the college records
     *
     * @param  a new Registration object
     * @return    chaining object
     */
    @Override
    public CollegeRecords add(Registration newRegistration)
    {
        records.add(newRegistration);
        return this;
    }

    /**
     *
     * @return a count of the registration records
     */
    public int count(){
        return records.size();
    }

    public String toString()
    {
        
        var trail = new Object(){ String str = ""; };
        records.forEach(s->{            
              trail.str += s.toString();
        });
        
        return trail.str+",";
      
               

        
    }

    /**
     * Use all three validity predicates to remove invalid records from this
     * CollegeRecords object and return records that
     * were removed as a different CollegeRecords object.
     *
     * @return a CollegeRecords of invalid registrations that were removed
     */
    @Override
    public CollegeRecords clean() {
        CollegeRecords newR = new CollegeRecords();
        records.stream().filter(r->!verifySlot(r) || !verifySection(r) || !verifyCourseID(r))
                             .forEach(r-> newR.add(r));
        
        records.removeIf(r->!verifySlot(r) || !verifySection(r) || !verifyCourseID(r));
        
        //System.out.println(newR);
        return newR;
        /*CollegeRecords newR = new CollegeRecords();
        ArrayList<Registration> badRecords = records.stream()
                                                     .filter(e->!verifySlot(e) || !verifySection(e) || !verifyCourseID(e))
                                                     .collect(Collectors.toCollection(ArrayList::new));
        badRecords.forEach(e->records.remove(e));
        badRecords.forEach(e->newR.add(e));
        return newR;*/
    }

    @Override public CollegeRecords removeDuplicates() {
        /*CollegeRecords Dup = new CollegeRecords();
        records.stream()//.filter(r->addValidCourseID(r.getCourseID()))
                        .forEach(r->Dup.add(r));
        int freq=frequency(records,                
        records.removeIf(r->!addValidCourseID(r.getCourseID()));
        return Dup;*/
        CollegeRecords newRec=new CollegeRecords();
        Set<String> rec=new HashSet<>();
        Set<String> rec1=new HashSet<>();
        
        this.records.stream().filter(r -> !rec1.add(r.toString()))
                            .forEach(r->newRec.add(r));
         records.removeIf(r->(!rec.add(r.toString())));
         return newRec;
        
    }

    public CollegeRecords recordsForStudent(String student)
    {   CollegeRecords newR = new CollegeRecords();
        this.records.stream().filter(r -> r.getStudent().equals(student))
                             .forEach(r->newR.add(r));
        return newR;    
    }

    public int courseCount(String student)
    {
        int count = 0;
        var trail = new Object(){ int cnt = 0; };
        records.stream().filter(r-> r.getStudent().equals(student)).forEach(s -> trail.cnt++);
        //System.out.println(trail.cnt);
        return trail.cnt;
    }

    /**
     * the Registrations listing this professor
     * @param professor
     * @return a CollegeRecords object of all registrations with the given professor
     */
    public CollegeRecords recordsForProfessor(String professor)
    {
        CollegeRecords newRecords = new CollegeRecords();
        this.records.stream().filter(r -> r.getProfessor().equals(professor))
                             .forEach(r->newRecords.add(r));
        return newRecords;
    }

    public CollegeRecords sectionRecords(String courseID, int section) {
        CollegeRecords newRecords = new CollegeRecords();
        records.stream().filter(r -> r.getCourseID().equals(courseID)).filter(fr -> fr.getSection() == section).forEach(ffr -> newRecords.add(ffr));
        return newRecords;
    }

    public CollegeRecords dropAllClassesForStudent(String student)
    {
         CollegeRecords newRecords = new CollegeRecords();
         this.records.stream().filter(r -> r.getStudent().equals(student))
                              .forEach(r->newRecords.add(r));
         records.removeIf(r->r.getStudent().equals(student));
         
         return newRecords;
    }

    public String profCourses(String professor){
        return records.stream()
               .filter(r->r.getProfessor().equals(professor))
               .filter(r->addValidCourseID(r.getCourseID()))
               .map(r->r.getCourseID())
               .reduce("",(a,b)->a+" "+b);
        
    }
    private boolean verifyCourseID(Registration r){
        //return addValidCourseID(r.getCourseID());
        //return addValidCourseID(r.getCourseID());
        return course.stream()
                     .filter(e->r.getCourseID().equals(e))
                     .findAny()
                     .isPresent();
    }
    private boolean verifySection(Registration r){
         //records.stream()
          //.filter(r->r>0 &&r<2);
          
        if(r.getSection()>=0 && r.getSection()<=2){
            
            return true;
        }
    
        return false;
    }
    private boolean verifySlot(Registration r){
        if(r.getTimeSlot()>=12 && r.getTimeSlot()<=25){
            
        return true;
    }
    return false;
    }
    
    }


