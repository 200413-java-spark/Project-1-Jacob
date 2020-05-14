package spark.logic;

    // 1,3,4,5,7,8,9,11
public class EntryParser {

    public static Entry parse(String s) {
        String[] output = new String[8];
        String[] toParse = s.split(",");

        output[0] = toParse[1];
        output[1] = toParse[3];
        output[2] = toParse[4];
        output[3] = toParse[5];
        output[4] = toParse[7];
        output[5] = toParse[8];
        output[6] = toParse[9];
        output[7] = toParse[11];

        Entry entry = new Entry(output);
        return entry;
    }
    
}