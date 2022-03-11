package relation;
import hypernymdatabase.HypernymDatabase;
import java.util.regex.Pattern;

/**
 * As is a relation - Such Np as Np....
 * @author Yossi Maatook.
 */
public class As extends MultiNpsRelation {
    @Override
    public String getString() {
        return "as";
    }

    @Override
    public Pattern getRegex() {
        return Pattern.compile("such " + Np.Regex + "(( )(,)( ))? " + this.getString() + " (" + Np.Regex
                + "(( )(,)( ))?)+( ?or | ?and )?(" + Np.Regex + ")?");

    }

    @Override
    public void addToDataBase(String string, HypernymDatabase database) {
        super.addToDataBase(string, database);
    }
}
