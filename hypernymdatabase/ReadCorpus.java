//ID: 208641472

package hypernymdatabase;
import relation.As;
import relation.Including;
import relation.Relation;
import relation.SuchAs;
import relation.Especially;
import relation.WhichIs;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;

/**
 * ReadCorpus create HypernymDatabase database from the files in the revived address.
 */
public class ReadCorpus {
    /**
     * Read the files in the received folder and store the hypernym-hyponym relations in the received
     * HypernymDatabase database.
     * @param address - address of folder with files to be read.
     * @param database - the data base which we want to sotre in the data.
     * @throws IOException - in case the files reading didn't work.
     */
    public static void readFolder(String address, HypernymDatabase database) throws IOException {
        File folder = new File(address);
        File[] folderFiles = folder.listFiles();

        //Read every file in the folder//
        for (File file:folderFiles) {
            readFile(file, database);
        }
    }

    /**
     * Read received file and store the hypernym-hyponym relations in the received HypernymDatabase database.
     * @param file - the file with the sentences that contains hypernym-hyponym relations.
     * @param database - the database to store the data in.
     * @throws IOException - in case of problem at reading file.
     */
    private static void readFile(File file, HypernymDatabase database) throws IOException {
        //Array of the relations//
        Relation[] relations = {new SuchAs(), new As(), new Including(),
               new Especially(), new WhichIs()};
        BufferedReader in = null;

        //Check for each relation if it appears in the lines of the file and add to database accordingly//
        for (Relation relation:relations) {
            try {
                in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                List<String> lines = Files.readAllLines(file.toPath());

                for (String line:lines) {
                    Matcher matcher = relation.getRegex().matcher(line);
                    //In case the line is empty, skip it//
                    if (line.length() == 0) {
                        continue;
                    }
                    //As long the relation appears on the line, add it to the database//
                    while (matcher.find()) {
                        relation.addToDataBase(matcher.group(), database);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //Close the stream//
                if (in != null) {
                    in.close();
                }
            }
        }
    }
}
