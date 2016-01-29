import static org.junit.Assert.*;
import org.junit.Test;

public class TestCompare{

    public Planet[] planets;

    @Test
    public void testRadius(){
        planets=new Planet[5];
        planets[0]=new Planet(1001, 50);
        planets[1]=new Planet(1010, 50);
        planets[2]=new Planet(1200, 501);
        planets[3]=new Planet(1020, 150);
        planets[4]=new Planet(1000, 510);
        Planet maxR=MaxPlanet.maxPlanet(planets,new RadiusComparator());
        assertEquals((double)1200, maxR.getRadius(),0.01);
    }

    @Test
    public void testMass(){
        planets=new Planet[5];
        planets[0]=new Planet(1001, 50);
        planets[1]=new Planet(1010, 50);
        planets[2]=new Planet(1200, 501);
        planets[3]=new Planet(1020, 150);
        planets[4]=new Planet(1000, 510);
        Planet maxM=MaxPlanet.maxPlanet(planets,new MassComparator());
        assertEquals((double)510, maxM.getMass(),0.01);
    }


    public static void main(String... args){
         jh61b.junit.textui.runClasses(TestCompare.class);   
    }

}