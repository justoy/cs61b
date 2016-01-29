import java.util.Comparator;
/* Import anything else you need here. */

/**
 * MaxPlanet.java
 */

public class MaxPlanet {

    /** Returns the Planet with the maximum value according to Comparator c. */
    public static Planet maxPlanet(Planet[] planets, Comparator<Planet> c) {
        Planet max=planets[0];
        for(Planet p:planets){
            if(c.compare(max, p)<0){
                max=p;
            }
        }
        return max;
    }
}