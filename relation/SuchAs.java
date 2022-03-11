package relation;
import hypernymdatabase.HypernymDatabase;
import java.util.regex.Pattern;

/**
 * SuchAs is a relation - Np such as Np....
 * @author Yossi Maatook.
 */
public class SuchAs extends MultiNpsRelation {
    @Override
    public String getString() {
        return "such as";
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
