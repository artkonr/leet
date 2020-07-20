package artkonr.leet.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Algorithm goes like: we take a source array and for each element
 *  need two parameters:
 *  <ul>
 *      <li>difference against the target value as key</li>
 *      <li>index of the current array element as value</li>
 *  </ul>
 * We store these two in a {@link HashMap} to preserve association.
 *
 * <p>Then, for each element we dispute, if a difference has already
 *  been found by using {@link Map#containsKey(Object)}, which retains
 *  O(1) complexity.
 *
 * <p>If such a difference value can be found, we return the found
 *  value wrapped in an {@code int[]} immediately. Else we keep
 *  searching. Failure to find the pair results in an exception.
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
 *             <td colspan="20">1 ms</td>
 *             <td colspan="20">99.96</td>
 *         </tr>
 *         <tr>
 *             <td colspan="20">Memory footprint</td>
 *             <td colspan="20">39.5 Mb</td>
 *             <td colspan="20">68.84</td>
 *         </tr>
 *     </tbody>
 * </table>
 *
 */
public class TwoSumProblem {

    protected final int[] srcArr;
    protected final int target;

    public TwoSumProblem(int[] srcArr, int target) {
        this.srcArr = srcArr;
        this.target = target;
    }

    public int[] calculate() {
        Map<Integer, Integer> pairs = new HashMap<>();
        for (int i = 0; i < srcArr.length; i++) {
            int curr = srcArr[i];
            int diff = target - curr;
            if (pairs.containsKey(diff))
                return new int[] { pairs.get(diff), i };
            pairs.put(curr, i);
        }
        throw new IllegalArgumentException();
    }

}
