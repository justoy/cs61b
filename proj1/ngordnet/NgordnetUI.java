/* Starter code for NgordnetUI (part 7 of the project). Rename this file and 
remove this comment. */

package ngordnet;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.*;

/** Provides a simple user interface for exploring WordNet and NGram data.
 *  @author [yournamehere mcjones]
 */
public class NgordnetUI {
  public static void main(String[] args) {
    In in = new In("./ngordnet/ngordnetui.config");
    System.out.println("Reading ngordnetui.config...");

    String wordFile = in.readString();
    String countFile = in.readString();
    String synsetFile = in.readString();
    String hyponymFile = in.readString();
    NGramMap ngm = new NGramMap(wordFile, countFile);
    WordNet wn = new WordNet(synsetFile, hyponymFile);
    System.out.println("\nBased on ngordnetui.config, using the following: "
       + wordFile + ", " + countFile + ", " + synsetFile +
       ", and " + hyponymFile + ".");

    System.out.println("\nFor tips on implementing NgordnetUI, see ExampleUI.java.");
    
    int endYear = 3000;
    int startYear = -1;

    while(true){
      System.out.print("> ");
      String line = StdIn.readLine();
      String[] rawTokens = line.split(" ");
      String command = rawTokens[0];
      String[] tokens = new String[rawTokens.length - 1];
      System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
      switch(command){
        case "quit"://quit: program exits
            System.exit(0);
        case "help"://help: Provides a list of commands.
            In helpIn = new In("./ngordnet/help.txt");
            String helpStr = helpIn.readAll();
            System.out.println(helpStr);
            break;
        case "range"://range [start] [end]: resets the start and end years to the values provided. affects only future plots, not existing plots.
            if(tokens.length==2){
                startYear = Integer.parseInt(tokens[0]); 
                endYear = Integer.parseInt(tokens[1]);
            }
            else System.out.println("Illegal inputs");
            break;
        case "count"://count [word] [year]: print the count of word in the given year.
            if(tokens.length == 2){
                int year = Integer.parseInt(tokens[1]);
                YearlyRecord yr = ngm.getRecord(year);
                int count = yr.count(tokens[0]);
                System.out.println(count);               
            }
            else System.out.println("Illegal inputs");
            break;
        case "hyponyms"://hyponyms [word]: prints all hyponyms of the given word using the default Set string representation.
            if(tokens.length==1)System.out.println(wn.hyponyms(tokens[0]));
            else System.out.println("Illegal inputs");
            break;
        case "history"://history [words...]: plots relative frequency of all words from start to end.
            if(tokens.length>1) Plotter.plotAllWords(ngm, tokens,startYear,endYear);
            else if(tokens.length==1) Plotter.plotWeightHistory(ngm,tokens[0],startYear,endYear);
            else System.out.println("Illegal inputs");
            break;
        case "hypohist"://hypohist [words...]: plots relative frequency of all hyponyms of words from start to end
            if (tokens.length == 1)Plotter.plotCategoryWeights(ngm, wn, tokens[0], startYear, endYear); 
            else if(tokens.length>1)Plotter.plotCategoryWeights(ngm, wn, tokens, startYear, endYear);
            else    System.out.println("Illegal inputs");
            break;
        case"wordlength"://wordlength: plots the length of the average word from start to end.
            Plotter.plotProcessedHistory(ngm,startYear,endYear,new WordLengthProcessor());
            break;
        case "zipf"://zipf [year]: plots the count (or weight) of every word vs. its rank on a log log plot.
            if(tokens.length>0)Plotter.plotZipfsLaw(ngm, Integer.parseInt(tokens[0]));
            else    System.out.println("Illegal inputs");
            break;
        default:
            System.out.println("Illegal inputs");
            break;
        }
    }
}

private static class MyComparator implements Comparator<Number>{ 
    @Override
    public int compare(Number n1, Number n2) {
        int i1 = n1.intValue();
        int i2 = n2.intValue();
        return i1-i2;
    }
}
} 
