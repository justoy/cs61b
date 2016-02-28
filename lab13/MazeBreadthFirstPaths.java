import java.util.Observable;
import java.util.LinkedList;
/** 
 *  @author Josh Hug
 */

public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields: 
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze; 
    private LinkedList<Integer> fringe = new LinkedList<Integer>();

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);     
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;   
    }

    /** Conducts a breadth first search of the maze starting at vertex x. */
    private void bfs(int s) {
        fringe.add(s);
        marked[s] = true;
        announce();
        if(s==t){
            targetFound=true;
        }
        if(targetFound){
            return;
        }

        while(!fringe.isEmpty()){
            int v = fringe.pollFirst();
            for(int w:maze.adj(v)){
                if(!marked[w]){
                    fringe.add(w);
                    marked[w]=true;
                    edgeTo[w]=v;
                    announce();
                    distTo[w]=distTo[v]+1;
                    if(t==w){
                        targetFound=true;
                    }
                    if(targetFound){
                        announce();
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs(s);
    }
} 

