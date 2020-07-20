package artkonr.leet;

import artkonr.leet.easy.TwoSumProblem;

public class SortedTwoSumProblem extends TwoSumProblem {

    public SortedTwoSumProblem(int[] srcArr, int target) {
        super(srcArr, target);
    }

    @Override public int[] calculate() {
        // check args
        if (srcArr == null || srcArr.length == 0)
            throw new IllegalArgumentException();
        // if only 2 args present, check if sum is correct
        if (srcArr.length == 2)
            if ((srcArr[0] + srcArr[1]) == target)
                return new int[]{ 1, 2 };

        int str = 0;
        int end = srcArr.length - 1;

        while (str < end) {
            if ((srcArr[str] + srcArr[end]) == target)
                
            str++;
            end--;
        }

        throw new IllegalArgumentException();
    }
}
