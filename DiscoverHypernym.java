import hypernymdatabase.HypernymDatabase;
import hypernymdatabase.PrintNumOfAppearances;
import hypernymdatabase.ReadCorpus;
import java.io.IOException;
import java.rmi.UnexpectedException;

/**
 * Main 2 to ass7 - gets a corpus path and a lemma and prints the lemma's hypernyms in the corpus and the
 * number of relation between them for each hypernym.
 * @author Yossi Maatook.
 */
public class DiscoverHypernym {

    /**
     * Main 2 to ass7 - gets a corpus path and a lemma and prints the lemma's hypernyms in the corpus and the
     * number of relation between them for each hypernym.
     * @param args - command line arguments - corpus path and destination path.
     * @throws IOException - in case of a problem with reading files.
     */
    public static void main(String[] args) throws IOException {
        //In case there aren't enough arguments//
        if (args.length < 2) {
            throw new UnexpectedException("Must get at least two args!");
        }

        String lemma = args[1];

        //In case the lemma is a few words lemma//
        if (args.length > 2) {
            for (int i = 2; i < args.length; i++) {
                lemma = lemma + " " + args[i];
            }
        }

        HypernymDatabase database = new HypernymDatabase();
        //Read the corpus//
        ReadCorpus.readFolder(args[0], database);
        //Prints the lemma's hypernyms and num of appearances in each//
        PrintNumOfAppearances.findAppearances(lemma, database);
    }

}
