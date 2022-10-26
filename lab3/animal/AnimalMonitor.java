package animal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Monitor counts of different types of animal.
 * Sightings are recorded by spotters.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.03.01 (functional)
 */
public class AnimalMonitor implements AnimalMonitorInterface
{
    private ArrayList<Sighting> sightings;
    
    /**
     * Create an AnimalMonitor.
     */
    public AnimalMonitor()
    {
        this.sightings = new ArrayList<>();
    }
    
    /**
     * Add the sightings recorded in the given filename to the current list.
     * @param filename A CSV file of Sighting records.
     */
    public void addSightings(String filename)
    {
        SightingReader reader = new SightingReader();
        sightings.addAll(reader.getSightings(filename));
    }
    
    /**
     * Print details of all the sightings.
     */
     public AnimalMonitor printList(){
    
        sightings.forEach(sighting -> System.out.println(sighting.getDetails()));
        return this;
        
    }
    
    /**
     * Print details of all the sightings of the given animal.
     * @param animal The type of animal.
     */
    public AnimalMonitor printSightingsOf(String animal)
    {
        sightings.stream()
                 .filter(sighting -> animal.equals(sighting.getAnimal()))
                 .forEach(sighting -> System.out.println(sighting.getDetails()));        
        return this;
    }
    
    /**
     * Print all the sightings by the given spotter.
     * @param spotter The ID of the spotter.
     */
    public AnimalMonitor printSightingsBy(int spotter)
    {
        sightings.stream()
                 .filter(sighting -> sighting.getSpotter() == spotter)
                 .map(sighting -> sighting.getDetails())
                 .forEach(details -> System.out.println(details));        
        return this;
    }
    
    /**
     * Return a count of the number of sightings of the given animal.
     * @param animal The type of animal.
     * @return The count of sightings of the given animal.
     */
    public int getCount(String animal)
    {
        return sightings.stream()
                        .filter(sighting -> animal.equals(sighting.getAnimal()))
                        .map(sighting -> sighting.getCount())
                        .reduce(0, (runningSum, count) -> runningSum + count);
    }
    public String sightingsBy(int spotter, int when){
        
        return sightings.stream()
                        .filter(sighting -> sighting.getSpotter()==spotter && sighting.getPeriod()==when && sighting.getCount()!=0)
                        .map(sighting-> sighting.getAnimal())
                        .reduce("",(a,b)->a +" "+ b);
                
        
        
    }
    public AnimalMonitor removeSpotter(int spotter){
        sightings.removeIf(sighting->sighting.getSpotter()==spotter);
        /*sightings.stream()
                 .filter(sighting->sighting.getSpotter()==spotter)
                 .forEach(sighting->sightings.remove(sighting));*/
        return this;
    }
    public AnimalMonitor removeZeroCounts(){
        sightings.removeIf(sighting->sighting.getCount()==0);
                 /*.filter(sighting ->sighting.getCount()==0)
                 .forEach(sighting->sightings.remove(sighting));*/
        return this;
          //this       
        
        
                 
                 
                           
        
    }
    public AnimalMonitor printEndangered(ArrayList<String> animalNames, int dangerThreshold){
        
        animalNames.stream()
                   .filter(animal->getCount(animal)<=dangerThreshold)
                   .forEach(animal->System.out.println(animal));
        return this;
        /*ArrayList<Sighting> endanger = new ArrayList<>();
        ArrayList<AnimalMonitor> tm = new ArrayList<>();
        System.out.println(animalNames);
        System.out.println("kuch to chal");*/
        
        //sightings.stream().filter(s -> (s.getCount()>= dangerThreshold)).forEach(s -> System.out.println(s));
        
        //sightings.stream().filter(sighting->sighting.getCount()<= dangerThreshold).map(sighting -> new AnimalMonitor(sighting.getAnimal(), sighting.getSpotter(), sighting.getCount(),sighting.getArea(),sighting.getPeriod())).forEach(s -> tm.add(s));
        
                 
        
        
        
        
        //return this;
         /*sightings.stream()
                 .filter(sighting->sighting.getCount()<= dangerThreshold)
                 .map(sighting->sighting.getAnimal())
                 .forEach(sighting->animalNames.add(sighting));*/
         //return this;
         
                        
                          
    }
    public int getCount(int when){
        return sightings.stream()
                        .filter(sighting->when==sighting.getPeriod())
                        .map(sighting -> sighting.getCount())
                        .reduce(0, (runningSum, count) -> runningSum + count);
        
    }
    }


