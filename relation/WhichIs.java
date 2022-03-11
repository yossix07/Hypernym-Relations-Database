package relation;
import hypernymdatabase.HypernymDatabase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SuchAs is a relation - Np which is Np....
 * @author Yossi Maatook.
 */
public class WhichIs extends MultiNpsRelation {
    @Override
    public String getString() {
        return "which is";
    }

    @Override
    public Pattern getRegex() {
        return Pattern.compile(Np.Regex + "(( )(,)( ))?" + " " + getString() + " "
                + "((an example|a kind|a class)? of )?" + Np.Regex);
    }

    @Override
    public void addToDataBase(String string, HypernymDatabase database) {
        Pattern npPattern = Pattern.compile("<np>(.*?)</np>");
        Matcher npMatcher = npPattern.matcher(string);
        try {
            String hyponym = null;
            //First np match will be the hyponym//
            if (npMatcher.find()) {
                hyponym = npMatcher.group(1);
            }
            String hyper = null;
            //Second np match will be the hypernym//
            if (npMatcher.find()) {
                hyper = npMatcher.group(1);
            }
            //Add the relation to the database//
            database.addRelations(hyper, hyponym);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
