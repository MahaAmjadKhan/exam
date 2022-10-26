package grading;

import animal.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import java.io.IOException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(GradingTestWatcher.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@GradeValue(30)
public class CompleteAnimalMonitorTest {

    private AnimalMonitor am;
    private String datafile = "animal/sightings.csv";
    private String sourceFile = "animal/AnimalMonitor.java";

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @BeforeEach
    void setUp() throws IOException {
        am = new AnimalMonitor();
        System.setOut(new PrintStream(outputStreamCaptor));
        am.addSightings(datafile);
        if(outputStreamCaptor.toString().contains("Unable to open")){
            throw new IOException("Unable to open data file " + datafile);    
        }
        
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
        //System.out.println(outputStreamCaptor.toString());
    }
 

    @Test
    @Order(1)
    @GradeValue(1)
    public void dataFileAvailable() throws IOException {
        Path filePath = Paths.get(datafile);
        assertTrue(Files.exists(filePath), datafile + "data file could not be found");
        long fileSize = Files.size(filePath);
        assertEquals(405, Files.size(filePath), datafile + "data file is wrong size");
    }
    
    @Test
    @Order(3)
    @GradeValue(1)
        public void getCount() {
        assertEquals(70, am.getCount(1), "getCount(int) not correct");
    }
    
    @Test
    @Order(4)
    @GradeValue(1)
        public void getCountAnimal() {
        assertEquals(14, am.getCount("Mountain Gorilla"), "getCount(String) not correct");
    }
    
    @Test
    @Order(5)
    @GradeValue(1)
    public void pSightingsOf() {
                String[] expected = {
                    "Elephant, count = 0, area = 1, spotter = 0, period = 0",
                    "Elephant, count = 24, area = 2, spotter = 3, period = 2"
        };
        AnimalMonitorInterface am2 = am.printSightingsOf("Elephant");
        assertSame(am, am2, "printSightingsOf should chain");
        String out = outputStreamCaptor.toString();
        for( String e: expected ) assertTrue(out.contains(e), "printSightingsOf did not output correctly");
        assertEquals(expected.length, out.lines().count(), "printSightingsOf wrong number of sightings");
    }
    
    @Test
    @Order(6)
    @GradeValue(1)
    public void printEndangered() {
        String[] initializers = {"Elephant", "Mountain Gorilla", "Topi", "Horse"};
        ArrayList<String> animals = new ArrayList(java.util.Arrays.asList(initializers));
        assertSame(am, am.printEndangered(animals, 50), "wrong return value");
        String x = outputStreamCaptor.toString();
        assertTrue(outputStreamCaptor.toString().contains("Horse"), "Wrong result printed");
        assertTrue(outputStreamCaptor.toString().contains("Mountain Gorilla"), "Wrong result printed");
        assertFalse(outputStreamCaptor.toString().contains("Topi"), "Wrong result printed");
        assertTrue(outputStreamCaptor.toString().contains("Elephant"), "Wrong result printed");

    }

    @Test
    @GradeValue(10)
    public void codeValidity() throws java.io.IOException {
        String content = Files.readString(Path.of(sourceFile));

        assertFalse(content.contains("while"), "'while' detected in source. Loop is not allowed. Use streams and lambdas in this problem");
        assertFalse(content.contains("for("), "For loop is not allowed. Use streams and lambdas in this problem");
        assertFalse(content.matches("\\sfor[\\s(]"), "For loop is not allowed. Use streams and lambdas in this problem");

    }
    
    @GradeValue(1)
    @Test
    @Order(2)
    public void sourceCodeAvailable() throws java.io.IOException {
        String content = Files.readString(Path.of(sourceFile));
    }
    
    @GradeValue(4)
    @Test
    public void codeValidityFor() throws java.io.IOException {
        final AtomicInteger lineNo = new AtomicInteger();
        
        // chains must work first
        chain1();
        Files.lines(Path.of(sourceFile)).forEach(line ->
            {
                assertFalse(line.matches("(.*)\\sfor[\\s(](.*)"), "<for> not allowed:" + " line " + lineNo.incrementAndGet()
                                                                                       + " " + sourceFile);
            });
 
    }

    @Test
    @GradeValue(3)
    @Order(8)
    public void chain1() {
        String[] expected = {
                "Mountain Gorilla, count = 0, area = 2, spotter = 3, period = 0",
                "Buffalo, count = 2, area = 1, spotter = 3, period = 0",
                "Topi, count = 25, area = 1, spotter = 3, period = 0",
                "Buffalo, count = 0, area = 2, spotter = 3, period = 1",
                "Topi, count = 30, area = 2, spotter = 3, period = 1",
                "Topi, count = 30, area = 2, spotter = 3, period = 2",
                "Elephant, count = 24, area = 2, spotter = 3, period = 2"
        };
        assertEquals(28, am.printSightingsBy(3).removeZeroCounts().getCount("Buffalo"), "chain of printSightingsBy/removeZeroCounts/getCount failed");
        String out = outputStreamCaptor.toString();
        for( String e: expected ) assertTrue(out.contains(e), "printSightingsBy did not chain correctly");
        assertEquals(expected.length, out.lines().count(), "printSightingsBy wrong number of sightings");

    }

    @Test
    @Order(8)
    @GradeValue(2)
    public void chain2() {
        String[] expected = {
                "Mountain Gorilla, count = 3, area = 1, spotter = 0, period = 0",
                "Buffalo, count = 10, area = 1, spotter = 0, period = 0",
                "Mountain Gorilla, count = 1, area = 2, spotter = 1, period = 0",
                "Mountain Gorilla, count = 3, area = 3, spotter = 2, period = 0",
                "Buffalo, count = 2, area = 1, spotter = 3, period = 0",
                "Topi, count = 25, area = 1, spotter = 3, period = 0",
                "Mountain Gorilla, count = 4, area = 1, spotter = 0, period = 1",
                "Buffalo, count = 16, area = 1, spotter = 0, period = 1",
                "Topi, count = 20, area = 1, spotter = 1, period = 1",
                "Topi, count = 30, area = 2, spotter = 3, period = 1",
                "Mountain Gorilla, count = 1, area = 1, spotter = 0, period = 2",
                "Mountain Gorilla, count = 2, area = 2, spotter = 1, period = 2",
                "Topi, count = 30, area = 2, spotter = 3, period = 2",
                "Elephant, count = 24, area = 2, spotter = 3, period = 2",
        };
        assertEquals(57, am.removeZeroCounts().printList().getCount(2), "chain of removeZeroCounts/printList/getCount failed");
        String out = outputStreamCaptor.toString();
        for( String e: expected ) assertTrue(out.contains(e), "removeZeroCounts/printList did not produce correct output when chained");
        assertEquals(expected.length, out.lines().count(), "removeZeroCounts wrong number of sightings");

    }
    
    @Test
    @Order(8)
    @GradeValue(2)
    public void chain3(){
        String[] expected = {
            "Mountain Gorilla, count = 3, area = 1, spotter = 0, period = 0",
            "Buffalo, count = 10, area = 1, spotter = 0, period = 0",
            "Elephant, count = 0, area = 1, spotter = 0, period = 0",
            "Mountain Gorilla, count = 1, area = 2, spotter = 1, period = 0",
            "Mountain Gorilla, count = 0, area = 2, spotter = 3, period = 0",
            "Buffalo, count = 2, area = 1, spotter = 3, period = 0",
            "Topi, count = 25, area = 1, spotter = 3, period = 0",
            "Mountain Gorilla, count = 4, area = 1, spotter = 0, period = 1",
            "Buffalo, count = 16, area = 1, spotter = 0, period = 1",
            "Topi, count = 20, area = 1, spotter = 1, period = 1",
            "Buffalo, count = 0, area = 2, spotter = 3, period = 1",
            "Topi, count = 30, area = 2, spotter = 3, period = 1",
            "Mountain Gorilla, count = 1, area = 1, spotter = 0, period = 2",
            "Mountain Gorilla, count = 2, area = 2, spotter = 1, period = 2",
            "Topi, count = 30, area = 2, spotter = 3, period = 2",
            "Elephant, count = 24, area = 2, spotter = 3, period = 2"
        };
        am.removeSpotter(2).printList();
        String out = outputStreamCaptor.toString();
        for( String e: expected ) assertTrue(out.contains(e), "removeSpotter/printList chain did not produce correct output");
        assertEquals(expected.length, out.lines().count(), "removeSpotter wrong number of sightings");
    }

    @Test
    @GradeValue(3)
    @Order(7)
    public void sightingsBy() {
        String animalList = am.sightingsBy(3, 1);
        assertTrue(animalList.contains("Topi"), "problem with sightingsBy");
        assertFalse(animalList.contains("Buffalo"), "problem with sightingsBy");
    }
}
