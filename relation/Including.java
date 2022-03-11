package relation;
import hypernymdatabase.HypernymDatabase;
import java.util.regex.Pattern;

/**
 * Including is a relation - Np including Np....
 * @author Yossi Maatook.
 */
public class Including extends MultiNpsRelation {
    @Override
    public String getString() {
        return "including";
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
