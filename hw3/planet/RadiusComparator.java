import java.util.Comparator;

public class RadiusComparator implements Comparator<Planet>{
    
    public int compare(Planet planet1, Planet planet2){
        return (int)(planet1.getRadius()-planet2.getRadius());
    }
}