package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus{

    private final double energyStay = 0.01; // energy lose after stay
    private final double energyMove = 0.03; // energy lose after move

   @Test
    public void testBasics() {
        Clorus p = new Clorus(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), p.color());
        p.move();
        assertEquals(2- energyMove , p.energy(), 0.01);
        p.move();
        assertEquals(2-2*energyMove, p.energy(), 0.01);
        p.stay();
        assertEquals(2-2*energyMove-energyStay , p.energy(), 0.01);
        p.stay();
        assertEquals(2-2*energyMove-2*energyStay, p.energy(), 0.01);
    }

   @Test
    public void testReplicate() {
        Clorus p = new Clorus(2);
        Clorus p2 = p.replicate();
        assertNotSame(p,p2);
        assertEquals(1.00, p.energy(), 0.01);
        assertEquals(1.00, p2.energy(), 0.01);
    }

    @Test
    public void testStayChoice(){
        //choose stay
        Clorus p = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Plip());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());     

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);
}
    @Test
    public void testAttackChoice(){
        //choose attack
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Plip());
        surrounded.put(Direction.BOTTOM, new Empty());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());    

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.ATTACK, Direction.TOP);
        assertEquals(expected, actual); 
    }

    @Test
    public void testReplicateChoice(){
        //choose Replicate
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Empty());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible()); 

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);
        assertEquals(expected, actual);    
    }

    public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestClorus.class));
    }
}