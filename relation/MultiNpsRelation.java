//ID: 208641472

package relation;
import hypernymdatabase.HypernymDatabase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MultiNpsRelation is a sentence with more than one Np.
 * @author Yossi Maatook.
 */
public abstract class MultiNpsRelation implements Relation {

    @Override
    public Pattern getRegex() {
        return Pattern.compile(Np.Regex + "(( )(,)( ))? " + this.getString()
                + " (" + Np.Regex + "(( )(,)( ))?)+( ?or | ?and )?(" + Np.Regex + ")?");
    }

    @Override
    public void addToDataBase(String string, HypernymDatabase database) {
        //np pattern//
        Pattern npPattern = Pattern.compile("<np>(.*?)</np>");
        Matcher npMatcher = npPattern.matcher(string);
        try {
            String hyper = null;
            //First np match will be the hypernym//
            if (npMatcher.find()) {
                hyper = npMatcher.group(1);
            }

            String hyponyms = string;
            Pattern hyperPattern = Pattern.compile("^(<np>" + hyper + "</np>)");
            Matcher hyperMatcher = hyperPattern.matcher(hyponyms);
            //Remove hypernym from the string//
            if (hyperMatcher.find()) {
                hyponyms = hyperMatcher.group(1);
            }

            //Take all the hyponyms from the string without the hypernym//
            while (npMatcher.find()) {
                String current = new String(npMatcher.group(1));
                database.addRelations(hyper, current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
