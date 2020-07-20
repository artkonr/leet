package artkonr.leet.medium;

import java.util.Map;

/**
 * This algorithm, contrary to the classic solution, which uses recursive function,
 *  attempts to get by without it. Reason is to avoid using {@link String} types
 *  and go with {@code char}. Although, in some places the algorithm has nested
 *  loops, it has proven to be faster than recursive approach anyway.
 * <p>Algorithm goes like this:
 * <ol>
 *     <li>First, we attempt two fast-tracks: either the max value we know we can get,
 *      or the value already defined in the dictionary</li>
 *     <li>Then we view the number as some number of 1000, of 100 and downwards.
 *      Then, each power of ten is interpreted as a sequence of roman chars.</li>
 * </ol>
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
 *             <td colspan="20">5-9 ms</td>
 *             <td colspan="20">69.31</td>
 *         </tr>
 *         <tr>
 *             <td colspan="20">Memory footprint</td>
 *             <td colspan="20">39.6-40 Mb</td>
 *             <td colspan="20">40.46</td>
 *         </tr>
 *     </tbody>
 * </table>
 */
public class IntegerToRoman {

    private static final int BOUND = 3999;
    private static final Map<Integer, Character> MM = Map.of(
            1, 'I',
            5, 'V',
            10, 'X',
            50, 'L',
            100, 'C',
            500, 'D',
            1000, 'M'
    );

    public String convert(int num) {

        // fast-track: check if its max or min expected
        if (num == BOUND) return "MMMCMXCIX";

        // fast-track: check against the dict
        if (MM.containsKey(num))
            return String.valueOf(MM.get(num));

        StringBuilder sb = new StringBuilder();
        int remainder = -1;
        int divisor = 1000;
        while (remainder != 0) {

            // first calculate the quotient
            int quotient;

            // in first iteration, simply set
            // divide the source number
            if (remainder == -1) {
                quotient = num / divisor;
                remainder = num % divisor;
            // else, work with the remainder
            } else {
                quotient = remainder / divisor;
                remainder = remainder % divisor;
            }

            // if in the current iteration no
            // qutient is present -> shift iter
            if (quotient == 0) {
                divisor = divisor / 10;
                continue;
            }

            // the number in the lead: always 1 unless is in [5:9)
            int leadNum = quotient >= 5 && quotient < 9 ? 5 : 1;

            // break-point number: always zero unless 4 or 9;
            // represents the case when number is 'quotient' - 1 * 'divisor',
            // like 'IV' for 5 or 'XL' for 40
            int corner = (quotient == 4 ? 5 : quotient == 9 ? 10 : 0);

            // leading character which begins the sequence
            char leadChar;
            // type of the following characters
            char followupChar;
            // number of following characters
            int countFollowup;

            // if the sequence is at a break-point
            // lead character is always eq to the divisor
            if (corner != 0) {
                leadChar = MM.get(divisor);
                // we should stay cautious in case sequence is thousands!
                followupChar = MM.get(Math.min(corner * divisor, 1000));
                countFollowup = 1;
            // else, just get the leading character which can be either
            // and then repeat 'divisor' as required
            } else {
                leadChar = MM.get(leadNum * divisor);
                followupChar = MM.get(divisor);
                countFollowup = quotient - leadNum;
            }

            // append the values
            sb.append(leadChar);
            if (countFollowup == 1) {
                sb.append(followupChar);
            } else {
                sb.append(String.valueOf(followupChar)
                        .repeat(Math.max(0, countFollowup)));
            }

            divisor = divisor / 10;

        }

        return sb.toString();
    }

}
