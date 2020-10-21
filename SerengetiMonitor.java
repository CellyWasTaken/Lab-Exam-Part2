import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.HashSet;

/**
 * Monitor counts of different types of animal.
 * Sightings are recorded by spotters.
 */
public class SerengetiMonitor
{
    private ArrayList<SerengetiSighting> serengetiSightings;
    
    /**
     * Create a SerengetiMonitor.
     */
    public SerengetiMonitor()
    {
        serengetiSightings = new ArrayList<>();
        addSightings("sightings.csv");
    }
    
    /**
     * Add the sightings recorded in the given filename to the current list.
     * @param filename A CSV file of Sighting records.
     */
    private void addSightings(String filename)
    {
        SightingReader reader = new SightingReader();
        serengetiSightings.addAll(reader.getSightings(filename));
    }
    
    /**
     * Print details of all the sightings.
     */
    public void printList()
    {
        serengetiSightings.forEach(sighting -> System.out.println(sighting.getInfo()));
        System.out.println();
    }
    
    /**
     * Print all the sightings by the given ranger.
     * @param ranger The ID of the ranger.
     */
    public void printSightingsBy(int ranger)
    {
        serengetiSightings.stream()
            .filter(rang -> ranger == (rang.getRanger()))
            .forEach(rang -> System.out.println(rang.getInfo() + "\n")); 
                
    }
    
    /**
     * Return a count of the number of sightings of the given animal in a given area
     * @param animal The type of animal
     * @param areaID The ID of area
     * @return The count of sightings of the given animal.
     */
    public int getCountOfIn(String animal, int areaID)
    {
        return serengetiSightings.stream()
            .filter(sighting -> animal.equals(sighting.getAnimal()))
            .filter(sighting -> areaID == sighting.getAreaID())
            .map(sighting -> sighting.getCount())
            .reduce(0, (sum,count) -> sum + count);
        
    }
    
    public String findAnimalsSpottedIn(int areaID)
    {
        List<serengetiSightings>sighting = serengetiSightings.stream()
            .filter(sighting -> areaID == sighting.getAreaID())
            .map(sighting -> sighting.getAnimal())
            .collect(Collectors.toList())
            .reduce("", (sighting, animal),sighting + animal);
    }
}
