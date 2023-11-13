package seedu.address.model.util;


/**
 * A utility class to format all names in the desired format, regardless of upper or lower casing.
 */
public class NameFormatter {



    /**
     * Formats the given String so that the first letter of every word is capitalised and every other word is
     * in lower case. Also trims excess whitespace.
     *
     * @param name The input name. e.g. "jOhN   doE"
     * @return The correctly formatted name. e.g. "John Doe"
     */
    public static String format(String name) {
        String trimmedName = name.trim().replaceAll("\\s+", " ");
        String[] nameParts = trimmedName.split(" ");
        String capitalisedName = "";
        for (String word: nameParts) {
            capitalisedName += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
        }
        return capitalisedName.trim();
    }
}
