import java.util.Observable;
/** 
 *  @author Josh Hug
 */

public class MazeCycles extends MazeExplorer {
    /* Inherits public fields: 
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze; 
    private int[] previous;


    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        previous = new int[maze.V()];        
    }

    private void dfs(int v) {
        marked[v] = true;
        announce();
        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                previous[w]=v;
                System.out.println("w: " + w + " v: "+ v);
                dfs(w);         
            }
            //if w is marked
            else{
                if(previous[v]!=w){
                System.out.println("w: " + w + " v: "+ v);
                    return;
                }
            }
        }
    }

    @Override
    public void solve() {
        dfs(s);
    }
} 