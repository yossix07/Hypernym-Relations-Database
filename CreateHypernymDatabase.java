//ID: 208641472

import hypernymdatabase.CreateDatabase;
import hypernymdatabase.HypernymDatabase;
import hypernymdatabase.ReadCorpus;
import java.io.IOException;
import java.rmi.UnexpectedException;

/**
 * Main 1 to ass7 - create hypernym database file in the received path argument
 * with the corpus in the received argument path.
 * @author Yossi Maatook.
 */
public class CreateHypernymDatabase {

    /**
     * Main 1 to ass7 - create hypernym database file in the received path argument
     * with the corpus in the received argument path.
     * @param args - command line arguments - corpus path and destination path.
     * @throws IOException - in case of a problem with reading files.
     */
    public static void main(String[] args) throws IOException {
        //Must get corpus path and destination path//
        if (args.length != 2) {
            throw new UnexpectedException("Must get two args!");
        }
        HypernymDatabase database = new HypernymDatabase();
        //Get the data of the corpus//
        ReadCorpus.readFolder(args[0], database);
        //Create the database file//
        CreateDatabase.createFile(args[1], database);
    }
}
