package artkonr.leet.medium;

/**
 * We start from both ends fo the array. With every move we:
 * <ul>
 *     <li>calculate the current max area</li>
 *     <li>then we see if the current height at the left end is bigger than the one on the right end</li>
 *     <li>we shift to the next left or right item which could maximize the area</li>
 * </ul>
 * <p>Regardless of optimizations, O^2 is always ~150-200 times worse
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
 *             <td colspan="20">2-3 ms</td>
 *             <td colspan="20">96.66</td>
 *         </tr>
 *         <tr>
 *             <td colspan="20">Memory footprint</td>
 *             <td colspan="20">~40 Mb</td>
 *             <td colspan="20">57.51</td>
 *         </tr>
 *     </tbody>
 * </table>
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int curmax = 0;
        int ls = 0;
        int rs = height.length - 1;

        while (ls < rs) {
            int lh = height[ls];
            int rh = height[rs];
            curmax = Math.max(curmax, (rs - ls) * Math.min(lh, rh));
            if (lh < rh) ls++; else rs--;
        }

        return curmax;
    }

}
