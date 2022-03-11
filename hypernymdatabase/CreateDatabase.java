package hypernymdatabase;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * A class used to create a data base file to the giving address from the given data base.
 * @author Yossi Maatook.
 */
public class CreateDatabase {

    /**
     * Creates a text file of the given data base at the given address.
     * For every hypernym with more than 3 hyponym, prints the hypernym and than the hyponym in decreasing order.
     * @param address - address which the file will be created at.
     * @param database - the data base that will be printed on the file.
     * @throws IOException - in case of problem when creating file.
     */
    public static void createFile(String address, HypernymDatabase database) throws IOException {
        File file = new File(address + "\\hypernym_db.txt");
        TreeMap<String, TreeMap<String, Integer>> map = database.getMap();
        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

            //For every hypernym with more than 3 hyponym, prints it to the file//
            for (String hyper : map.keySet()) {
                if (database.numOfHypernyms(hyper) > 2) {
                    out.write(hyper + ":");
                    LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

                    //Sort the map in decreasing order//
                    map.get(hyper).entrySet().stream().sorted(Map.Entry.comparingByValue(
                            Comparator.reverseOrder())).forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

                    //Prints the sorted hyponyms//
                    for (String hyponym : sortedMap.keySet()) {
                        out.write(" " + hyponym + " (" + sortedMap.get(hyponym) + "),");
                    }
                    out.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Close the stream//
            if (out != null) {
                out.close();
            }
        }
    }
}
