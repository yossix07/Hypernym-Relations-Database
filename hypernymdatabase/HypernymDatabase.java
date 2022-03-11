package hypernymdatabase;
import java.util.TreeMap;

/**
 * HypernymDatabase is a mapping each hypernym to it's hyponyms and thier counters.
 * @author Yossi Maatook.
 */
public class HypernymDatabase {
    public static final int ADD_RELATION = 1;
    private TreeMap<String, TreeMap<String, Integer>> map;

    /**
     * Constructor.
     */
    public HypernymDatabase() {
        this.map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    /**
     * Adds a relation to the map, meaning add the received hyponym to the received hypernym's relations.
     * @param hypernym - the hypernym of the relation.
     * @param hyponym - the hyponym of the relation.
     */
    public void addRelations(String hypernym, String hyponym) {
        //In case the current hypernym doesn't exist in the map//
        if (!this.map.containsKey(hypernym)) {
            this.map.put(hypernym, new TreeMap<String, Integer>());
            this.map.get(hypernym).put(hyponym, ADD_RELATION);
            return;
        }
        //In case they're related, add one to the counter of relations//
        if (this.map.get(hypernym).containsKey(hyponym)) {
            int current = this.map.get(hypernym).get(hyponym);
            this.map.get(hypernym).put(hyponym, current + ADD_RELATION);
            return;
        }
        this.map.get(hypernym).put(hyponym, ADD_RELATION);
    }

    /**
     * Return the map of the database.
     * @return the map of the database.
     */
    public TreeMap<String, TreeMap<String, Integer>> getMap() {
        return this.map;
    }

    /**
     * Returns the number of hyponyms the received hypernym has.
     * @param hypernym - the hypernym we want to know how much hyponyms he has.
     * @return the number of hyponyms the received hypernym has. if it dosent exist in the database,
     * returns -1.
     */
    public int numOfHypernyms(String hypernym) {
        if (this.map.containsKey(hypernym)) {
            return this.map.get(hypernym).size();
        }
        return -1;
    }

    /**
     * Returns true if the received hyponym exist in the database.
     * @param hyponym - the hyponym to be know if exist.
     * @return true if it is exist in the database and false otherwise.
     */
    public boolean doesHyponymExist(String hyponym) {
        //In case the hyponym exist in the database, return true//
        for (String hyper:this.map.keySet()) {
            for (String currentHyponym:this.map.get(hyper).keySet()) {
                if (currentHyponym.equals(hyponym)) {
                    return true;
                }
            }
        }
        return false;
    }
}
