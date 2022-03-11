package relation;
import hypernymdatabase.HypernymDatabase;
import java.util.regex.Pattern;

/**
 * Especially is a relation - Np especially Np....
 * @author Yossi Maatook.
 */
public class Especially extends MultiNpsRelation {
    @Override
    public String getString() {
        return "especially";
    }

    @Override
    public Pattern getRegex() {
        return super.getRegex();
    }

    @Override
    public void addToDataBase(String string, HypernymDatabase database) {
        super.addToDataBase(string, database);
    }
}
