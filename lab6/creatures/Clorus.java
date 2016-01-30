package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

public class Clorus extends Creature{

    /** red color. */
    private int r=34;
    /** green color. */
    private int g=0;
    /** blue color. */
    private int b=231;

    private final double energyStay = 0.01; // energy lose after stay
    private final double energyMove = 0.03; // energy lose after move

    public Clorus(double e){
        super("clorus");
        energy=e;
    }

    public Clorus(){
        this(1);
    }

    public Color color() {
        return color(r, g, b);
    }
    
    public void attack(Creature c) {
        energy += c.energy();
    }
    public Clorus replicate() {
        energy *= 0.5;
        return new Clorus(energy);
    }
    public void move(){
        energy -= energyMove;
    }

    public void stay(){
        energy -= energyStay;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        if(empties.size()==0){
            return new Action(Action.ActionType.STAY);
        }

        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if(!plips.isEmpty()){
            Direction moveDir;
            if(plips.size()==1){
                moveDir = plips.get(0);              
            }
            else{
                moveDir = HugLifeUtils.randomEntry(plips);           
            }
            return new Action(Action.ActionType.ATTACK, moveDir);   
        }
            
        Direction replicateDir;//also means moveDir
        if(empties.size()==1){
            replicateDir = empties.get(0);
        }
        else{
            replicateDir = HugLifeUtils.randomEntry(empties);
        }

        if(energy>=1){
            return new Action(Action.ActionType.REPLICATE, replicateDir);
        }
        return new Action(Action.ActionType.MOVE, replicateDir);
    }
}