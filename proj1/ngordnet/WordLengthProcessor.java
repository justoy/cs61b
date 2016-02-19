package ngordnet;

import java.util.Collection;

public class WordLengthProcessor implements YearlyRecordProcessor{
    public double process(YearlyRecord yearlyRecord) {
        Collection<String> words = yearlyRecord.words();
        double size = 0;
        double totalLength = 0;
        for(String word:words){
            int counts = yearlyRecord.count(word);
            totalLength += word.length()*counts;
            size += counts;
        }
        double avgLength = totalLength / size;
        return avgLength; 
    }
}