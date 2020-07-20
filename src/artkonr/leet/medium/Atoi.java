package artkonr.leet.medium;

/**
 * Algorithm has the following key points:
 * <ul>
 *     <li>Algorithm should return the value if possible ASAP</li>
 *     <li>To control integer overflow exception handling is
 *          not preferred, it incurs additional overhead and slows
 *          down the algorithm by up to 300%</li>
 *     <li>Algorithm should take into account whether parsed string
 *          begins with +/-</li>
 *     <li>Algorithm should return {@link Integer#MAX_VALUE} or
 *          {@link Integer#MIN_VALUE} in case of int overflow</li>
 * </ul>
 *
 * <p>Leet code statistics:
 * <table>
 *     <thead>
 *         <tr>
 *             <th colspan="20"><b>Stat</b></th>
 *             <th colspan="20"><b>Val</b></th>
 *             <th colspan="20"><b>Beats, %</b></th>
 *         </tr>
 *     </thead>
 *     <tbody>
 *         <tr>
 *             <td colspan="20">Runtime Speed</td>
 *             <td colspan="20">2 ms</td>
 *             <td colspan="20">85.76</td>
 *         </tr>
 *         <tr>
 *             <td colspan="20">Memory footprint</td>
 *             <td colspan="20">39.3 Mb</td>
 *             <td colspan="20">71.73</td>
 *         </tr>
 *     </tbody>
 * </table>
 */
public class Atoi {

    private final String str;

    public Atoi(String converted) {
        this.str = converted;
    }

    public int convert() {

        // validate arg
        if (str == null)
            throw new IllegalArgumentException();

        // if passed is blank -> return 0
        if (str.isBlank())
            return 0;

        boolean firstFound = false;
        boolean makeNeg = false;
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);

            // if current char is not digit
            if (!Character.isDigit(curr)) {

                // if char is whitespace before anything
                // was found -> continue loop
                if (Character.isWhitespace(curr) && !firstFound)
                    continue;

                // if char is minus, result should be negative
                // and it also means that a match has been found
                if (curr == '-') {
                    // if minus is not the first -> break
                    if (firstFound)
                        break;
                    makeNeg = true;
                    firstFound = true;
                    continue;
                }

                // if char is plus, result should be positive
                // and it also means that a match has been found
                if (curr == '+') {
                    // if plus is not the first -> break
                    if (firstFound)
                        break;
                    firstFound = true;
                    continue;
                }

                break;
            }

            // if we are here than we certainly are at a beginning
            // of a numeric char sequence
            if (!firstFound)
                firstFound = true;

            // parse
            int asInt = Character.getNumericValue(curr) *
                    (makeNeg ? -1 : 1);

            // if resulting int is still 0, just set a new value
            // if result must be negative, set accordingly
            if (result == 0) {
                result = asInt;
                continue;
            }

            // calculate increase
            // if overflows -> return min or max of int32
            long toSet = result * 10L + asInt;
            if (toSet >= Integer.MAX_VALUE && !makeNeg)
                return Integer.MAX_VALUE;
            if (toSet <= Integer.MIN_VALUE && makeNeg)
                return Integer.MIN_VALUE;

            result = (int) toSet;

        }

        return result;
    }

}
